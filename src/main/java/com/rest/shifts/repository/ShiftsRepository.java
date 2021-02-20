package com.rest.shifts.repository;

import com.rest.shifts.domain.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftsRepository extends CrudRepository<Shift, Integer> {
    @Override
    List<Shift> findAll();
    Optional<Shift> findByWorker_Id(int workerID);
    @Override
    Shift save(Shift shift);
}
