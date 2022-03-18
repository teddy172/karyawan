package com.binar.grab2.controller;

//import com.binar.grab.dao.request.KaryawanRequest;
import com.binar.grab2.models.Karyawan;
import com.binar.grab2.repository.KaryawanRepository;
import com.binar.grab2.servis.KaryawanRestTemplateService;
import com.binar.grab2.util.TemplateRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restApi/karyawan")
public class KaryawanRestTemplateController {

        @Autowired
        public KaryawanRestTemplateService karyawanRestTemplateService;

        @Autowired
        public KaryawanRepository karyawanRepository;

        @Autowired
        public TemplateRespon templateRespon;

        @PostMapping("/add")
        public ResponseEntity<Map> save(@RequestBody Karyawan obj) {
            Map oby = karyawanRestTemplateService.insert(obj);
            return new ResponseEntity<Map>(oby, HttpStatus.OK);
        }

        @PutMapping("/update")
        public ResponseEntity<Map> update(@RequestBody Karyawan objModel ) {
            Map map = karyawanRestTemplateService.update(objModel);
            return new ResponseEntity<Map>(map, HttpStatus.OK);
        }


        @DeleteMapping("/Hapus/{idkaryawan}")
        public ResponseEntity<Map> delete(@PathVariable(value = "idkaryawan") Long idkaryawan) {
            Map map = karyawanRestTemplateService.delete(idkaryawan);
            return new ResponseEntity<Map>(map, HttpStatus.OK);
        }

        @GetMapping("/listPage")
        public ResponseEntity<Map> getList(){
            Map map = new HashMap();
            map.put("data", karyawanRepository.findAll());
            map.put("code", "200");
            map.put("status","succes");
            return new ResponseEntity<Map>(map, HttpStatus.OK);
        }
}
