package com.uyltos.cospace.service;

import com.uyltos.cospace.exception.BookingConflictException;
import com.uyltos.cospace.exception.ResourceNotFoundException;
import com.uyltos.cospace.model.Booking;
import com.uyltos.cospace.repository.BookingRepository;
import com.uyltos.cospace.repository.SpaceRepository;
import com.uyltos.cospace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final UserRepository userRepository;
    private final SpaceRepository spaceRepository;
    private final BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        userRepository.findById(booking.getUser().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Пользователь с таким ID не найден!"));

        spaceRepository.findById(booking.getSpace().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Рабочее место с таким ID не найдено!"));

        if (bookingRepository.isBusy(booking.getSpace().getId(), booking.getStartTime(), booking.getEndTime())) {
            throw new BookingConflictException("Это рабочее место уже занято на выбранное время!");
        }

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Пользователь с таким ID не найден!");
        }
        return bookingRepository.findByUserId(userId);
    }

    public void cancelBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new ResourceNotFoundException("Бронирования с таким ID не найдено!");
        }
        bookingRepository.deleteById(bookingId);
    }

    public Booking updateBooking(Long id, Booking newBookingData) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Бронирования с таким ID не существует!");
        }

        if (bookingRepository.isBusyForUpdate(newBookingData.getSpace().getId(), newBookingData.getStartTime(), newBookingData.getEndTime(), id)) {
            throw new BookingConflictException("Это рабочее место уже занято на выбранное время!");
        }

        newBookingData.setId(id);
        return bookingRepository.save(newBookingData);
    }
}