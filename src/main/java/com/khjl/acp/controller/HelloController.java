package com.khjl.acp.controller;

import com.khjl.acp.domain.PerformanceParser;
import com.khjl.acp.entity.Performance;
import com.khjl.acp.entity.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final PerformanceRepository performanceRepository;

    @GetMapping("/performance_sample_data")
    public String performanceSampleData() {
        String url = "https://www.daejeon.go.kr/djac/performanceList.do?menuSeq=6709&yyyymm=202305&type=calendar&listCondition=&calendarCondition=";
        List<Performance> performances = PerformanceParser.fromUrl(url, 2023, 5).getPerformances();

        performanceRepository.saveAll(performances);

        return "ok";
    }
}
