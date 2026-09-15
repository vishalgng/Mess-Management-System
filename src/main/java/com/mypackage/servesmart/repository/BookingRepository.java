package com.mypackage.servesmart.repository;

import com.mypackage.servesmart.model.Booking;
import com.mypackage.servesmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByStudent(User student);
    List<Booking> findByDate(LocalDate date);
    Optional<Booking> findById(String bookingId);
}