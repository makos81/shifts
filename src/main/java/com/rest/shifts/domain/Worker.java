package com.rest.shifts.domain;

import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
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

    @OneToMany(mappedBy="worker")
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

    private Shift currentShift() {
        // aktualna zmiana. co jest aktualna zmianą? ostatnio przypisana pracownikowi?
        // jeśli tak, to:
        if(shifts == null || shifts.isEmpty()) {
            return null;
        }
        return shifts.get(shifts.size()-1);
    }

    public LocalDateTime startOfCurrentShift() {
        return currentShift().getFrom();
    }

    public LocalDateTime endOfCurrentShift() {
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
