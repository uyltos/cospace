package com.uyltos.cospace.repository;

import com.uyltos.cospace.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = """
            SELECT EXISTS (
            SELECT 1 FROM booking
            WHERE space_id = :spaceId AND start_time < :endTime AND end_time > :startTime
            )
           """, nativeQuery = true)
    boolean isBusy(@Param("spaceId") Long spaceId,
                   @Param("startTime") LocalDateTime startTime,
                   @Param("endTime") LocalDateTime endTime);

    List<Booking> findByUserId(Long userId);

    @Query(value = """
            SELECT EXISTS (
            SELECT 1 FROM booking
            WHERE space_id = :spaceId
                AND id != :currentBookingId
                AND start_time < :endTime
                AND end_time > :startTime
            )
            """, nativeQuery = true)
    boolean isBusyForUpdate(@Param("spaceId") Long spaceId,
                            @Param("startTime") LocalDateTime startTime,
                            @Param("endTime") LocalDateTime endTime,
                            @Param("currentBookingId") Long currentBookingId);
}