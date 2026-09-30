package com.smartmeter.dashboard.Repository;

import com.smartmeter.dashboard.Model.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MeterRepository extends JpaRepository<MeterReading, Long> {
    List<MeterReading> findByMeterIdContainingIgnoreCase(String meterId);
    List<MeterReading> findByStatus(String status);
}