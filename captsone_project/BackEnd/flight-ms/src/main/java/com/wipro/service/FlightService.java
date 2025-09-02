package com.wipro.service;

import com.wipro.entity.Flight;
import com.wipro.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public Flight updateFlight(Long id, Flight flight) {
        Flight existingFlight = getFlightById(id);
        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setAircraftName(flight.getAircraftName());
        existingFlight.setRoute(flight.getRoute());
        existingFlight.setPrice(flight.getPrice());
        existingFlight.setDate(flight.getDate());
        return flightRepository.save(existingFlight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
