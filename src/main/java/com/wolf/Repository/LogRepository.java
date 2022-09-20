package com.wolf.Repository;

import com.wolf.Domain.LogItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Jirka on 03.01.2017.
 */

public interface LogRepository extends CrudRepository<LogItem, Integer>{
    @Query("SELECT l FROM LogItem l WHERE l.timestamp > :ts")
    public List<LogItem> findByTS(@Param("ts") Date timestamp);
}
