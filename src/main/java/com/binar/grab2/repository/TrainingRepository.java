package com.binar.grab2.repository;

import com.binar.grab2.models.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends PagingAndSortingRepository<Training, Long>{
    @Query("select c from Training c")
    Page<Training> getAllData(Pageable pageable);

    @Query("select c from Training c where c.id = :idtraining")//titik dua penunjuk parameter
    public Training getbyID(@Param("idtraining")Long id);
}
