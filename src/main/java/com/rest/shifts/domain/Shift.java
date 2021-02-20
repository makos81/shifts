package com.rest.shifts.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Table(name="SHIFTS")
public class Shift {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name="STARTING_DATETIME")
    private LocalDateTime from;
    @Column(name="END_DATETIME")
    private LocalDateTime to;
    @Column(name="WORKER_ID")
    private int workerId;

    public Shift(LocalDateTime from, LocalDateTime to, int workerId) {
        this.from = from;
        this.to = to;
        this.workerId = workerId;
    }
}
