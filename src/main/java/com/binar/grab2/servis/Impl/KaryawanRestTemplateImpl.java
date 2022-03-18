package com.binar.grab2.servis.Impl;

import com.binar.grab2.models.Karyawan;
import com.binar.grab2.repository.KaryawanRepository;
//import com.binar.grab2.repository.SupplierRepository;
import com.binar.grab2.servis.KaryawanRestTemplateService;
import com.binar.grab2.util.TemplateRespon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service //service wajib
public class KaryawanRestTemplateImpl implements KaryawanRestTemplateService {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    //1. call repository banrang dan supplierbi
    @Autowired
    public KaryawanRepository karyawanRepository;

    public static final Logger log = LoggerFactory.getLogger(KaryawanRestTemplateImpl.class);


//    @Autowired
//    public SupplierRepository supplierRepository;

    @Autowired
    public TemplateRespon templateRespon;


    @Override
    public Map insert(Karyawan obj) {
        try {
            if (templateRespon.chekNull(obj.getNama())) {
                return templateRespon.templateError("Nama is Requiered");
            }

            if (templateRespon.chekNull(obj.getAlamat())) {
                return templateRespon.templateError("Alamat is requiered");
            }

            if (templateRespon.chekNull(obj.getJk())){
                return templateRespon.templateError("jk is required");
            }

            if (templateRespon.chekNull(obj.getStatus())){
                return templateRespon.templateError("status is required");
            }

            //do save : yang kita ganti
            String url = "http://localhost:8082/api/v2/binar/karyawan/add";

            ResponseEntity<Map> result = restTemplateBuilder.build().postForEntity(url, obj, Map.class);

            return templateRespon.templateSukses(result.getBody());
        } catch (Exception e) {
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map update(Karyawan obj) {
        try {
            if (templateRespon.chekNull(obj.getId())) {
                return templateRespon.templateError("Id karyawan is requiered");
            }
            Karyawan karyawanId = karyawanRepository.getbyID(obj.getId());
            if (templateRespon.chekNull(karyawanId)) {
                return templateRespon.templateError("id karyawan not found");
            }

            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            String url = "http://localhost:8082/api/v2/binar/karyawan/update";
            HttpHeaders headers = new HttpHeaders();
            // set `content-type` header
            headers.setContentType(MediaType.APPLICATION_JSON);
            // set `accept` header
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            // build the request
            HttpEntity<Karyawan> entity = new HttpEntity<>(obj, headers);

            // send PUT request to update post with `id` 10
            ResponseEntity<Map> response = restTemplateBuilder.build().exchange(url, HttpMethod.PUT, entity, Map.class);

            // check response status code
            if (response.getStatusCode() == HttpStatus.OK) {
                return templateRespon.templateSukses(response.getBody());
            } else {
                System.out.println("eror");
                return templateRespon.templateError(response.getBody());
            }


        } catch (Exception e) {
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map delete(Long karyawan) {
        try {
            if (templateRespon.chekNull(karyawan)) {
                return templateRespon.templateError("Id Karyawans is required");
            }
            Karyawan idkaryawan = karyawanRepository.getbyID(karyawan);
            if (templateRespon.chekNull(idkaryawan)) {
                return templateRespon.templateError("id karyawan not found");
            }
            //            1. chek id karyawan
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "*/*");
            headers.set("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<String>(null, headers);

//            Long id = 5L;
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            ResponseEntity<Map> exchange = restTemplateBuilder.build().exchange("http://localhost:8082/api/v2/binar/karyawan/delete/"+karyawan , HttpMethod.DELETE, entity, Map.class);

            System.out.println("response   karyawan="+exchange.getBody());

            return templateRespon.templateSukses(exchange.getBody());

        } catch (Exception e) {
            return templateRespon.templateError(e);
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            final  String url = "http://localhost:8082/api/v2/binar/karyawan/listPage";
            Map<String, Object> params = new HashMap<>();
            params.put("size", 10);
            params.put("page", 0);
            HttpEntity<?> httpEntity  = new HttpEntity<>(httpHeaders);
            ResponseEntity<Map> result = restTemplateBuilder.build().exchange(url, HttpMethod.GET, httpEntity,Map.class, params);
            return templateRespon.templateSukses(result.getBody());
        } catch (Exception e) {
            log.error("ada eror di method getAll:" + e);
            return templateRespon.templateError(e);
        }
    }
}
