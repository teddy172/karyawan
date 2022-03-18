package com.binar.grab2.controller;

import com.binar.grab2.models.KaryawanTraining;
import com.binar.grab2.repository.KaryawanRepository;
import com.binar.grab2.repository.KaryawanTrainingRepository;
import com.binar.grab2.servis.KaryawanTrainingServis;
import com.binar.grab2.util.TemplateRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v2/binar/karyawantraining")
public class KaryawanTrainingController {
    @Autowired
    TemplateRespon templateRespon;
    @Autowired
    KaryawanTrainingRepository karyawanTrainingRepository;
    @Autowired
    KaryawanTrainingServis karyawanTrainingServis;

    @GetMapping("/listPage")
    public ResponseEntity<Map> getlist(){
        Map map = new HashMap();
        map.put("data", karyawanTrainingRepository.findAll());
        map.put("code","200");
        map.put("status","succes");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Map> save(@RequestBody KaryawanTraining objModel,
                                    @RequestParam(required=false,name="id_karyawan") Long idkaryawan,
                                    @RequestParam(required = false, name = "id_training") Long idtraining){
        Map map = karyawanTrainingServis.insert(objModel, idkaryawan, idtraining);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody KaryawanTraining objModel,
                                      @RequestParam Long idkaryawan,
                                      @RequestParam Long idtraining){
        Map map1 = karyawanTrainingServis.update(objModel, idkaryawan, idtraining);
        return new ResponseEntity<Map>(map1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id){
        Map map2 = karyawanTrainingServis.delete(id);
        return new ResponseEntity<Map>(map2,HttpStatus.OK);
    }


}
