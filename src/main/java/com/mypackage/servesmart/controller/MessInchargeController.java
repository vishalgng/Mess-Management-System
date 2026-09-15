package com.mypackage.servesmart.controller;

import com.mypackage.servesmart.dto.MealPostRequest;
import com.mypackage.servesmart.model.Booking;
import com.mypackage.servesmart.service.MessInchargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mess")
public class MessInchargeController {

    @Autowired
    private MessInchargeService messInchargeService;

    @PostMapping("/post-meal")
    public String postMeal(@RequestBody MealPostRequest request) {
        messInchargeService.postMeal(request);
        return "Meal details posted successfully!";
    }

    @GetMapping("/today-bookings")
    public List<Booking> getTodayBookings() {
        return messInchargeService.getTodayBookings();
    }

    @PostMapping("/verify-qr")
    public String verifyQR(@RequestParam String bookingId) {
        return messInchargeService.verifyQRCode(bookingId);
    }

}