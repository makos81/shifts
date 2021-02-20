package com.rest.shifts.controller;

import com.rest.shifts.common.ApiError;
import com.rest.shifts.domain.Shift;
import com.rest.shifts.common.ShiftDto;
import com.rest.shifts.repository.ShiftRepository;
import com.rest.shifts.services.ShiftService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    private ShiftService shiftService;
    private ShiftRepository shiftRepository;

    @RequestMapping(method = RequestMethod.POST, value = "createShift", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addShift(@RequestBody ShiftDto shiftDto) {
        /* TODO: tu zrobić tylko walidację pól, tzn., jeśli coś jest nullem to return ResponseEntity(new ApiError("Niepoprawne dane wejściowe", HttpStatus.BAD_REQUEST)) */

        /* teraz tutaj wywołanie serwisu tylko, czyli: */
        try {
            // nie mapuje tutaj tego dto na encję, bo to robimy w warstwie serwisowej */
            shiftService.addShift(shiftDto);
        } catch(Exception e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllShiftsForWorker")
    public List<Shift> getListOfShiftsForWorkerId(int workerId){
        /* TODO: tutaj nie ma sensu tworzyć żadnego serwisu, czy workera, bo w nim i tak tylko wywoływałeś repozytorium, więc szkoda czasu */
        /*       możesz zostawić taką informację w komentarzu, że tutaj zdecydowałeś się, aby nie tworzyć pośredniej warstwy (serwisowej) bo
                 ona by nic nie wniosła. Warstwa serwisowa jest wtedy kiedy mamy jakąś logikę biznesową, robimy coś więcej niż operacja na bazie danych */

        return shiftRepository.getShiftsForWorker(workerId);
    }

    @RequestMapping(method = RequestMethod.GET, value="getAllShifts")
    public List<Shift> getListOfAllShifts(){
        return shiftRepository.findAll();
    }

}
