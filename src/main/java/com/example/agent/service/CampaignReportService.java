package com.example.agent.service;

import com.example.agent.controller.dto.GenerateCampaignReportDto;
import org.xml.sax.SAXException;

import javax.net.ssl.SSLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface CampaignReportService {
    void generateReport(GenerateCampaignReportDto dto) throws IOException, ParserConfigurationException, SAXException, TransformerException;
}
