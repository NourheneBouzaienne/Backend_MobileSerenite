package com.app.ClientService.dao;

import com.app.ClientService.models.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
    Optional<OtpVerification> findByNumTelAndOtp(String numTel, String otp);
    Optional<OtpVerification> findByNumTel(String numTel);
}