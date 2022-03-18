package com.binar.grab2.controller;

import com.binar.grab2.repository.KaryawanRepository;
import com.binar.grab2.models.Karyawan;
import com.binar.grab2.servis.KaryawanServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/binar/karyawan")
public class KaryawanController {
    @Autowired
    public KaryawanServis karyawanServis;

    @Autowired
    public KaryawanRepository karyawanRepository;

    //untuk menampilkan
    @GetMapping("/listPage")
    public ResponseEntity<Map> getList(){
        Map map = new HashMap();
        map.put("data", karyawanRepository.findAll());
        map.put("code", "200");
        map.put("status","succes");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Map> save(@RequestBody Karyawan objmodel
    ){
        Map save = karyawanServis.insert(objmodel);
        return new ResponseEntity<Map>(save, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Karyawan objmodel
    ) {
        Map map = karyawanServis.Perbaharui(objmodel);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id
    ){
        Map map = karyawanServis.Hapus(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
}
