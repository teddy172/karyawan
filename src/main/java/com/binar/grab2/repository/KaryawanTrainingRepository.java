package com.binar.grab2.repository;

import com.binar.grab2.models.KaryawanTraining;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanTrainingRepository extends PagingAndSortingRepository<KaryawanTraining, Long>{
    @Query("select c from KaryawanTraining c")
    Page<KaryawanTraining> getAllData(Pageable pageable);

    @Query("select c from KaryawanTraining c where c.id = :idkaryawantraining")//titik dua penunjuk parameter
    public KaryawanTraining getbyID(@Param("idkaryawantraining")Long id);

//    public Page<KaryawanTraining> findByNamaTanggalTraining(String nama);
}
