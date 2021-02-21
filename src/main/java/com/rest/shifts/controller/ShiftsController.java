package com.rest.shifts.controller;

import com.rest.shifts.common.ApiError;
import com.rest.shifts.common.ShiftValidator;
import com.rest.shifts.domain.Shift;
import com.rest.shifts.common.ShiftDto;
import com.rest.shifts.repository.ShiftRepository;
import com.rest.shifts.services.ShiftService;
import com.rest.shifts.services.WorkerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/shifts")
@RequiredArgsConstructor
public class ShiftsController {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private ShiftRepository shiftRepository;

    @RequestMapping(method = RequestMethod.POST, value = "createShift", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addShift(@RequestBody ShiftDto shiftDto) {
        ShiftValidator shiftValidator = new ShiftValidator();
        if(!shiftValidator.validatedShift(shiftDto) || !shiftValidator.validateDates(shiftDto)){
            return new ResponseEntity(new ApiError("Wrong data provided"), HttpStatus.BAD_REQUEST);
        }
        try {
            shiftService.addShift(shiftDto);
        } catch(Exception e) {
            return new ResponseEntity(new ApiError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllShiftsForWorker")
    public List<Shift> getListOfShiftsForWorkerId(int workerId){
        return workerService.getShiftsForWorker(workerId);
    }

    @RequestMapping(method = RequestMethod.GET, value="getAllShifts")
    public List<Shift> getListOfAllShifts(){
        return shiftRepository.findAll();
    }

}
