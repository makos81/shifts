package com.rest.shifts.services;

import com.rest.shifts.common.ShiftAlreadyDefined;
import com.rest.shifts.common.ShiftDto;
import com.rest.shifts.domain.Shift;
import com.rest.shifts.mapper.ShiftsMapper;
import com.rest.shifts.repository.ShiftRepository;
import com.rest.shifts.repository.WorkerRepository;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {
    private ShiftRepository shiftRepository;
    private WorkerRepository workerRepository;
    private ShiftsMapper shiftsMapper;

    public void addShift(ShiftDto shiftDto) throws ShiftAlreadyDefined {
        Shift shift = shiftsMapper.mapToShift(shiftDto);
        if(shiftRepository.getShift(shift.getFrom(), shift.getTo())==null){
            throw new ShiftAlreadyDefined();
        }
        Shift currentShift = shiftRepository.save(shift);}
}
