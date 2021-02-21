package com.rest.shifts.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Table(name="SHIFTS")
@NamedNativeQuery(
        name = "Shift.getActiveShiftForWorker",
        query = "SELECT * FROM SHIFTS WHERE id=:ID " +
                "ORDER BY ID DESC LIMIT 1",
        resultClass = Shift.class
)
@NamedQuery(
        name = "Shift.getShiftsForWorker",
        query = "FROM Shift WHERE workerId=:ID"
)
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

    /* [TODO] relacje się trochę inaczej robi. Wygooglaj jak się robi @OneToMany w hibernate'cie, bo teraz to taki udawany klucz obcy masz, hibernate nie
             stworzy Ci constrainta FK dla takiego zapiu jak poniżej.
     */
    @Column(name="WORKER_ID")
    private int workerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Worker worker;

    public Shift() {
    }

    public Shift(LocalDateTime from, LocalDateTime to, int workerId) {
        this.from = from;
        this.to = to;
        this.workerId = workerId;
    }

    public void assignTo(Worker worker) {
        // tu reguły biznesowe, czyli np.: czy nie próbuję przypisać pracownikowi już przypisanej mu zmiany, mogłobyto wyglądać tak mniej więcej:
        if(worker.startOfCurrentShift().isEqual(from) && worker.endOfCurrentShift().isEqual(to)) {
            throw new RuntimeException("Worker has already assigned shift");
        }
        // i teraz czy nie jest to druga zmiana z rzędu.
        // jakbyś chciał tutaj na przykład się przeiterować przez wszystkie zmiany przypisane do pracownika to użyj worker.allAssignedShifts, jakbyś potrzebował innych to dodawaj swoje. dzięki
        // temu mamy enkapsulację i jasne nazwy metod.
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
