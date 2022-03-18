package com.binar.grab2.repository;

import com.binar.grab2.models.DetailKaryawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailKaryawanRepository extends PagingAndSortingRepository<DetailKaryawan, Long> {
        @Query("select c from DetailKaryawan c where c.id = :iddetailkaryawan")//titik dua penunjuk parameter
        public DetailKaryawan getbyID(@Param("iddetailkaryawan")Long id);

        @Query("select c from DetailKaryawan c")
        Page<DetailKaryawan> getAllData(Pageable pageable);
}
