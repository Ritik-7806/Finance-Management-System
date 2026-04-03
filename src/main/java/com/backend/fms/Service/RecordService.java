package com.backend.fms.Service;

import com.backend.fms.DTO.CreateRecord;
import com.backend.fms.Entity.FinanceRecord;
import com.backend.fms.Entity.User;
import com.backend.fms.Repository.RecordRepository;
import com.backend.fms.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecordService {

    @Autowired UserRepository userRepository ;
    @Autowired RecordRepository recordRepository ;

    private User getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username);
    }

    public void createRecord(CreateRecord dto) {
        User user = getCurrentUser();

        FinanceRecord frecord = new FinanceRecord();
        frecord.setUser(user);
        frecord.setAmount(dto.getAmount());
        frecord.setType(dto.getType());
        frecord.setCategory(dto.getCategory());
        frecord.setCreatedDate(dto.getCreatedDate());
        frecord.setNotes(dto.getNotes());
        frecord.setCreatedDate(LocalDate.now());

        recordRepository.save(frecord);
    }

    public List<FinanceRecord> myRecords(){
        User user = getCurrentUser();
        return recordRepository.findByUserId(user.getId()) ;

    }

    public void updateRecord(Long recordId, CreateRecord dto) {
        User user = getCurrentUser();
        FinanceRecord frecord = recordRepository.findById(recordId).orElse(null);
        if (frecord == null || frecord.getUser().getId() != (user.getId())) {
            throw new RuntimeException("Record not found or access denied");
        }
        frecord.setAmount(dto.getAmount());
        frecord.setType(dto.getType());
        frecord.setCategory(dto.getCategory());
        frecord.setNotes(dto.getNotes());
        frecord.setCreatedDate(LocalDate.now());

        recordRepository.save(frecord);
    }

    public void deleteRecord(Long recordId){
        User user = getCurrentUser();
        FinanceRecord frecord = recordRepository.findById(recordId).orElse(null);
        if(frecord == null || frecord.getUser().getId() != user.getId()){
            throw new RuntimeException("Record not found or access denied");
        }

        recordRepository.delete(frecord);
    }

    public List<FinanceRecord> getRecordsForCurrentUser() {
        User user = getCurrentUser();
        return recordRepository.findByUserId(user.getId());
    }
}
