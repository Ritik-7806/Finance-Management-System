package com.backend.fms.Repository;

import com.backend.fms.Entity.FinanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<FinanceRecord,Long> {
    List<FinanceRecord> findByUserId(Long userId);
}
