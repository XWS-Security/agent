package com.example.agent.service.impl;

import com.example.agent.controller.dto.AgentReportDto;
import com.example.agent.controller.dto.GenerateCampaignReportDto;
import com.example.agent.model.CampaignReport;
import com.example.agent.report.ReportPDFExporter;
import com.example.agent.report.XmlService;
import com.example.agent.repository.OrderRepository;
import com.example.agent.service.CampaignReportService;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.xml.sax.SAXException;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CampaignReportServiceImpl implements CampaignReportService {
    private final XmlService xmlService;
    private final ReportPDFExporter reportPDFExporter;
    private final OrderRepository orderRepository;

    @Value("${NISTAGRAM_CAMPAIGN}")
    private String nistagramCampaignURI;

    public CampaignReportServiceImpl(XmlService xmlService, ReportPDFExporter reportPDFExporter, OrderRepository orderRepository) {
        this.xmlService = xmlService;
        this.reportPDFExporter = reportPDFExporter;
        this.orderRepository = orderRepository;
    }

    @Override
    public void generateReport(GenerateCampaignReportDto dto) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        var info = getCampaignInfo(dto);
        var reports = new ArrayList<CampaignReport>();
        var orders = orderRepository.findAll();
        double totalRevenue = 0.0;
        for (var order : orders) {
            for (var item : order.getItems()) {
                totalRevenue += item.getAmount() * item.getPrice();
            }
        }
        for (var reportInfo : info) {
            String date = "";
            String clicks = "";
            if (reportInfo.getClicks() != null)
                for (var click : reportInfo.getClicks())
                    clicks += click.getUsername() + " : " + click.getQuantity() + "\n";
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
            if (reportInfo.getDate() != null) date = format.format(reportInfo.getDate());
            else date = format.format(reportInfo.getDateStart()) + " - " + format.format(reportInfo.getDateEnd());
            int views = 0;
            if (reportInfo.getViews() != null) views = reportInfo.getViews().size();
            var report = new CampaignReport(2, reportInfo.getLikes(), reportInfo.getDislikes(), reportInfo.getComments(),
                    totalRevenue, reportInfo.getCampaignId(), date, clicks, views);
            reports.add(report);
        }
        xmlService.saveAll(reports);
    }

    @Override
    public List<CampaignReport> getAll() throws IOException, SAXException, ParserConfigurationException {
        return xmlService.readAll();
    }

    @Override
    public ByteArrayInputStream generatePDF() throws IOException, ParserConfigurationException, SAXException {
        var reports = xmlService.readAll();
        reportPDFExporter.setReports(reports);
        return reportPDFExporter.export();
    }

    private List<AgentReportDto> getCampaignInfo(GenerateCampaignReportDto dto) throws SSLException {
        // SSL
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        // Creating web client.
        WebClient client = WebClient.builder()
                .baseUrl(nistagramCampaignURI)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        // Define a method.
        var result = client.get()
                .uri("/report/")
                .headers(h -> h.setBearerAuth(dto.getToken()))
                .retrieve()
                .bodyToMono(AgentReportDto[].class)
                .block();

        return Arrays.asList(result.clone());
    }
}
