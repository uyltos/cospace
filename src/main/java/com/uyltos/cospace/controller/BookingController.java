package com.uyltos.cospace.controller;

import com.uyltos.cospace.model.Booking;
import com.uyltos.cospace.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsUserId(userId));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId, @RequestBody Booking newBookingData) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId, newBookingData));
    }
}