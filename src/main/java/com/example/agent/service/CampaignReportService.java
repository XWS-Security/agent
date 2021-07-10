package com.example.agent.service;

import com.example.agent.controller.dto.GenerateCampaignReportDto;
import com.example.agent.model.CampaignReport;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface CampaignReportService {
    void generateReport(GenerateCampaignReportDto dto) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    List<CampaignReport> getAll() throws IOException, SAXException, ParserConfigurationException;

    ByteArrayInputStream generatePDF() throws IOException, ParserConfigurationException, SAXException;
}
