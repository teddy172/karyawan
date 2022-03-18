package com.binar.grab2.Junit;

import com.binar.grab2.models.Karyawan;
import com.binar.grab2.servis.Impl.KaryawanRestTemplateImpl;
import com.binar.grab2.servis.KaryawanRestTemplateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.security.auth.login.AppConfigurationEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KaryawanJunitTestRestTemplate {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private KaryawanRestTemplateService karyawanRestTemplateService;
    @Autowired
    private KaryawanRestTemplateImpl karyawanRestTemplate;

    @Test
    public void insertKaryawan(){
        Karyawan req = new Karyawan();
        req.setNama("bagus");
        req.setJk("laki-laki");
        req.setAlamat("banyuwangi");
        req.setStatus("pegawai tetap");

//        Long idSupp = 1L;
        Map map = karyawanRestTemplateService.insert(req);
        assertEquals("400", map.get("status"));
        if (map.get("status").equals("400")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }
    }

    @Test
    public void updateBarang() {
        Karyawan req = new Karyawan();
        req.setId(16L);
        req.setNama("salsa");
        req.setJk("perempuan");
        req.setAlamat("palembang");
        req.setStatus("pegawai tetap");

//        Long idSupp = 1L;
        Map map = karyawanRestTemplateService.update(req);
        assertEquals("400", map.get("status"));
        if (map.get("status").equals("400")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }
    }

    @Test
    public void listKaryawan() {
        Map map = karyawanRestTemplateService.getAll(10, 0);
        assertEquals("400", map.get("status"));
        if (map.get("status").equals("400")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }
    }

    @Test
    public void deleteKaryawan(){
        Map map = karyawanRestTemplateService.delete(16l);
        assertEquals("400", map.get("status"));
        String status = (String) map.get("status");
        if(status.equals("400")){
            System.out.println("berhasil menghapus");
        }else {
            System.out.println("terjadi error");
        }
    }

}
