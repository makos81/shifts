package com.rest.shifts.common;

import java.time.LocalDateTime;

public class ShiftDto {
    private int id;
    private LocalDateTime from;
    private LocalDateTime to;
    private int workerId;

    public ShiftDto(int id, LocalDateTime from, LocalDateTime to, int workerId) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.workerId = workerId;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public int getWorkerId() {
        return workerId;
    }
}
