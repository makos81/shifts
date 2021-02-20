package com.rest.shifts.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="SHIFTS")
public class Shift {
    private int id;
    private Date from;
    private Date to;
    private int workerId;

    public Shift(){

    }

    public Shift(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public int getId() {
        return id;
    }

    @Column(name="FROM")
    public Date getFrom() {
        return from;
    }

    @Column(name="TO")
    public Date getTo() {
        return to;
    }

    @Column(name="WORKER_ID")
    public int getWorker() {
        return workerId;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public void setWorker(int worker) {
        this.workerId = worker;
    }

    public void setId(int id) {
        this.id = id;
    }
}
