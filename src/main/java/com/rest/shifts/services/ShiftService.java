package com.rest.shifts.services;

import com.rest.shifts.common.TwoShiftsInRowException;
import com.rest.shifts.common.WorkerNotFoundException;
import com.rest.shifts.domain.Shift;
import com.rest.shifts.common.ShiftDto;
import com.rest.shifts.mapper.ShiftsMapper;
import com.rest.shifts.repository.ShiftRepository;
import com.rest.shifts.repository.WorkerRepository;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {

    private ShiftRepository shiftRepository;
    private WorkerRepository workerRepository;
    private ShiftsMapper shiftsMapper;

    public void addShift(ShiftDto shiftDto) {
        Shift shift = shiftsMapper.mapToShift(shiftDto);

        /* TODO: ogólnie to bym robił że to Worker _ma_ zmianę, a nie zmiana workera. */
        /*       zrobiłbym metodę addShiftFor(WorkerDto workerDto) i ten WorkerDto, a tym samym encja Worker miałaby referencje Shift
        *        i można by tworzyć ładne metody biznesowe tak jak w przykładzie który Ci przesłałem */

        int workerId = shift.getWorkerId();
        try {
            workerRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        } catch (WorkerNotFoundException e) {
            e.printStackTrace();
        }
        Shift currentShift = shiftRepository.getActiveShiftForWorker(workerId);

        /* TODO: ogólnie to nie ma nic złego żeby takie reguły biznesowe kodować w serwisie (tzw. Transaction Script, czy jakoś tak */
        /*       ładniej by było jednak, aby były to metody ładnie zenkapsulowane w encjach, ale spokojnie się wybronisz, powiesz, że to gówno
                 aplikacyjka, więc nie chciałeś przemarszczać
         */
        if (currentShift == null) {
            shiftRepository.save(shiftsMapper.mapToShift(shiftDto));
        } else {
            long hours = ChronoUnit.HOURS.between(shiftDto.getTo(), currentShift.getFrom());
            if (hours < 8) {
                throw new TwoShiftsInRowException();
            } else {
                shiftRepository.save(shiftsMapper.mapToShift(shiftDto));
            }
        }
    }
}
