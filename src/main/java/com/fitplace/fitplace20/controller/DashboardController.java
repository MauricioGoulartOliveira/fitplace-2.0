package com.fitplace.fitplace20.controller;

import com.fitplace.fitplace20.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardStats() {
        return dashboardService.getStatistics();
    }
}


