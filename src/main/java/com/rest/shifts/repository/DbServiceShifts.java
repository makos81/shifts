package com.rest.shifts.repository;

import com.rest.shifts.domain.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* TODO: do wywalenia jak dla mnie :-) */
@Service
public class DbServiceShifts {
    @Autowired
    ShiftRepository shiftRepository;

    public List<Shift> getAllShifts(){
        return shiftRepository.findAll();
    }

    public Shift getShiftByWorkerId(int workerId){
        return shiftRepository.getActiveShiftForWorker(workerId);
    }

    public void saveShift(Shift shift){
        shiftRepository.save(shift);
    }

    public List<Shift> getAllShiftsForWorker(int workerId){
        return shiftRepository.getShiftsForWorker(workerId);
    }

}
