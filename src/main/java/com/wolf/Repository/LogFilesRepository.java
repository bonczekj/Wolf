package com.wolf.Repository;

import com.wolf.Domain.LogFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Jirka on 04.01.2017.
 */
public interface LogFilesRepository extends CrudRepository<LogFile, Integer>{
    @Query("SELECT l FROM LogFiles l WHERE l.fileName = :fileName")
    public LogFile findByFile(@Param("fileName") String fileName);
}
