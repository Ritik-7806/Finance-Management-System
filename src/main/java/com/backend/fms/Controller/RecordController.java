package com.backend.fms.Controller;

import com.backend.fms.DTO.CreateRecord;
import com.backend.fms.Entity.FinanceRecord;
import com.backend.fms.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("record")
public class RecordController {

    @Autowired
    RecordService recordService ;

    @PostMapping("create-record")
    public ResponseEntity<String> createRecord(@RequestBody CreateRecord record){
        recordService.createRecord(record) ;
        return ResponseEntity.ok("Record have been added successfully") ;
    }

    @GetMapping("my-records")
    public ResponseEntity<List<FinanceRecord>> myRecords() {
        List<FinanceRecord> records = recordService.myRecords();
        return ResponseEntity.ok(records);
    }


    @PutMapping("update-records/{recordId}")
    public ResponseEntity<String> updateRecord(@PathVariable Long recordId, @RequestBody CreateRecord record){
        recordService.updateRecord(recordId, record) ;
        return ResponseEntity.ok("Records are updated successfully") ;
    }

    @DeleteMapping("{recordId}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long recordId){
        recordService.deleteRecord(recordId);
        return ResponseEntity.ok("Record is deleted successfully") ;
    }





}
