package com.binar.grab2.servis;

import com.binar.grab2.models.DetailKaryawan;

import java.util.Map;
public interface DetailKaryawanServis {
    public Map insert(DetailKaryawan detailKaryawan, Long idkaryawan);

    public Map update(DetailKaryawan detailKaryawan, Long idkaryawan);

    public Map delete(Long detailkaryawan);

    public Map getAll(Integer size, Integer page);

}
