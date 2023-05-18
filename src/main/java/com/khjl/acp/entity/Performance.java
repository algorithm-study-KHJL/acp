package com.khjl.acp.entity;

import com.khjl.acp.entity.Schedule;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Performance {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dateTime;

    private String name;
    private String type;
    private String hall;
    private String rating;

    @OneToMany(mappedBy = "performance")
    @ToString.Exclude
    private List<Schedule> scheduleList;

    @Builder
    public Performance(Long id, LocalDateTime dateTime, String name, String type, String hall, String rating, List<Schedule> scheduleList) {
        this.id = id;
        this.dateTime = dateTime;
        this.name = name;
        this.type = type;
        this.hall = hall;
        this.rating = rating;
        this.scheduleList = scheduleList;
    }
}

