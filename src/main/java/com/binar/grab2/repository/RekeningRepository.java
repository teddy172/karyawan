package com.binar.grab2.repository;

import com.binar.grab2.models.Rekening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeningRepository extends PagingAndSortingRepository<Rekening, Long> {
    @Query("select c from Rekening c")
    Page<Rekening> getAllData(Pageable pageable);

    @Query("select c from Rekening c where c.id = :id")//titik dua penunjuk parameter
    public Rekening getbyID(@Param("id")Long id);

    @Query("select c from Rekening c where c.nama = :nama")
    Page<Rekening> findByNama(String nama, Pageable pageable);

    public Page<Rekening> findByNamaLike(String nama , Pageable pageable);
}
