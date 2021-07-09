package com.example.agent.service.impl;

import com.example.agent.controller.dto.GenerateCampaignReportDto;
import com.example.agent.service.CampaignReportService;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Service
public class CampaignReportServiceImpl implements CampaignReportService {
    @Value("${NISTAGRAM_CAMPAIGN}")
    private String nistagramCampaignURI;

    @Override
    public void generateReport(GenerateCampaignReportDto dto) throws SSLException {
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
                .bodyToMono(String.class)
                .block();

        System.out.println("Result: " + result);
    }
}
