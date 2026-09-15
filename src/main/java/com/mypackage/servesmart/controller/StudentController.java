package com.mypackage.servesmart.controller;

import com.google.zxing.WriterException;
import com.mypackage.servesmart.dto.BookingRequest;
import com.mypackage.servesmart.model.Booking;
import com.mypackage.servesmart.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/book-meal")
    public String bookMeal(@RequestBody BookingRequest request) throws WriterException, IOException {
        return studentService.bookMeal(request.getUserId(), request.isLunch(), request.isDinner());
    }

    @GetMapping("/past-bookings/{userId}")
    public List<Booking> getPastBookings(@PathVariable Long userId) {
        return studentService.getPastBookings(userId);
    }
}
