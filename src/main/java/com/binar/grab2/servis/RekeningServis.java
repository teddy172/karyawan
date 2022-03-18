package com.binar.grab2.servis;

import com.binar.grab2.models.Rekening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
public interface RekeningServis {
    public Map insert(Rekening obj, Long idkaryawan);

    public Map Perbaharui(Rekening obj, Long idkaryawan);

    public Map Hapus(Long idRekening);

    public Map getAll(Integer size, Integer page);

    public Map findByNama(String nama, Integer page, Integer size);

    Page<Rekening> findByNamaLike(String nama, Pageable pageable);
}
