package com.khjl.acp.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Usher {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer salary;

    @OneToMany(mappedBy = "usher")
    @ToString.Exclude
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "usher")
    @ToString.Exclude
    private List<Change> changeList;

    public void setChangeList(List<Change> changeList) {
        this.changeList = changeList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Builder
    public Usher(Long id, String name, Integer salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
