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

}
