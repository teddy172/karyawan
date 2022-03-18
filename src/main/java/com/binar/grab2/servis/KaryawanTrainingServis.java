package com.binar.grab2.servis;
import com.binar.grab2.models.KaryawanTraining;


import java.util.Map;
public interface KaryawanTrainingServis {
    public Map insert (KaryawanTraining obj,Long idkaryawan,Long idtraining);

    public Map update (KaryawanTraining obj,Long idkaryawan,Long idtraining);

    public Map delete(Long idKaryawanTraining);

    public Map getAll(Integer size, Integer page);

}
