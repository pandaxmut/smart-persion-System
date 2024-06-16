package com.edu.smartpersionsys.controller;

import org.springframework.http.ResponseEntity;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.edu.smartpersionsys.service.UserService;
import com.edu.smartpersionsys.utils.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserService userService;
    private Map<String, String> verificationCodes = new HashMap<>();
    @GetMapping("/bind")
    public ModelAndView showPhone() {
        return new ModelAndView("bind");
    }

    @PostMapping("/sendSms")
    public String sendSms(@RequestBody Map<String, String> requestBody) {
        String phoneNumber = requestBody.get("phoneNumber");
        String code = smsService.generateVerificationCode();
        System.out.println("发送短信到: " + phoneNumber);
        System.out.println("验证码: " + code);
        System.out.println(phoneNumber);
        try {
            SendSmsResponse response = smsService.sendSms(phoneNumber, code);
            System.out.println("阿里云响应: " + response.getCode() + " - " + response.getMessage());
            if ("OK".equals(response.getCode())) {
                verificationCodes.put(phoneNumber, code);
                return "短信发送成功";
            } else {
                return "短信发送失败：" + response.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "短信发送失败：" + e.getMessage();
        }
    }

    @PostMapping("/verifyCode")
    public String verifyCode(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");
        String code = request.get("code");
        int userId = Integer.parseInt(request.get("userId"));
        System.out.println("userId:"+userId);
        String storedCode = verificationCodes.get(phoneNumber);

        if (storedCode != null && storedCode.equals(code)) {
            //将手机号绑定到表中
            boolean flag = userService.bindPhone(userId,phoneNumber);
            if (flag){
                System.out.println("login");
                return "login";
            }else{
                System.out.println("bind");
                return "bind";
            }
        } else {
            return "验证码错误";
        }
    }
}
