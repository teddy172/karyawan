package com.binar.grab2.controller;


import com.binar.grab2.config.Config;
import com.binar.grab2.Dao.RegisterModel;
import com.binar.grab2.models.oauth.User;
import com.binar.grab2.repository.oauth.UserRepository;
import com.binar.grab2.servis.UserService;
import com.binar.grab2.servis.email.EmailSender;
import com.binar.grab2.util.EmailTemplate;
import com.binar.grab2.util.SimpleStringUtils;
import com.binar.grab2.util.TemplateRespon;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/user-register/")
public class Register {
    @Autowired
    private UserRepository userRepository;

    Config config = new Config();

    @Autowired
    public UserService serviceReq;

    @Value("${expired.token.password.minute:}")//FILE_SHOW_RUL
    private int expiredToken;

    @Autowired
    public TemplateRespon templateCRUD;
    @PostMapping("/register")
    public ResponseEntity<Map> saveRegisterManual(@RequestBody RegisterModel objModel) throws RuntimeException {
        Map map = new HashMap();

        User user = userRepository.checkExistingEmail(objModel.getEmail());
        if (null != user) {
            return new ResponseEntity<Map>(templateCRUD.notFound("Username sudah ada"), HttpStatus.OK);

        }
        map = serviceReq.registerManual(objModel);

        Map sendOTP = sendEmailegister(objModel);

        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @Autowired
    public EmailTemplate emailTemplate;

    @Autowired
    public EmailSender emailSender;

    // Step 2: sendp OTP berupa URL: guna updeta enable agar bisa login:
    @PostMapping("/send-otp")//send OTP
    public Map sendEmailegister(@RequestBody RegisterModel user) {
        String message = "Thanks, please check your email for activation.";

        if (user.getEmail() == null) return templateCRUD.templateError("No email provided");
        User found = userRepository.findOneByUsername(user.getEmail());
        if (found == null) return templateCRUD.notFound("Email not found"); //throw new BadRequest("Email not found");

        String template = emailTemplate.getRegisterTemplate();
        if (StringUtils.isEmpty(found.getOtp())) {
            User search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();

            found.setOtp(otp);
            found.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername1() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",  otp);
            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername1() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",  found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "Register", template);
        return templateCRUD.templateSukses(message);
    }

    @PutMapping("update")
    public Map verif(@RequestBody RegisterModel user,
                     @RequestParam() String otp) {
        try{
            if (user.getEmail() == null){
                return templateCRUD.templateError("tidak ada email yang tersedia");
            }
            User found = userRepository.findOneByUsername(user.getEmail());
            if (found == null){
                return templateCRUD.notFound("Email tidak ditemukan");
            }
            User checkOTP = userRepository.findOneByOTP(otp);
            if (templateCRUD.chekNull(checkOTP)){
                return templateCRUD.templateError("Masukan OTP yang benar");
            }
            checkOTP.setEnabled(true);
            checkOTP.setOtpExpiredDate(null);
            checkOTP.setOtp(null);
            User verif = userRepository.save(checkOTP);
            return templateCRUD.templateSukses("berhasil merubah");
        }catch (Exception e){
            return templateCRUD.templateError(e);
        }
    }

}