package com.binar.grab2.controller;

import com.binar.grab2.models.Rekening;
import com.binar.grab2.repository.RekeningRepository;
import com.binar.grab2.servis.RekeningServis;
import com.binar.grab2.util.TemplateRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/binar/rekening")
public class RekeningController {
    @Autowired
    RekeningRepository rekeningRepository;

    @Autowired
    RekeningServis rekeningServis;

    @Autowired
    TemplateRespon templateRespon;

    @GetMapping("/listPage")
    public ResponseEntity<Map> getlist(){
        Map map = new HashMap();
        map.put("data", rekeningRepository.findAll());
        map.put("code", "200");
        map.put("status","succes");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/add/{idkaryawan}")
    public ResponseEntity<Map> save(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                    @RequestBody Rekening objModel){
        Map map = new HashMap();
        Map obj1 = rekeningServis.insert(objModel, idkaryawan);
        return new ResponseEntity<Map>(obj1, HttpStatus.OK);
    }

    @PutMapping("/update/{idkaryawan}")
    public ResponseEntity<Map> update(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                      @RequestBody Rekening objModel){
        Map obj = rekeningServis.Perbaharui(objModel, idkaryawan);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id){
        Map map = rekeningServis.Hapus(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByBama(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String nama) {
        Map map = new HashMap();
        Page<Rekening> list = null;
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());//batasin roq

        if(nama != null && !nama.isEmpty()){
            list = rekeningRepository.findByNama("%"+nama+"%",show_data);
        }if (nama != null && !nama.isEmpty()){
            list = rekeningRepository.findByNamaLike("%"+nama+"%", show_data);
        }else{
            list = rekeningRepository.getAllData(show_data);
        }
        return new ResponseEntity<Map>(templateRespon.templateSukses(list), new HttpHeaders(), HttpStatus.OK);
    }
}
