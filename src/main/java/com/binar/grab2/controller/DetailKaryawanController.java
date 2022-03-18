package com.binar.grab2.controller;

import com.binar.grab2.models.DetailKaryawan;
import com.binar.grab2.repository.DetailKaryawanRepository;
import com.binar.grab2.servis.DetailKaryawanServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/binar/detailkaryawan")
public class DetailKaryawanController {
    @Autowired
    public DetailKaryawanServis detailKaryawanServis;
    @Autowired
    public DetailKaryawanRepository detailKaryawanRepository;

    //untuk menampilkan
    @GetMapping("/listPage")
    public ResponseEntity<Map> getList(){
        Map map = new HashMap();
        map.put("data", detailKaryawanRepository.findAll());
        map.put("code", "200");
        map.put("status","succes");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/add/{idkaryawan}")
    public ResponseEntity<Map> save(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                    @RequestBody DetailKaryawan detailKaryawan){
        Map map = new HashMap();
        Map obj = detailKaryawanServis.insert(detailKaryawan, idkaryawan);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update/{idkaryawan}")
    public ResponseEntity<Map> update(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                      @RequestBody DetailKaryawan detailKaryawan){
        Map obj = detailKaryawanServis.update(detailKaryawan, idkaryawan);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id){
        Map map = detailKaryawanServis.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

}
