package com.rest.shifts.controller;

import com.rest.shifts.domain.ApiError;
import com.rest.shifts.domain.Shift;
import com.rest.shifts.domain.ShiftDto;
import com.rest.shifts.domain.Worker;
import com.rest.shifts.mapper.ShiftsMapper;
import com.rest.shifts.repository.DbServiceShifts;
import com.rest.shifts.repository.DbServiceWorker;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/v1/shifts")
@RequiredArgsConstructor
public class ShiftsController {
    @Autowired
    private DbServiceShifts dbServiceShifts;
    @Autowired
    private ShiftsMapper shiftsMapper;
    @Autowired
    private DbServiceWorker dbServiceWorker;

    @RequestMapping(method = RequestMethod.POST, value = "createShift", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addShift(@RequestBody ShiftDto shiftDto) throws Exception{
        Shift shift = shiftsMapper.mapToShift(shiftDto);
        int workerId = shift.getWorkerId();
        dbServiceWorker.getById(workerId).orElseThrow(WorkerNotFoundException::new);
        Shift currentShift = dbServiceShifts.getShiftByWorkerId(workerId);
        if(currentShift==null){
            dbServiceShifts.saveShift(shiftsMapper.mapToShift(shiftDto));
            return ResponseEntity.ok(HttpStatus.OK);
        }else{
            long hours = ChronoUnit.HOURS.between(currentShift.getTo(), shiftDto.getFrom());
            if(hours < 8){
                return new ResponseEntity(new ApiError("Second shift in the row"),HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                dbServiceShifts.saveShift(shiftsMapper.mapToShift(shiftDto));
                return ResponseEntity.ok(HttpStatus.OK);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllShiftsForWorker")
    public List<Shift> getListOfShiftsForWorkerId(int workerId){
        return dbServiceShifts.getAllShiftsForWorker(workerId);
    }

    @RequestMapping(method = RequestMethod.GET, value="getAllShifts")
    public List<Shift> getListOfAllShifts(){
        return dbServiceShifts.getAllShifts();
    }

}
