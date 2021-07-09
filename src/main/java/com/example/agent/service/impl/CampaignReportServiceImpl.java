package com.example.agent.service.impl;

import com.example.agent.controller.dto.AgentReportDto;
import com.example.agent.controller.dto.GenerateCampaignReportDto;
import com.example.agent.exist.XmlService;
import com.example.agent.model.CampaignReport;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CampaignReportServiceImpl implements CampaignReportService {
    private final XmlService existService;

    @Value("${NISTAGRAM_CAMPAIGN}")
    private String nistagramCampaignURI;

    public CampaignReportServiceImpl(XmlService existService) {
        this.existService = existService;
    }

    @Override
    public void generateReport(GenerateCampaignReportDto dto) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        var info = getCampaignInfo(dto);
        var reports = new ArrayList<CampaignReport>();
        for (var reportInfo : info) {
            var report = new CampaignReport(2, reportInfo.getLikes(), reportInfo.getDislikes(), reportInfo.getComments(), 2000.00);
            reports.add(report);
            System.out.println("Result: " + report);

        }
        existService.saveAll(reports);
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
