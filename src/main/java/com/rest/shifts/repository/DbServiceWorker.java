package com.rest.shifts.repository;

import com.rest.shifts.domain.Shift;
import com.rest.shifts.domain.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceWorker {
    @Autowired
    WorkerRepository workerRepository;

    public List<Worker> getAllWorkers(){
        return workerRepository.findAll();
    }

    public Optional<Worker> getById(int workerId){
        return workerRepository.findById(workerId);
    }

    public Worker save(Worker worker){
        return workerRepository.save(worker);
    }

    public void deleteWorker(int workerId){
        workerRepository.deleteById(workerId);
    }

    public boolean existWorker(int workerId){
        return workerRepository.existsById(workerId);
    }
}
