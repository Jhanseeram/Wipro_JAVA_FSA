package com.wipro.repository;

import com.wipro.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    // Find bookings by passenger email
    List<Booking> findByPassengerEmail(String passengerEmail);
    
    // Find bookings by status
    List<Booking> findByStatus(Booking.BookingStatus status);
    
    // Find booking by booking ID
    Optional<Booking> findByBookingId(String bookingId);
    
    // Find bookings by passenger name
    List<Booking> findByPassengerName(String passengerName);
}