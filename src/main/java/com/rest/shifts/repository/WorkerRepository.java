package com.rest.shifts.repository;

import com.rest.shifts.domain.Shift;
import com.rest.shifts.domain.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Integer> {
    @Override
    List<Worker> findAll();
    @Override
    Worker save(Worker worker);
}
