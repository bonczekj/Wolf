package com.wolf.Repository;

import com.wolf.Domain.Parameter;
import com.wolf.Domain.Texts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Jirka on 04.01.2017.
 */
public interface TextsRepository extends CrudRepository<Texts, Integer>{
    @Query("SELECT t FROM wolf_texts t WHERE t.deu = :txt")
    public Texts findByDEU(@Param("txt") String txt);
}
