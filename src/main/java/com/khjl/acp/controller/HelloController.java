package com.khjl.acp.controller;

import com.khjl.acp.domain.Performance;
import com.khjl.acp.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final PerformanceRepository performanceRepository;

    @GetMapping("/performance_sample_data")
    public String performanceSampleData() {
        Performance performance = new Performance();
        performance.setType("대관공연");
        performance.setHall("아트홀");
        performance.setRating("3세이상");
        performance.setDateTime(LocalDateTime.now());

        performanceRepository.save(performance);
        return "ok";
    }
}
