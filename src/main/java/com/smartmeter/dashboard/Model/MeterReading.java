package com.smartmeter.dashboard.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MeterReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String meterId;
    private double consumptionKwh;
    private LocalDateTime timestamp;
    private String status; // NORMAL, ALERT

    public MeterReading() {}

    public MeterReading(String meterId, double consumptionKwh, LocalDateTime timestamp, String status) {
        this.meterId = meterId;
        this.consumptionKwh = consumptionKwh;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMeterId() { return meterId; }
    public void setMeterId(String meterId) { this.meterId = meterId; }
    public double getConsumptionKwh() { return consumptionKwh; }
    public void setConsumptionKwh(double consumptionKwh) { this.consumptionKwh = consumptionKwh; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}