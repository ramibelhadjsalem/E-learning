package com.example.Elearning.Security.Twilio;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class SmsService {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    private final SmsSender smsSender;
    @Autowired
    public SmsService(SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }


    public String ConfirmAccout(String phoneNumber){
        StringBuilder sb = new StringBuilder(8);
        for(int i = 0; i < 8; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        String message ="form elearning application ,Your confirmation code is : "+sb;
        SmsRequest smsRequest = new SmsRequest(phoneNumber,message);

        smsSender.sendSms(smsRequest);
        return String.valueOf(sb);
    }

    public String generateNewPassword(String phoneNumber){
        StringBuilder sb = new StringBuilder(12);
        for(int i = 0; i < 12; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        String message ="form elearning application ,Your new password is : "+sb;
        SmsRequest smsRequest = new SmsRequest(phoneNumber,message);

        smsSender.sendSms(smsRequest);
        return String.valueOf(sb);
    }
}
