package com.rest.shifts.domain;

import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="WORKER")
public class Worker {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;

    // adnotacje
    private List<Shift> shifts = new ArrayList<>();

    public Worker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Worker() {
    }

    public List<Shift> allAssignedShifts() {
        return shifts;
    }

    public Shift currentShift() {
        // trzeba przeiterowac się po shifts i znalezc aktualna zmianę
        return null;
    }

    public LocalDateTime startOfCurrentShif() {
        return currentShift().getFrom();
    }

    public LocalDateTime sendOfCurrentShif() {
        return currentShift().getTo();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
