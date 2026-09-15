package com.mypackage.servesmart.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Random;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @Column(name = "booking_id", unique = true, nullable = false, length = 8)
    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User student;

    private boolean isLunch;  // Whether lunch is booked
    private boolean isDinner; // Whether dinner is booked
    private LocalDate date;

    @Lob
    @Column(length = 5000)
    private String qrCode;

    @Column(nullable = false)
    private boolean isLunchVerified = false; // Lunch verification status

    @Column(nullable = false)
    private boolean isDinnerVerified = false; // Dinner verification status

    @Column(nullable = false)
    private String lunchStatus = "Not Opted";  // New: Status for lunch

    @Column(nullable = false)
    private String dinnerStatus = "Not Opted"; // New: Status for dinner

    // Default constructor (JPA needs this)
    public Booking() {
        this.bookingId = generateBookingId();
    }

    // Parameterized constructor
    public Booking(User student, boolean isLunch, boolean isDinner, LocalDate date, String qrCode) {
        this.bookingId = generateBookingId();
        this.student = student;
        this.isLunch = isLunch;
        this.isDinner = isDinner;
        this.date = date;
        this.qrCode = qrCode;

        // Set status based on booking
        this.lunchStatus = isLunch ? "Opted" : "Not Opted";
        this.dinnerStatus = isDinner ? "Opted" : "Not Opted";
    }

    // Generate unique 8-character Booking ID
    private String generateBookingId() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public boolean isLunch() {
        return isLunch;
    }

    public void setLunch(boolean isLunch) {
        this.isLunch = isLunch;
    }

    public boolean isDinner() {
        return isDinner;
    }

    public void setDinner(boolean isDinner) {
        this.isDinner = isDinner;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public boolean isLunchVerified() {
        return isLunchVerified;
    }

    public void setLunchVerified(boolean isLunchVerified) {
        this.isLunchVerified = isLunchVerified;
        if (isLunchVerified) {
            this.lunchStatus = "Verified";  // Update status when verified
        }
    }

    public boolean isDinnerVerified() {
        return isDinnerVerified;
    }

    public void setDinnerVerified(boolean isDinnerVerified) {
        this.isDinnerVerified = isDinnerVerified;
        if (isDinnerVerified) {
            this.dinnerStatus = "Verified";  // Update status when verified
        }
    }

    public String getLunchStatus() {
        return lunchStatus;
    }

    public void setLunchStatus(String lunchStatus) {
        this.lunchStatus = lunchStatus;
    }

    public String getDinnerStatus() {
        return dinnerStatus;
    }

    public void setDinnerStatus(String dinnerStatus) {
        this.dinnerStatus = dinnerStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", student=" + student +
                ", isLunch=" + isLunch +
                ", isDinner=" + isDinner +
                ", date=" + date +
                ", qrCode='" + qrCode + '\'' +
                ", isLunchVerified=" + isLunchVerified +
                ", isDinnerVerified=" + isDinnerVerified +
                ", lunchStatus='" + lunchStatus + '\'' +
                ", dinnerStatus='" + dinnerStatus + '\'' +
                '}';
    }
}
