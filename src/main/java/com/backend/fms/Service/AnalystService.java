package com.backend.fms.Service;

import com.backend.fms.Entity.FinanceRecord;
import com.backend.fms.Entity.User;
import com.backend.fms.Repository.RecordRepository;
import com.backend.fms.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalystService {


    @Autowired UserRepository userRepository ;
    @Autowired RecordRepository recordRepository ;
    @Autowired DashboardService dashboardService ;

    public List<FinanceRecord> getRecords(Long userId) {
        User user = userRepository.findById(userId)
                .orElse(null);
        if (user == null) {
            return List.of();
        }
        return recordRepository.findByUserId(user.getId());
    }

    public List<String> getDashboard(Long userId) {
        User user = userRepository.findById(userId)
                .orElse(null);
        if (user == null) {
            return List.of();
        }
        return dashboardService.dashboard(userId) ;
    }

}
