package com.example.Elearning.Security.Twilio;


import com.example.Elearning.Security.Twilio.TwilioConfiguration;
import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TwilioINitiazer {

    private  final TwilioConfiguration twilioConfigration;

    @Autowired
    public TwilioINitiazer(TwilioConfiguration twilioConfigration) {
        this.twilioConfigration = twilioConfigration;
        Twilio.init(
               twilioConfigration.getAccountSid() ,
                twilioConfigration.getAuthToken()
        );
        log.info("twilio initialied ...");
    }
}
