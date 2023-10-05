package com.driver.service;//package com.driver.service;
//
//import com.driver.model.City;
//import com.driver.model.Flight;
//import com.driver.model.Passenger;
//import com.driver.repository.FlightRepository;
//import com.driver.repository.PassengerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FlightService {
//
//    @Autowired
//    private FlightRepository flightRepository;
//    @Autowired
//    private PassengerRepository passengerRepository;
//    public void addFlight(Flight flight) {
//        flightRepository.addFlight(flight);
//    }
//
//    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
//        flightRepository.addSampleFlights(); // Populate the flightMap with sample flights
//        List<Flight> allFlights = flightRepository.getAllFlight();
//        double shortestDuration = -1;
//
//        for (Flight flight : allFlights) {
//            if (flight.getFromCity() == fromCity && flight.getToCity() == toCity) {
//                if (shortestDuration == -1 || flight.getDuration() < shortestDuration) {
//                    shortestDuration = flight.getDuration();
//                }
//                else if(shortestDuration>flight.getDuration()){
//                    shortestDuration=flight.getDuration();
//                }
//            }
//        }
//        return shortestDuration;
//    }
//
//
//    public int calculateFlightFare(Integer flightId) {
//
//        int numberOfPassengersBookedFlight = flightRepository.numberOfTicketForFlight(flightId);
//        int price = 3000 + (numberOfPassengersBookedFlight*50);
//        return price;
//    }
//
//
//    public int calculateRevenueOfAFlight(Integer flightId) {
//        flightRepository.addSampleFlights();
//        int price = calculateFlightFare(flightId);
//        int cancelBooking = flightRepository.getCancelBookings(flightId); // flight id se number of cancel booking correct nhi pta rha
//
//        int cancelPrice = (cancelBooking * 50);
//        return price - cancelPrice;
//    }
//
//    public String bookTicket(Integer flightId, Integer passengerId) {
//        //If the numberOfPassengers who have booked the flight is greater than : maxCapacity, in that case :
//        //return a String "FAILURE"
//        //Also if the passenger has already booked a flight then also return "FAILURE".
//        //else if you are able to book a ticket then return "SUCCESS"
//        flightRepository.addSampleFlights();
//        passengerRepository.addSamplePassengers();
//        int passengersInFlight=flightRepository.numberOfTicketForFlight(flightId);
//        Flight flight = flightRepository.getFlightById(flightId);
//
//        if(passengersInFlight>=flight.getMaxCapacity()){
//            return "maxCapacity reached";
//        }
//        boolean passengerAlreadyBooked=passengerRepository.flightBookings(flightId,passengerId);
//
//        if(passengerAlreadyBooked){
//            return "passenger already booked";
//        } else {
//           return passengerRepository.bookTicket(flightId,passengerId);
//        }
//    }
//
//    public String cancleTicket(Integer flightId, Integer passengerId) {
//        flightRepository.addSampleFlights();
//        passengerRepository.addSamplePassengers();
//        Flight flight = flightRepository.getFlightById(flightId);
//        Passenger passenger = passengerRepository.getPassengerById(passengerId);
//        if(flight == null && passenger == null) return "FAILURE null";
//
//        List<Integer> flightIds = flightRepository.getAllBookingsWithFlightId();
//
//        for(Integer fId : flightIds){
//            List<Integer> passengers = passengerRepository.getBookingPassengersByFlightIds(flightId);
//            if(passengers.contains(passengerId)){
//                return  flightRepository.cancelATicket(flightId,passengerId);
//
//            }
//            else {
//                return "FAILURE";
//            }
//        }
//
//        return "FAILURE";
//    }
//}
//



import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void addFlight(Flight flight) {
        flightRepository.addFlight(flight);
    }

    public int calculateFlightFare(Integer flightId) {
        // Calculate fare based on the number of passengers
        int numberOfPassengersBookedFlight = flightRepository.numberOfPassengersForFlight(flightId);
        int price = 3000 + (numberOfPassengersBookedFlight * 50);
        return price;
    }

    public String bookTicket(Integer flightId, Integer passengerId) {
        return flightRepository.bookTicket(flightId, passengerId);
    }

    public String cancelTicket(Integer flightId, Integer passengerId) {
        return flightRepository.cancelTicket(flightId, passengerId);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.getAllFlights();
    }
    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        flightRepository.addSampleFlights(); // Populate the flightMap with sample flights
        List<Flight> allFlights = flightRepository.getAllFlights();
        double shortestDuration = -1;

        for (Flight flight : allFlights) {
            if (flight.getFromCity() == fromCity && flight.getToCity() == toCity) {
                if (shortestDuration == -1 || flight.getDuration() < shortestDuration) {
                    shortestDuration = flight.getDuration();
                }
                else if(shortestDuration>flight.getDuration()){
                    shortestDuration=flight.getDuration();
                }
            }
        }
        return shortestDuration;
    }

        public int calculateRevenueOfAFlight(Integer flightId) {
        flightRepository.addSampleFlights();
        int price = calculateFlightFare(flightId);
        int cancelBooking = flightRepository.getCancelBookings(flightId); // flight id se number of cancel booking correct nhi pta rha

        int cancelPrice = (cancelBooking * 50);
        return price - cancelPrice;
    }
    // Other methods related to flights
}
