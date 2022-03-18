package com.binar.grab2.servis.Impl;

import com.binar.grab2.models.Training;
import com.binar.grab2.repository.TrainingRepository;
import com.binar.grab2.servis.TrainingServis;
import com.binar.grab2.util.TemplateRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrainingImpl implements TrainingServis{
    @Autowired
    public TrainingRepository trainingRepository;

    @Autowired
    public TemplateRespon templateRespon;

    @Override
    public Map insert(Training obj) {
        try {
            if (templateRespon.chekNull(obj.getTema())){
                return templateRespon.templateError("tema training harus ada");
            }
            if (templateRespon.chekNull(obj.getPengajar())){
                return templateRespon.templateError("nama pengajar harus ada");
            }
            //untuk menyimpan
            Training dosave = trainingRepository.save(obj);
            return templateRespon.templateSukses(dosave);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map update(Training obj) {
        try {
            if (templateRespon.chekNull(obj.getId())){
                return templateRespon.templateError("id training harus ada");
            }
            Training chekId = trainingRepository.getbyID(obj.getId());
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id tidak terdaftar");
            }

            //untuk menyimpan
            Training dosave = trainingRepository.save(obj);
            return templateRespon.templateSukses(dosave);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map delete(Long idTraining) {
        try {
            if (templateRespon.chekNull(idTraining)){
                return templateRespon.templateError("id training tidak boleh kosong");
            }
            //cek ada tidak
            Training chekId = trainingRepository.getbyID(idTraining);
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id training tidak terdaftar");
            }
            //hapus
            trainingRepository.delete(chekId);
            return templateRespon.templateSukses("berhasil menghapus");
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map getAll(Integer size, Integer page) {
        try{
            Pageable show_data = PageRequest.of(size, page);
            Page<Training> list = trainingRepository.getAllData(show_data);
            return templateRespon.templateSukses(list);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }
}
