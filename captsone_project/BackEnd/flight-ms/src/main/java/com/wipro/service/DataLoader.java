package com.wipro.service;

import com.wipro.entity.Flight;
import com.wipro.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (flightRepository.count() == 0) {
            loadSampleFlights();
        }
    }

    private void loadSampleFlights() {
        // Create sample flights
        Flight flight1 = new Flight();
        flight1.setFlightNumber("6E-234");
        flight1.setAircraftName("IndiGo A320");
        flight1.setRoute("Kolkata-Chennai");
        flight1.setPrice(5650.0);
        flight1.setDate(LocalDate.now().plusDays(1));

        Flight flight2 = new Flight();
        flight2.setFlightNumber("AI-763");
        flight2.setAircraftName("Air India Boeing 737");
        flight2.setRoute("Kolkata-Chennai");
        flight2.setPrice(5651.0);
        flight2.setDate(LocalDate.now().plusDays(1));

        Flight flight3 = new Flight();
        flight3.setFlightNumber("6E-723");
        flight3.setAircraftName("IndiGo A320");
        flight3.setRoute("Kolkata-Chennai");
        flight3.setPrice(5650.0);
        flight3.setDate(LocalDate.now().plusDays(2));

        Flight flight4 = new Flight();
        flight4.setFlightNumber("AI-567");
        flight4.setAircraftName("Air India Boeing 737");
        flight4.setRoute("Delhi-Mumbai");
        flight4.setPrice(6500.0);
        flight4.setDate(LocalDate.now().plusDays(1));

        Flight flight5 = new Flight();
        flight5.setFlightNumber("6E-445");
        flight5.setAircraftName("IndiGo A320");
        flight5.setRoute("Delhi-Mumbai");
        flight5.setPrice(6200.0);
        flight5.setDate(LocalDate.now().plusDays(1));

        Flight flight6 = new Flight();
        flight6.setFlightNumber("SG-892");
        flight6.setAircraftName("SpiceJet Boeing 737");
        flight6.setRoute("Mumbai-Bangalore");
        flight6.setPrice(4500.0);
        flight6.setDate(LocalDate.now().plusDays(1));

        // Save flights
        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);
        flightRepository.save(flight4);
        flightRepository.save(flight5);
        flightRepository.save(flight6);

        System.out.println("Sample flight data loaded successfully!");
    }
}