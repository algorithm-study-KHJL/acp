package com.khjl.acp.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Usher {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer salary;

    @OneToMany(mappedBy = "usher")
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "usher")
    private List<Change> changeList;

    @Builder
    public Usher(Long id, String name, Integer salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
