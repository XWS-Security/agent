package com.example.agent.controller;

import com.example.agent.controller.dto.GenerateCampaignReportDto;
import com.example.agent.controller.dto.OrderDto;
import com.example.agent.service.CampaignReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/campaign", produces = MediaType.APPLICATION_JSON_VALUE)
public class CampaignReportController {
    private final CampaignReportService campaignReportService;

    public CampaignReportController(CampaignReportService campaignReportService) {
        this.campaignReportService = campaignReportService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('PRODUCT_CRUD_PRIVILEGE')")
    public ResponseEntity<List<OrderDto>> generateReport(@RequestBody GenerateCampaignReportDto dto) {
        try {
            campaignReportService.generateReport(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
