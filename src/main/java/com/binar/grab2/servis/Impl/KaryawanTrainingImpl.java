package com.binar.grab2.servis.Impl;

import com.binar.grab2.models.*;
import com.binar.grab2.servis.KaryawanTrainingServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.binar.grab2.models.KaryawanTraining;
import com.binar.grab2.repository.KaryawanRepository;
import com.binar.grab2.repository.TrainingRepository;
import com.binar.grab2.repository.KaryawanTrainingRepository;
import com.binar.grab2.util.TemplateRespon;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KaryawanTrainingImpl implements KaryawanTrainingServis {
    @Autowired
    KaryawanTrainingRepository karyawanTrainingRepository;
    @Autowired
    TrainingRepository trainingRepository;
    @Autowired
    KaryawanRepository karyawanRepository;
    @Autowired
    TemplateRespon templateRespon;


    @Override
    public Map insert(KaryawanTraining obj, Long idkaryawan, Long idtraining) {
        try{
            if (templateRespon.chekNull(obj.getTanggal())){
                return templateRespon.templateError("tanggal training harus ada");
            }
            Karyawan chekId= karyawanRepository.getbyID(idkaryawan);
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id karyawan tidak terdaftar");
            }
            Training chekIdTrain = trainingRepository.getbyID(idtraining);
            if (templateRespon.chekNull(chekIdTrain)){
                return templateRespon.templateError("id training tidak terdaftar");
            }
            //dosave
            obj.setKaryawan(chekId);
            obj.setTraining(chekIdTrain);
            KaryawanTraining dosave = karyawanTrainingRepository.save(obj);
            return templateRespon.templateSukses(obj);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map update(KaryawanTraining obj, Long idkaryawan, Long idtraining) {
        try{
            if (templateRespon.chekNull(obj.getId())){
                return templateRespon.templateError("id karyawan training tidak boleh kosong");
            }
            KaryawanTraining chekId= karyawanTrainingRepository.getbyID(obj.getId());
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id karyawan training tidak terdaftar");
            }
            if (templateRespon.chekNull(idkaryawan)){
                return templateRespon.templateError("id karyawan tidak boleh kosong");
            }
            Karyawan chekIdkaryawan = karyawanRepository.getbyID(idkaryawan);
            if (templateRespon.chekNull(chekIdkaryawan)){
                return templateRespon.templateError("id karyawan tidak terdaftar");
            }
            if (templateRespon.chekNull(idtraining)){
                return templateRespon.templateError("id training tidak boleh kosong");
            }
            Training chekIdtraining = trainingRepository.getbyID(idtraining);
            if (templateRespon.chekNull(chekIdtraining)){
                return templateRespon.templateError("id training tidak terdaftar");
            }
            //update
            chekId.setTanggal(obj.getTanggal());
            KaryawanTraining dosave = karyawanTrainingRepository.save(obj);
            return templateRespon.templateSukses(dosave);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map delete(Long idKaryawanTraining) {
        try {
            if (templateRespon.chekNull(idKaryawanTraining)){
                return templateRespon.templateError("id karyawan training harus ada");
            }
            KaryawanTraining obj = karyawanTrainingRepository.getbyID(idKaryawanTraining);
            if (templateRespon.chekNull(obj)){
                return templateRespon.templateError("id karyawan training tidak terdaftar");
            }
            karyawanTrainingRepository.delete(obj);
            return templateRespon.templateSukses("data berhasil di hapus");
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map getAll(Integer size, Integer page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<KaryawanTraining> list = karyawanTrainingRepository.getAllData(show_data);
            return templateRespon.templateSukses(list);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

}
