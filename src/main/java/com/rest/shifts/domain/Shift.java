package com.rest.shifts.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Table(name="SHIFTS")
@NamedNativeQuery(
        name = "Shift.getActiveShiftForWorker",
        query = "SELECT * FROM SHIFTS WHERE id=:ID " +
                "ORDER BY ID DESC LIMIT 1",
        resultClass = Shift.class
)
@NamedNativeQuery(
        name = "Shift.getShift",
        query = "SELECT * FROM SHIFTS WHERE STARTING_DATETIME=:STARTING_DATETIME" +
                " AND  END_DATETIME=:END_DATETIME",
        resultClass = Shift.class
)
public class Shift {
    private int id;
    private LocalDateTime from;
    private LocalDateTime to;

    private List<Worker> workerList = new ArrayList<>();

    public Shift() {
    }

    // tu będzie w takim razie nie int workerId tylko Worker worke, a w zasadzie to Workera z konstruktora można wyjebać i zostawić tylko
    // w assignTo. Zależy czy klasa Shift wymaga to działania workera - no chyba nie, bo można dodać po prostu zmianę, bez przypisanego pracownika
    // zatem w konstruktorze nie powinno być Workera, tylko w razie potrzeby przekazujemy go do metody assignTo
    public Shift(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    // te metode potem wywolasz w serwisie. I w serwisie już nie będziesz musiał sprawdzać reguł typu czy to druga zmiana z rzedu czy nie,
    // bo ta metoda biznesowa to kontroluje. Gdyby trzeba zawsze w serwisie sprawdzac te reguly przy przypisywaniu zmiany pracownikowi, to
    // to ktoś mógłby zapomnieć w serwisie sprawdzić tych reguł. A dzięki temu, że
    // mamy je w assignTo() to nie trzeba się o to martwić, bo ta metoda przypisywania sama się waliduje - sama dba o reguły biznesowe
    // Moge więc tę metodę wywoływać wszędzie gdzie potrzebuje w mojej aplikacji nie musząc pamiętać o zakodowaniu zawsze reguł.

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "SHIFT_ID", unique = true)
    public int getId() {
        return id;
    }

    @Column(name="STARTING_DATETIME")
    public LocalDateTime getFrom() {
        return from;
    }

    @Column(name="END_DATETIME")
    public LocalDateTime getTo() {
        return to;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_SHIFT_WORKER",
            joinColumns = {@JoinColumn(name = "SHIFT_ID", referencedColumnName = "SHIFT_ID")},
                    inverseJoinColumns = {@JoinColumn(name = "WORKER", referencedColumnName = "WORKER_ID")}
            )
    public List<Worker> getWorkerList() {
        return workerList;
    }

    public void setWorker(Worker worker){
        workerList.add(worker);
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }
}
