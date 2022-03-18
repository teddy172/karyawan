package com.binar.grab2.servis.Impl;

import com.binar.grab2.models.Karyawan;
import com.binar.grab2.repository.KaryawanRepository;
import com.binar.grab2.servis.DetailKaryawanServis;
import com.binar.grab2.models.DetailKaryawan;
import com.binar.grab2.repository.DetailKaryawanRepository;
import org.springframework.stereotype.Service;
import com.binar.grab2.util.TemplateRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Map;

@Service
public class DetailKaryawanImpl implements DetailKaryawanServis {

    @Autowired
    TemplateRespon templateRespon;

    @Autowired
    DetailKaryawanRepository detailKaryawanRepository;

    @Autowired
    KaryawanRepository karyawanRepository;

    @Override
    public Map insert(DetailKaryawan detailKaryawan, Long idkaryawan) {
        try {
            if (templateRespon.chekNull(detailKaryawan.getNik())){
                return templateRespon.templateError("nik karyawan tidak boleh kosong");
            }
            if (templateRespon.chekNull(detailKaryawan.getNpwp())){
                return templateRespon.templateError("npwp karyawan tidak boleh kosong");
            }
            Karyawan chekIdkaryawan = karyawanRepository.getbyID(idkaryawan);
            if (templateRespon.chekNull(chekIdkaryawan)){
                return templateRespon.templateError("id karyawan tidak tidak terdaftar");
            }

            //dosave
            detailKaryawan.setKaryawan(chekIdkaryawan);
            DetailKaryawan dosave = detailKaryawanRepository.save(detailKaryawan);
            return templateRespon.templateSukses(detailKaryawan);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map update(DetailKaryawan detailKaryawan, Long idkaryawan) {
        try {
            if (templateRespon.chekNull(detailKaryawan.getId())){
                return templateRespon.templateError("id detail karyawan tidak boleh kosong");
            }
            DetailKaryawan chekId = detailKaryawanRepository.getbyID(detailKaryawan.getId());
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id detail karyawan tidak terdaftar");
            }
            if (templateRespon.chekNull(idkaryawan)){
                return templateRespon.templateError("id karyawan harus ada");
            }
            Karyawan chekIdkaryawan = karyawanRepository.getbyID(idkaryawan);
            if (templateRespon.chekNull(chekIdkaryawan)){
                return templateRespon.templateError("id karyawan tidak terdaftar");
            }
            //update
            chekId.setNik(detailKaryawan.getNik());
            chekId.setNpwp(detailKaryawan.getNpwp());
            DetailKaryawan dosave = detailKaryawanRepository.save(detailKaryawan);
            return templateRespon.templateSukses(dosave);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map delete(Long detailkaryawan) {
        try {
            if (templateRespon.chekNull(detailkaryawan)){
                return templateRespon.templateError("id detail karyawan harus ada");
            }
            DetailKaryawan obj = detailKaryawanRepository.getbyID(detailkaryawan);
            if (templateRespon.chekNull(obj)){
                return templateRespon.templateError("id detail karyawan tidak terdaftar");
            }
            //delete
            detailKaryawanRepository.delete(obj);
            return templateRespon.templateSukses("data berhasil di hapus");
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map getAll(Integer size, Integer page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<DetailKaryawan> list = detailKaryawanRepository.getAllData(show_data);
            return templateRespon.templateSukses(list);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }
}
