package com.smartmeter.dashboard.Controller;

import com.smartmeter.dashboard.Model.MeterReading;
import com.smartmeter.dashboard.Repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MeterController {

    @Autowired
    private MeterRepository repository;

    @GetMapping("/")
    public String dashboard(@RequestParam(value = "search", required = false) String search, Model model) {
        List<MeterReading> readings;
        if (search != null && !search.isEmpty()) {
            readings = repository.findByMeterIdContainingIgnoreCase(search);
        } else {
            readings = repository.findAll();
        }

        double totalConsumption = readings.stream().mapToDouble(MeterReading::getConsumptionKwh).sum();
        long alertCount = readings.stream().filter(r -> "ALERT".equals(r.getStatus())).count();

        model.addAttribute("readings", readings);
        model.addAttribute("totalConsumption", totalConsumption);
        model.addAttribute("alertCount", alertCount);
        model.addAttribute("search", search);
        return "dashboard";
    }

    @PostMapping("/add")
    public String addReading(@RequestParam String meterId, @RequestParam double consumptionKwh) {
        String status = consumptionKwh > 100.0 ? "ALERT" : "NORMAL";
        MeterReading reading = new MeterReading(meterId, consumptionKwh, LocalDateTime.now(), status);
        repository.save(reading);
        return "redirect:/";
    }
}