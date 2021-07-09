package com.example.agent.service;

import com.example.agent.controller.dto.GenerateCampaignReportDto;

import javax.net.ssl.SSLException;

public interface CampaignReportService {
    void generateReport(GenerateCampaignReportDto dto) throws SSLException;
}
