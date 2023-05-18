package com.khjl.acp.entity.repository;

import com.khjl.acp.entity.Performance;
import com.khjl.acp.entity.Schedule;
import com.khjl.acp.entity.Usher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PerformanceRepositoryTest {

    @Autowired
    PerformanceRepository performanceRepository;
    @Autowired
    UsherRepository usherRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EntityManager em;

    @Transactional
    @Test
    void name() {

        Performance performance = performanceRepository.findByDateTime(LocalDateTime.of(2023, 4, 1, 0, 0)).get(0);
        Usher usher = usherRepository.findByName("정지은").get(0);

        System.out.println(performance);
        System.out.println(usher);

        Schedule schedule = Schedule.builder()
                .performance(performance)
                .usher(usher)
                .build();

        scheduleRepository.save(schedule);



        Schedule savedSchedule = scheduleRepository.findAll().get(0);

        System.out.println(savedSchedule.getPerformance());
        System.out.println(savedSchedule.getUsher());
    }
}
