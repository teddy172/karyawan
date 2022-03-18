package com.binar.grab2.servis.Impl;

import com.binar.grab2.models.Karyawan;
import com.binar.grab2.models.Rekening;
import com.binar.grab2.repository.KaryawanRepository;
import com.binar.grab2.repository.RekeningRepository;
import com.binar.grab2.servis.RekeningServis;
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
public class RekeningImpl implements RekeningServis {

    @Autowired
    RekeningRepository rekeningRepository;

    public static final Logger log = LoggerFactory.getLogger(RekeningImpl.class);

    @Autowired
    KaryawanRepository karyawanRepository;
    @Autowired
    TemplateRespon templateRespon;

    @Override
    public Map insert(Rekening obj, Long idkaryawan) {
        try{
            if (templateRespon.chekNull(obj.getNama())){
                return templateRespon.templateError("nama harus ada");
            }
            if (templateRespon.chekNull(obj.getJenis())){
                return templateRespon.templateError("jenis rekening apa?");
            }
            if (templateRespon.chekNull(obj.getNomer())){
                return templateRespon.templateError("nomer rekening harus ada");
            }
            //untuk cek id karyawan ada tidak
            Karyawan chekId = karyawanRepository.getbyID(idkaryawan);
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id karyawan tidak terdaftar");
            }

            //dosave
            obj.setKaryawan(chekId);
            Rekening rekeningSave = rekeningRepository.save(obj);
            return templateRespon.templateSukses(obj);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map Perbaharui(Rekening obj, Long idkaryawan) {
        try {
            if (templateRespon.chekNull(idkaryawan)){
                return templateRespon.templateError("id karyawan harus ada");
            }
            Karyawan chekIdKaryawan = karyawanRepository.getbyID(obj.getId());
            if (templateRespon.chekNull(chekIdKaryawan)){
                return templateRespon.templateError("id karyawan tidak terdaftar");
            }
            if (templateRespon.chekNull(obj.getId())){
                return templateRespon.templateError("id rekening harus ada");
            }
            Rekening chekId = rekeningRepository.getbyID(obj.getId());
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id rekening tidak terdaftar");
            }
            chekId.setNama(obj.getNama());
            chekId.setJenis(obj.getJenis());
            chekId.setNomer(obj.getNomer());
            Rekening dosave = rekeningRepository.save(chekId);
            return templateRespon.templateSukses(dosave);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map Hapus(Long idRekening) {
        try{
            if (templateRespon.chekNull(idRekening)){
                return templateRespon.templateError("id rekening harus ada ");
            }
            Rekening chekId = rekeningRepository.getbyID(idRekening);
            if (templateRespon.chekNull(chekId)){
                return templateRespon.templateError("id rekening tidak terdaftar");
            }

            //delete
            rekeningRepository.delete(chekId);
            return templateRespon.templateSukses("berhasil menghapus");
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map getAll(Integer size, Integer page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Rekening> list = rekeningRepository.getAllData(show_data);
            return templateRespon.templateSukses(list);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map findByNama(String nama, Integer page, Integer size) {
        try{
            Pageable show_data = PageRequest.of(page, size);
            Page<Rekening> list = rekeningRepository.findByNama(nama, show_data);
            return templateRespon.templateSukses(list);
        }catch (Exception e){
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Page<Rekening> findByNamaLike(String nama, Pageable pageable) {
        try {
            Page<Rekening> list = rekeningRepository.findByNamaLike("'%" + nama + "%'", pageable);
            return list;
        }catch (Exception e){
            log.error("ada error di method findByNamaLike:" + e);
            return null;
        }
    }
}
