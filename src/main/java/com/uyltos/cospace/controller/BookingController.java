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
        Booking savedBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsUserId(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId, @RequestBody Booking newBookingData) {
        Booking savedNewBooking = bookingService.updateBooking(bookingId, newBookingData);
        return ResponseEntity.ok(savedNewBooking);
    }
}
