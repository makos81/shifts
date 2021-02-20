package com.rest.shifts.repository;

import com.rest.shifts.domain.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ShiftsRepository extends CrudRepository<Shift, Integer> {

}
