package com.khjl.acp.controller;

import com.khjl.acp.domain.PerformanceParser;
import com.khjl.acp.entity.Performance;
import com.khjl.acp.entity.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HelloController {

    private final PerformanceRepository performanceRepository;

    @GetMapping("/")
    public String index(Model model) {
        String url = "https://www.daejeon.go.kr/djac/performanceList.do?menuSeq=6709&yyyymm=202304&type=calendar&listCondition=&calendarCondition=";
        List<Performance> performances = PerformanceParser.fromUrl(url, 2023, 4).getPerformances();

        performanceRepository.saveAll(performances);

        model.addAttribute("performances", performances);
        return "index";
    }

}
