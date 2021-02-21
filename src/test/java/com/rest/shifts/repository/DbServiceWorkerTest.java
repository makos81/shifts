package com.rest.shifts.repository;

import com.rest.shifts.domain.Shift;
import com.rest.shifts.domain.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DbServiceWorkerTest {
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @Test
    public void savingShift(){
        //given
        Shift shift = new Shift(LocalDateTime.of(2021,02,20,10,0),
                LocalDateTime.of(2021,02,20,18,0));

        //when
        shiftRepository.save(shift);
        int id = shift.getId();
        //then
        Assertions.assertNotEquals(0, id);
        shiftRepository.deleteById(id);
    }

    @Test
    public void savingWorker(){
        //given
        Worker worker = new Worker("maciej", "ra");

        //when
        workerRepository.save(worker);
        int id = worker.getId();
        //then
        Assertions.assertNotEquals(0, id);
        workerRepository.deleteById(id);

    }
}
