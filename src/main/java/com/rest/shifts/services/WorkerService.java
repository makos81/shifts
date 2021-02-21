package com.rest.shifts.services;

import com.rest.shifts.common.ShiftAlreadyDefined;
import com.rest.shifts.common.TwoShiftsInRowException;
import com.rest.shifts.common.WorkerNotFoundException;
import com.rest.shifts.domain.Shift;
import com.rest.shifts.common.ShiftDto;
import com.rest.shifts.domain.Worker;
import com.rest.shifts.mapper.ShiftsMapper;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

import com.rest.shifts.repository.ShiftRepository;
import com.rest.shifts.repository.WorkerRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    private ShiftRepository shiftRepository;
    private WorkerRepository workerRepository;
    private ShiftsMapper shiftsMapper;

    public List<Shift> getShiftsForWorker(int workerId) {
        Worker worker = workerRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        return worker.getShifts();
    }

    public void assignShiftToWorker(int workerId, int shiftId) {
        Shift currentShift = shiftRepository.findById(shiftId).orElseThrow(ShiftAlreadyDefined::new);
        ShiftDto currentShiftDto = shiftsMapper.mapToDto(currentShift);
        Worker worker = workerRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        List<Shift> shiftList = worker.getShifts();
        if (shiftList.size()==0) {
            shiftRepository.save(shiftsMapper.mapToShift(currentShiftDto));
        } else {
            shiftList.sort(Comparator.comparing(Shift::getTo));
            Shift latestShift = shiftList.get(shiftList.size()-1);
            ShiftDto latestShiftDto = shiftsMapper.mapToDto(latestShift);
            long hours = ChronoUnit.HOURS.between(latestShift.getTo(), latestShift.getFrom());
            if (hours < 8) {
                throw new TwoShiftsInRowException();
            } else {
                shiftRepository.save(shiftsMapper.mapToShift(latestShiftDto));
            }
        }
    }
}
