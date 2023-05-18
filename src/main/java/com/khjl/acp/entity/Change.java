package com.khjl.acp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Change {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    @ToString.Exclude
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usher_id")
    @ToString.Exclude
    private Usher usher;

    public void setUsher(Usher usher) {
        this.usher = usher;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Builder
    public Change(Long id, Schedule schedule, Usher usher) {
        this.id = id;
        this.schedule = schedule;
        this.usher = usher;
    }
}
