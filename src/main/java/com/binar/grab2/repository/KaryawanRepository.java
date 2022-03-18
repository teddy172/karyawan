package com.binar.grab2.repository;

import com.binar.grab2.models.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanRepository extends PagingAndSortingRepository<Karyawan, Long>{
    @Query("select c from Karyawan c")
    Page<Karyawan> getAllData(Pageable pageable);

    @Query("select c from Karyawan c where c.id = :id")//titik dua penunjuk parameter
    public Karyawan getbyID(@Param("id")Long id);

    @Query("select c from Karyawan c where c.nama = :nama")
    Page<Karyawan> findByNama(String nama, Pageable pageable);

    public Page<Karyawan> findByNamaLike(String nama , Pageable pageable);
}
