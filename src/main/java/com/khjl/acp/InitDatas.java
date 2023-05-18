package com.khjl.acp;

import com.khjl.acp.domain.PerformanceParser;
import com.khjl.acp.entity.Performance;
import com.khjl.acp.entity.Usher;
import com.khjl.acp.entity.repository.PerformanceRepository;
import com.khjl.acp.entity.repository.UsherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InitDatas {

    private final UsherRepository usherRepository;
    private final PerformanceRepository performanceRepository;

    @PostConstruct
    public void initDatas() {
        // init Users
        List<String> usherNames = List.of(
                "전채림", "유주희", "김서인", "정수아", "김태현",
                "정지은", "김응준", "노지후", "이동현", "김기남", "양수경",
                "박정린", "박지현", "박기덕", "강근호", "전지원", "김세현",
                "남정원", "민규현", "전우철", "조찬양", "최승아", "김민주",
                "지연희", "김도혜", "남지윤", "김예원", "이홍구", "김도형",
                "신병기", "허윤지", "임민진"
        );

        List<Usher> ushers = usherNames.stream()
                .map(usherName -> Usher.builder()
                        .name(usherName)
                        .build())
                .collect(Collectors.toList());

        usherRepository.saveAll(ushers);

        // init performances
        String url = "https://www.daejeon.go.kr/djac/performanceList.do?menuSeq=6709&yyyymm=202304&type=calendar&listCondition=&calendarCondition=";
        List<Performance> performances = PerformanceParser.fromUrl(url, 2023, 4).getPerformances();

        performanceRepository.saveAll(performances);
    }

}
