package com.rest.shifts.repository;

import com.rest.shifts.domain.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DbServiceShifts {
    @Autowired
    ShiftsRepository shiftsRepository;

    public List<Shift> getAllShifts(){
        return shiftsRepository.findAll();
    }

    public Optional<Shift> getShiftByWorkerId(int workerId){
        return shiftsRepository.findByWorker_Id(workerId);
    }

    public void saveShift(Shift shift){
        shiftsRepository.save(shift);
    }
}
