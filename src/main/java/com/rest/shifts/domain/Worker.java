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
    private int id;
    private String firstName;
    private String lastName;
    private List<Shift> shifts;

    public Worker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        shifts = new ArrayList<>();
    }

    public Worker() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "WORKER_ID", unique = true)
    public int getId() {
        return id;
    }
    @Column(name="FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }
    @Column(name="LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "workerList")
    public List<Shift> getShifts() {
        return shifts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}
