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
import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DbServiceTest {
    @Autowired
    private ShiftsRepository shiftsRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @Test
    public void saveTest(){
        //given
        Worker worker = new Worker("maciej", "ra");
        Shift shift = new Shift();
        shift.setFrom(new Date());
        shift.setWorker(1);

        //when
        workerRepository.save(worker);
        shiftsRepository.save(shift);
        int id = shift.getId();
        //then
        Assertions.assertNotEquals(0, id);

    }
}
