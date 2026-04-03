package com.backend.fms.Service;

import com.backend.fms.Entity.FinanceRecord;
import com.backend.fms.Entity.User;
import com.backend.fms.Enum.RecordType;
import com.backend.fms.Repository.RecordRepository;
import com.backend.fms.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {

    @Autowired private RecordRepository recordRepository ;
    @Autowired private UserRepository userRepository ;


    private User getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username);
    }

    public List<String> dashboard(long userId) {

        //ALL FINANCE RECORDS FOR A SPECIFIC USER
        List<FinanceRecord> records = recordRepository.findByUserId(userId);

        //TOTAL INCOME
        BigDecimal totalIncome = BigDecimal.ZERO;
        for (FinanceRecord r : records) {
            if (r.getType() == RecordType.INCOME) {
                totalIncome = totalIncome.add(r.getAmount());
            }
        }

        //TOTAL EXPENSE
        BigDecimal totalExpense = BigDecimal.ZERO;
        for (FinanceRecord r : records) {
            if (r.getType() == RecordType.EXPENSE) {
                totalExpense = totalExpense.add(r.getAmount());
            }
        }

        //NET BALANCE
        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        //SAVINGS
        BigDecimal savings;
        if (netBalance.compareTo(BigDecimal.ZERO) > 0) {
            savings = netBalance;
        } else {
            savings = BigDecimal.ZERO;
        }

        // FINAL LIST
        List<String> dashboardData = new ArrayList<>();
        dashboardData.add("Total Income = " + totalIncome);
        dashboardData.add("Total Expense = " + totalExpense);
        dashboardData.add("Net Balance = " + netBalance);
        dashboardData.add("Savings = " + savings);

        return dashboardData;
    }



}
