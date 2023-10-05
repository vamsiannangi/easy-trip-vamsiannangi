package com.driver.service;

import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.repository.FlightRepository;
import com.driver.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private FlightRepository flightRepository;
    public void addPassenger(Passenger passenger) {
        passengerRepository.addPassenger(passenger);
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
                passengerRepository.addSamplePassengers();
        return passengerRepository.countOfBookingsDoneByPassengerAllCombined(passengerId);

    }


//    public String bookATicket(Integer flightId, Integer passengerId) {
//        passengerRepository.addSamplePassengers();
//        flightRepository.addSampleFlights();
//        Flight flight = flightRepository.getFlightById(flightId);
//        Passenger passenger = passengerRepository.getPassengerById(passengerId);
//        if(flight == null || passenger == null) return "flight or passenger does not exist";
//
//        int numberOfPassengersBookedFlight = flightRepository.numberOfTicketForFlight(flightId);
//
//        if(numberOfPassengersBookedFlight >= flight.getMaxCapacity()){
//            return "FAILURE";
//        }
//
//        return  passengerRepository.bookATicket(flightId,passengerId);
//    }


}
