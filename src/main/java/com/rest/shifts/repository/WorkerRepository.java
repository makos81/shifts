package com.rest.shifts.repository;

import com.rest.shifts.domain.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WorkerRepository extends CrudRepository<Worker, Integer> {
}
