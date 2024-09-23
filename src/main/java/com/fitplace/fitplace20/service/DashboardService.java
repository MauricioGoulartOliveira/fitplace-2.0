package com.fitplace.fitplace20.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {


    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();


        int totalUsers = 100;
        int totalExercises = 50;

        stats.put("totalUsers", totalUsers);
        stats.put("totalExercises", totalExercises);



        return stats;
    }
}
