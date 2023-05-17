package com.khjl.acp.entity;

import com.khjl.acp.entity.performance.Performance;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usher_id")
    private Usher usher;

    @OneToMany(mappedBy = "schedule")
    private List<Change> changeList;

}
