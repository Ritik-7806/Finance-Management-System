package com.backend.fms.Controller;

import com.backend.fms.Entity.FinanceRecord;
import com.backend.fms.Service.AnalystService;
import com.backend.fms.Service.DashboardService;
import com.backend.fms.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("analyst")
public class AnaylstController {

    @Autowired private AnalystService analystService ;

    @Autowired private DashboardService dashboardService;


    @GetMapping("/record/{userId}")
    public ResponseEntity<List<FinanceRecord>> getRecords(@PathVariable Long userId) {
        return ResponseEntity.ok(analystService.getRecords(userId));
    }


    @GetMapping("/dashboard/{userId}")
    public ResponseEntity<List<String>> getDashboard(@PathVariable long userId) {
        return ResponseEntity.ok(dashboardService.dashboard(userId));
    }



}
