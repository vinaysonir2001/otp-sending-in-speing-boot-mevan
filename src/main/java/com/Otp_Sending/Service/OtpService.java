package com.Otp_Sending.Service;
import com.twilio.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OtpService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    @Autowired
    public OtpService(
            @Value("${twilio.accountSid}") String accountSid,
            @Value("${twilio.authToken}") String authToken,
            @Value("${twilio.phoneNumber}") String twilioPhoneNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.twilioPhoneNumber = twilioPhoneNumber;
    }

    public boolean sendOtp(String phoneNumber, String otp) {
        Twilio.init(accountSid, authToken);

        try {
            Message message = Message.creator(
                            new PhoneNumber(phoneNumber),
                            new PhoneNumber(twilioPhoneNumber),
                            "Your OTP is: " + otp)
                    .create();
            return true;
        } catch (ApiException e) {
            // Handle exception (e.g., invalid phone number, insufficient balance)
            e.printStackTrace();
            return false;
        }
    }
}
