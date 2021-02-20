package com.rest.shifts.controller;

import com.rest.shifts.domain.Shift;
import com.rest.shifts.domain.ShiftDto;
import com.rest.shifts.domain.Worker;
import com.rest.shifts.mapper.ShiftsMapper;
import com.rest.shifts.repository.DbServiceShifts;
import com.rest.shifts.repository.DbServiceWorker;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.temporal.ChronoUnit;

import static javax.xml.datatype.DatatypeConstants.HOURS;

@RestController
@RequestMapping("/v1/shifts")
@RequiredArgsConstructor
public class ShiftsController {
    @Autowired
    private DbServiceShifts dbServiceShifts;
    @Autowired
    private ShiftsMapper shiftsMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createShift", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addShift(@RequestBody ShiftDto shiftDto) throws Exception{
        int workerId = shiftDto.getWorkerId();
        Shift currentShift = dbServiceShifts.getShiftByWorkerId(workerId).orElseThrow(WorkerNotFoundException::new);
        long hours = ChronoUnit.HOURS.between(shiftDto.getTo(), currentShift.getFrom());
        if(hours < 8){
            throw new TwoShiftsInRowException();
        }else{
            dbServiceShifts.saveShift(shiftsMapper.mapToShift(shiftDto));
        }
    }

}
