package com.binar.grab2.servis;

import com.binar.grab2.models.Karyawan;

import java.util.Map;

public interface KaryawanRestTemplateService {
    public Map insert(Karyawan obj);

    public Map update(Karyawan obj);

    public Map delete(Long karyawan);

    public Map getAll(int size, int page);
}
