package com.driver.service;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.repository.AirportRepository;
import com.driver.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AirportService {

    private AirportRepository airportRepository;
    private FlightRepository flightRepository;
    @Autowired
    public AirportService(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    public void addAirport(Airport airport) {
        airportRepository.addAirport(airport);
    }

    public String getLargestAirport() {
        return airportRepository.getLargestAirport();
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        // Calculate the total number of people who have flights on that day at a particular airport
        // This includes both the people who have come for a flight and who have landed at an airport after their flight
        airportRepository.addSampleAirports();
        flightRepository.addSampleFlights();
        Airport airport = airportRepository.getAirportByName(airportName);
        if (airport == null) return 0;
        City city = airport.getCity();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String givenDateString = dateFormat.format(date);

        List<Flight> flightList = flightRepository.getAllFlights();
        if (flightList == null || flightList.isEmpty()) return 0;

        List<Flight> flights = new ArrayList<>();

        for (Flight flight : flightList) {
            String flightDateString = dateFormat.format(flight.getFlightDate());

            if (givenDateString.equals(flightDateString) &&
                    (flight.getFromCity() == city || flight.getToCity() == city)) {
                flights.add(flight);
            }
        }

        int count = 0;
        for (Flight flight : flights) {
            count += flightRepository.numberOfTicketForFlight(flight.getFlightId());
        }
        return count;
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        airportRepository.addSampleAirports();
        flightRepository.addSampleFlights();
        Flight flight = flightRepository.getFlightById(flightId);
        if (flight == null) {
            return null; // Flight with the given ID does not exist
        }

        City fromCity = flight.getFromCity();
        List<Airport> airportList = airportRepository.getAllAirport();

        for (Airport airport : airportList) {
            if (airport.getCity() == fromCity) { // Use .equals() to compare enums
                return airport.getAirportName();
            }
        }
        return null; // Airport not found for the flight's departure city
    }
}
