package com.rest.shifts.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ShiftDto {
    private int id;
    private LocalDateTime from;
    private LocalDateTime to;
    private int workerId;
}
