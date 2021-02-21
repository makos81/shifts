package com.rest.shifts.common;

import java.time.LocalDateTime;

public class ShiftDto {
    private LocalDateTime from;
    private LocalDateTime to;

    public ShiftDto(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

}
