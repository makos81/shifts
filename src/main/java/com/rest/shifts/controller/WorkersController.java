package com.rest.shifts.controller;

import com.rest.shifts.common.WorkerNotFoundException;
import com.rest.shifts.domain.Worker;
import com.rest.shifts.common.WorkerDto;
import com.rest.shifts.mapper.WorkerMapper;
import com.rest.shifts.repository.DbServiceWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/workers")
@RequiredArgsConstructor
public class WorkersController {

    /* bym zrobił analogicznie do ShiftsController - wywaliłbym te workery jeśli one nie robią nic poza wywołaniem repozytorium */
    /* jeśli chcesz tworzyć klasy serwisowe to nie w warstwie infrastrukturalnej, którą jest na pewno pakiet repository. Serwisy
       są warstwą aplikacyjną i należą de facto do domeny [core aplikacji można postrzegać jako model domenowy (encje biznesowe) + właśnie serwisy aplikacyjne, które wywołują
       warstwę tę niższą, czyli warstwę bazodanową, tutaj: repozytoria
     */
    @Autowired
    DbServiceWorker dbServiceWorker;
    @Autowired
    WorkerMapper workerMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getWorker")
    public Worker getWorker(int workerId) throws WorkerNotFoundException {
        return dbServiceWorker.getById(workerId).orElseThrow(WorkerNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createWorker", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addWorker(@RequestBody WorkerDto workerDto){
        Worker worker = workerMapper.mapToWorker(workerDto);
        dbServiceWorker.save(worker);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getWorkers")
    public List<WorkerDto> getWorkers(){
        List<Worker> workerList = dbServiceWorker.getAllWorkers();
        return workerMapper.mapToTaskDtoList(workerList);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateWorker")
    public WorkerDto updateWorker(@RequestBody WorkerDto workerDto){
        Worker worker = workerMapper.mapToWorker(workerDto);
        Worker savedWorker = dbServiceWorker.save(worker);
        return workerMapper.mapToWorkerDto(worker);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteWorker")
    public void deleteWorker(int workerId){
        dbServiceWorker.deleteWorker(workerId);
    }
}
