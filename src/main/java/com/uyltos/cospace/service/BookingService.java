package com.uyltos.cospace.service;

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
                () -> new IllegalArgumentException("Пользователь с таким ID не найден!"));

        spaceRepository.findById(booking.getSpace().getId()).orElseThrow(
                () -> new IllegalArgumentException("Рабочее место с таким ID не найдено!"));

        Long spaceId = booking.getSpace().getId();
        if (bookingRepository.isBusy(spaceId, booking.getStartTime(), booking.getEndTime())) {
            throw new IllegalArgumentException("Это рабочее место уже занято на выбранное время!");
        }

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Пользователь с таким ID не найден!");
        }

        return bookingRepository.findByUserId(userId);
    }

    public void cancelBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new IllegalArgumentException("Бронирования с таким ID не найдено!");
        }

        bookingRepository.deleteById(bookingId);
    }

    public Booking updateBooking(Long id, Booking newBookingData) {
        if (!bookingRepository.existsById(id)) {
            throw new IllegalArgumentException("Бронирования с таким ID не существует!");
        }

        Long spaceId = newBookingData.getSpace().getId();

        if (bookingRepository.isBusyForUpdate(spaceId, newBookingData.getStartTime(), newBookingData.getEndTime(), id)) {
            throw new IllegalStateException("Это рабочее место уже занято на выбранное время!");
        }

        newBookingData.setId(id);
        return bookingRepository.save(newBookingData);
    }
}
