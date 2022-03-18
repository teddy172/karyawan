package com.binar.grab2.servis;
import com.binar.grab2.models.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
public interface KaryawanServis {
    public Map insert(Karyawan obj);

    public Map Perbaharui(Karyawan obj);

    public Map Hapus(Long idKaryawan);

    public Map getAll(Integer size, Integer page);

    public Map findByNama(String nama, Integer page, Integer size);

    Page<Karyawan> findByNamaLike(String nama, Pageable pageable);
}
