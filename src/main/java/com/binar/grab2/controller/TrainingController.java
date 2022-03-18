package com.binar.grab2.controller;

import com.binar.grab2.repository.TrainingRepository;
import com.binar.grab2.models.Training;
import com.binar.grab2.servis.TrainingServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/binar/training")
public class TrainingController {
    @Autowired
    public TrainingServis trainingServis;

    @Autowired
    public TrainingRepository trainingRepository;

    //untuk menampilakan
    @GetMapping("/listPage")
    public ResponseEntity<Map> getList(){
        Map map = new HashMap();
        map.put("data", trainingRepository.findAll());
        map.put("code", "200");
        map.put("status", "succes");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    //menambahkan
    @PostMapping("/add")
    public ResponseEntity<Map>save(@RequestBody Training objmodel)
    {
        Map save = trainingServis.insert(objmodel);
        return new ResponseEntity<Map>(save, HttpStatus.OK);
    }

    //update
    @PutMapping("/update")
    public ResponseEntity<Map>update(@RequestBody Training objmodel)
    {
        Map map = trainingServis.update(objmodel);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    //hapus
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id)
    {
        Map map = trainingServis.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
}
