package com.mypackage.servesmart.service;

import com.google.zxing.WriterException;
import com.mypackage.servesmart.model.Booking;
import com.mypackage.servesmart.model.Role;
import com.mypackage.servesmart.model.User;
import com.mypackage.servesmart.repository.BookingRepository;
import com.mypackage.servesmart.repository.UserRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private QRCodeService qrCodeService;

    public String bookMeal(Long userId, boolean lunch, boolean dinner) throws WriterException, IOException {
        User student = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        if (!lunch && !dinner) {
            return "❌ Please select at least one meal!";
        }
        Booking booking = new Booking(student, lunch, dinner, LocalDate.now(), null);
        if(lunch) booking.setLunchStatus("Opted");
        if(dinner) booking.setDinnerStatus("Opted");
        booking = bookingRepository.save(booking);
        String bookingId = booking.getBookingId();
        byte[] qrImage = qrCodeService.generateQRCodeImage(bookingId);
        String base64QR = Base64.getEncoder().encodeToString(qrImage);
        booking.setQrCode(base64QR);
        bookingRepository.save(booking);
        emailService.sendEmailWithQRCode(student.getEmail(), base64QR);
        return "🎉 Booking Successful! QR Code sent to email.";
    }

    public List<Booking> getPastBookings(Long userId) {
        User student = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return bookingRepository.findByStudent(student);
    }

}
