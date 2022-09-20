package com.wolf.Repository;

import com.wolf.Domain.LogItemConv;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

/**
 * Created by Jirka on 03.01.2017.
 */

public interface LogItemConvRepository extends CrudRepository<LogItemConv, Integer>{
    @Query(nativeQuery = true)
    LogItemConv getLastTS();

    @Query("SELECT l FROM LogItemConv l WHERE l.timestamp = :ts")
    public LogItemConv findByTS(@Param("ts") Date timestamp);

    @Query("SELECT l FROM LogItemConv l order by l.timestamp")
    public List<LogItemConv> getAll();

    @Query("SELECT l FROM LogItemConv l WHERE l.timestamp BETWEEN :d1 and :d2 order by l.timestamp")
    public List<LogItemConv> getInterval(@Param("d1") Date d1, @Param("d2") Date d2);
}
