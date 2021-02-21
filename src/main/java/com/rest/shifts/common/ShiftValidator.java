package com.rest.shifts.common;

public class ShiftValidator {
    public boolean validatedShift(ShiftDto shiftDto) {
        return shiftDto.getFrom()!=null && shiftDto.getTo()!=null;
    }

    public boolean validateDates(ShiftDto shiftDto){
        return shiftDto.getTo().isAfter(shiftDto.getFrom());
    }
}
