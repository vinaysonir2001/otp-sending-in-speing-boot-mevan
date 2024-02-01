package com.Otp_Sending.controller;

import com.Otp_Sending.Service.OtpService;
import com.Otp_Sending.payload.OtpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {

    private OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }
// http://localhost:8080/send-otp
    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody OtpRequest otpRequest) {
        if (otpService.sendOtp(otpRequest.getPhoneNumber(), otpRequest.getOtp())) {
            return "OTP sent successfully!";
        } else {
            return "Failed to send OTP. Please try again later.";
        }
    }
}

