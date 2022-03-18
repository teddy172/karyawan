package com.binar.grab2.util;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TemplateResponse {
    public Map templateSukses( Object objek){
        Map map = new HashMap();
        map.put("data", objek);
        map.put("message", "sukses");
        map.put("status", "200");
        return map;
    }
    public Map templateError( Object objek){
        Map map = new HashMap();
        map.put("data", objek);
        map.put("message", "sukses");
        map.put("status", "400");
        return map;
    }
    public boolean chekNull(Object obj){
        if(obj == null){
            return true;
        }
        return  false;
    }

    public Map notFound(String username_sudah_ada) {
        return null;
    }
}