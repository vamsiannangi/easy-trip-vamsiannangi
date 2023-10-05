package com.driver.repository;

import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PassengerRepository {

    HashMap<Integer,Passenger> passengerMap=new HashMap<>();
    Map<Integer, List<Integer>> flightPassengerMap = new HashMap<>();

    public void addSamplePassengers() {
        Passenger passenger1 = new Passenger(1, "vamsi@gmail.com", "vamsi", 30);
        Passenger passenger2 = new Passenger(2, "passenger2@gmail.com", "name2", 25);
        Passenger passenger3 = new Passenger(3, "passenger3@gmail.com", "name3", 40);

        addPassenger(passenger1);
        addPassenger(passenger2);
        addPassenger(passenger3);
    }

    public void addPassenger(Passenger passenger) {
        passengerMap.put(passenger.getPassengerId(),passenger);
    }




    public Passenger getPassengerById(Integer passengerId) {
        if(passengerMap.containsKey(passengerId)){
            return passengerMap.get(passengerId);
        }
        return null;
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        int count = 0;
        for (Integer flightId : flightPassengerMap.keySet()){
            List<Integer> passengerList = flightPassengerMap.get(flightId);
            for(Integer pId : passengerList){
                if(pId == passengerId){
                    count++;
                }
            }
        }
        return count;
    }

    public boolean flightBookings(Integer flightId, Integer passengerId) {
        if (flightPassengerMap.containsKey(flightId)) {
            List<Integer> passengers = flightPassengerMap.get(flightId);
            return passengers.contains(passengerId);
        }
        return false;
    }
    public String bookTicket(Integer flightId, Integer passengerId) {
        if (!flightPassengerMap.containsKey(flightId)) {
            // If the flightId doesn't exist, create a new entry for it
            flightPassengerMap.put(flightId, new ArrayList<>());
        }

        // Check if the passengerId is already booked for the flight
        List<Integer> passengers = flightPassengerMap.get(flightId);
        if (passengers.contains(passengerId)) {
            return "FAILURE: Passenger is already booked for this flight";
        }
        passengers.add(passengerId);
        return "SUCCESS";
    }

    public List<Integer> getBookingPassengersByFlightIds(Integer flightId) {
        return flightPassengerMap.get(flightId);
    }

}
