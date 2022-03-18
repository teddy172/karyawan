package com.binar.grab2.servis.Impl;

import com.binar.grab2.models.Karyawan;
import com.binar.grab2.repository.KaryawanRepository;
import com.binar.grab2.servis.KaryawanServis;
import com.binar.grab2.util.TemplateRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

@Service
public class KaryawanImpl implements KaryawanServis {

@Autowired
public KaryawanRepository karyawanRepository;

public static final Logger log = LoggerFactory.getLogger(KaryawanImpl.class);

@Autowired
TemplateRespon templateRespon;


    @Override
    public Map insert(Karyawan obj) {
        try{
            if (templateRespon.chekNull(obj.getNama())){
                return templateRespon.templateError("nama tidak boleh kosong");
            }
            if (templateRespon.chekNull(obj.getAlamat())){
                return templateRespon.templateError("alamat tidak boleh kosong");
            }
            if (templateRespon.chekNull(obj.getJk())){
                return templateRespon.templateError("jk tidak boleh kosong");
            }
            if (templateRespon.chekNull(obj.getStatus())){
                return templateRespon.templateError("status tidak boleh kosong");
            }
            //untuk menyimpan
            Karyawan saveObj = karyawanRepository.save(obj);
            return templateRespon.templateSukses(saveObj);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map Perbaharui(Karyawan obj) {
        try{
            if (templateRespon.chekNull(obj.getId())){
                return templateRespon.templateError("id tidak boleh kosong");
            }
            Karyawan chekId = karyawanRepository.getbyID(obj.getId());
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id karyawan tidak terdaftar");
            }
            //untuk menyimpan
            chekId.setNama(obj.getNama());
            chekId.setJk(obj.getJk());
            chekId.setAlamat(obj.getAlamat());
            chekId.setStatus(obj.getStatus());
            Karyawan dosave = karyawanRepository.save(chekId);
            return templateRespon.templateSukses(dosave);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map Hapus(Long idKaryawan) {
        try{
            if (templateRespon.chekNull(idKaryawan)){
                return templateRespon.templateError("id karyawan tidak boleh kosong");
            }
            Karyawan chekId = karyawanRepository.getbyID(idKaryawan);
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id karyawan tidak ditemukan");
            }
            //untuk menghapus
            karyawanRepository.delete(chekId);
            return templateRespon.templateSukses("berhasil menghapus" + chekId.getNama());
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map getAll(Integer size, Integer page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Karyawan> list = karyawanRepository.getAllData(show_data);
            return templateRespon.templateSukses(list);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map findByNama(String nama, Integer page, Integer size) {
        try{
            Pageable show_data = PageRequest.of(page, size);
            Page<Karyawan> list = karyawanRepository.findByNama(nama, show_data);
            return templateRespon.templateSukses(list);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Page<Karyawan> findByNamaLike(String nama, Pageable pageable) {
        try {
            Page<Karyawan> list = karyawanRepository.findByNamaLike("'%" + nama + "%'", pageable);
            return list;
        }catch (Exception e){
            log.error("ada error di method findByNamaLike:" + e);
            return null;
        }
    }
}
