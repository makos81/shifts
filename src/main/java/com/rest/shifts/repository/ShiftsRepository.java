package com.rest.shifts.repository;

import com.rest.shifts.domain.Shift;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftsRepository extends CrudRepository<Shift, Integer> {
    @Override
    List<Shift> findAll();
    Optional<Shift> findByWorkerId(int workerID);
    @Override
    Shift save(Shift shift);
    @Query
    Shift getActiveShiftForWorker(@Param("ID") int id);
    @Query
    List<Shift> getShiftsForWorker(@Param("ID") int id);
}
