package com.wolf.Repository;

import com.wolf.Domain.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Jirka on 04.01.2017.
 */
public interface ParameterRepository extends CrudRepository<Parameter, Integer>{
    @Query("SELECT p FROM parameters p WHERE p.id = :id")
    public Parameter findById(@Param("id") Integer id);
}
