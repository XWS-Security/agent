package com.example.agent.controller;

import com.example.agent.controller.dto.GenerateCampaignReportDto;
import com.example.agent.controller.dto.ReportDto;
import com.example.agent.service.CampaignReportService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/campaign", produces = MediaType.APPLICATION_JSON_VALUE)
public class CampaignReportController {
    private final CampaignReportService campaignReportService;

    public CampaignReportController(CampaignReportService campaignReportService) {
        this.campaignReportService = campaignReportService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('PRODUCT_CRUD_PRIVILEGE')")
    public ResponseEntity<List<ReportDto>> getReports() {
        try {
            var result = campaignReportService
                    .getAll().stream().map(ReportDto::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('PRODUCT_CRUD_PRIVILEGE')")
    public ResponseEntity<String> generateReport(@RequestBody GenerateCampaignReportDto dto) {
        try {
            campaignReportService.generateReport(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/pdf/", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasAuthority('PRODUCT_CRUD_PRIVILEGE')")
    public ResponseEntity<InputStreamResource> generatePDF(HttpServletResponse response) {
        try {
            var bis = campaignReportService.generatePDF();
            var headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=report.pdf");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
