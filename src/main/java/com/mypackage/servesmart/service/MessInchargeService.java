package com.mypackage.servesmart.service;

import com.mypackage.servesmart.dto.MealPostRequest;
import com.mypackage.servesmart.model.MealDetails;
import com.mypackage.servesmart.model.Booking;
import com.mypackage.servesmart.repository.MealRepository;
import com.mypackage.servesmart.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MessInchargeService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public void postMeal(MealPostRequest request) {
        MealDetails meal = new MealDetails();
        meal.setDate(LocalDate.now());
        meal.setMealType(request.getMealType());
        meal.setDescription(request.getDescription());
        mealRepository.save(meal);
    }

    public List<Booking> getTodayBookings() {
        return bookingRepository.findByDate(LocalDate.now());
    }

    @Transactional
    public String verifyQRCode(String bookingId) {
        bookingId = bookingId.trim(); // Remove spaces
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isEmpty()) {
            return "❌ Booking ID not found!";
        }
        Booking booking = bookingOpt.get();
        if (!booking.getDate().equals(LocalDate.now())) {
            return "❌ Invalid QR Code! Meal is not booked for today.";
        }
        if (booking.isLunch() && !booking.isLunchVerified()) {
            booking.setLunchVerified(true);
            bookingRepository.save(booking);
            return "✅ Lunch Verified Successfully!";
        }
        if (booking.isDinner() && !booking.isDinnerVerified()) {
            booking.setDinnerVerified(true);
            bookingRepository.save(booking);
            return "✅ Dinner Verified Successfully!";
        }
        return "✅ Meal already verified!";
    }

}