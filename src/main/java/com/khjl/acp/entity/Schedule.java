package com.khjl.acp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    @ToString.Exclude
    private Performance performance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usher_id")
    @ToString.Exclude
    private Usher usher;

    @OneToMany(mappedBy = "schedule")
    @ToString.Exclude
    private List<Change> changeList;

    @Builder
    public Schedule(Long id, Performance performance, Usher usher) {
        this.id = id;
        this.performance = performance;
        this.usher = usher;
    }
}
