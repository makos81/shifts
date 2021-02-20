package com.rest.shifts.mapper;

import com.rest.shifts.domain.Shift;
import com.rest.shifts.domain.ShiftDto;
import org.springframework.stereotype.Service;

@Service
public class ShiftsMapper {
    public ShiftDto mapToDto(Shift shift){
        return new ShiftDto(
                shift.getId(),
                shift.getFrom(),
                shift.getTo(),
                shift.getWorkerId()
        );
    }

    public Shift mapToShift(ShiftDto shiftDto){
        return new Shift(
                shiftDto.getFrom(),
                shiftDto.getTo(),
                shiftDto.getWorkerId()
        );
    }
}
