package com.rest.shifts.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WorkerDto {
    private int id;
    private String firstName;
    private String lastName;
}
