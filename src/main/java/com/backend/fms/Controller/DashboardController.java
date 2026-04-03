package com.backend.fms.Controller;

import com.backend.fms.Repository.RecordRepository;
import com.backend.fms.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired DashboardService dashboardService;

    @GetMapping("{userId}")
    public ResponseEntity<String> dashboard(@PathVariable long userId){
        dashboardService.dashboard(userId) ;
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("This is your Dashboard") ;
    }

}
