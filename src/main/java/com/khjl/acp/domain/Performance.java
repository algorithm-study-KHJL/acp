package com.khjl.acp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Performance {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dateTime;

    private String type;

    private String hall;

    private String rating;

    @OneToMany(mappedBy = "performance")
    private List<Schedule> scheduleList;

}

