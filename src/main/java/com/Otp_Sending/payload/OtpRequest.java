package com.Otp_Sending.payload;

import lombok.Data;

@Data
public class OtpRequest {
    private String phoneNumber;
    private String otp;

    // Getters and setters
}



