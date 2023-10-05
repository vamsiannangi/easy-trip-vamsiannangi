package com.driver.controllers;


import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.service.AirportService;
import com.driver.service.FlightService;
import com.driver.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AirportController {

    private AirportService airportService;
    private final FlightService flightService;
private PassengerService passengerService;

@Autowired
    public AirportController(AirportService airportService, FlightService flightService, PassengerService passengerService) {
        this.airportService = airportService;
        this.flightService = flightService;
        this.passengerService = passengerService;
    }

    @PostMapping("/add_airport")
    public String addAirport(@RequestBody Airport airport){
       airportService.addAirport(airport);
            return "SUCCESS";
    }

    @GetMapping("/get-largest-aiport")
    public String getLargestAirportName(){
    return airportService.getLargestAirport();
    }

    @GetMapping("/get-shortest-time-travel-between-cities")
    public double getShortestDurationOfPossibleBetweenTwoCities(@RequestParam("fromCity") City fromCity, @RequestParam("toCity")City toCity){
    return flightService.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity);
    }

    @GetMapping("/get-number-of-people-on-airport-on/{date}")
    public int getNumberOfPeopleOn(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
            ,@RequestParam("airportName")String airportName){
    return airportService.getNumberOfPeopleOn(date,airportName);

    }

    @GetMapping("/calculate-fare")
    public int calculateFlightFare(@RequestParam("flightId")Integer flightId){

        //Calculation of flight prices is a function of number of people who have booked the flight already.
        //Price for any flight will be : 3000 + noOfPeopleWhoHaveAlreadyBooked*50
        //Suppose if 2 people have booked the flight already : the price of flight for the third person will be 3000 + 2*50 = 3100
        //This will not include the current person who is trying to book, he might also be just checking price

        return flightService.calculateFlightFare(flightId);

    }


    @PostMapping("/book-a-ticket")
    public String bookATicket(@RequestParam("flightId")Integer flightId,@RequestParam("passengerId")Integer passengerId){

        //If the numberOfPassengers who have booked the flight is greater than : maxCapacity, in that case :
        //return a String "FAILURE"
        //Also if the passenger has already booked a flight then also return "FAILURE".
        //else if you are able to book a ticket then return "SUCCESS"
  return flightService.bookTicket(flightId,passengerId);
    }

    @PutMapping("/cancel-a-ticket")
    public String cancelATicket(@RequestParam("flightId")Integer flightId,@RequestParam("passengerId")Integer passengerId){

        //If the passenger has not booked a ticket for that flight or the flightId is invalid or in any other failure case
        // then return a "FAILURE" message
        // Otherwise return a "SUCCESS" message
        // and also cancel the ticket that passenger had booked earlier on the given flightId
return flightService.cancelTicket(flightId,passengerId);
    }


    @GetMapping("/get-count-of-bookings-done-by-a-passenger/{passengerId}")
    public int countOfBookingsDoneByPassengerAllCombined(@PathVariable("passengerId")Integer passengerId){

        //Tell the count of flight bookings done by a passenger: This will tell the total count of flight bookings done by a passenger :
        return passengerService.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    @PostMapping("/add-flight")
    public String addFlight(@RequestBody Flight flight){
        flightService.addFlight(flight);
        //Return a "SUCCESS" message string after adding a flight.
       return "SUCCESS";
    }


    @GetMapping("/get-aiportName-from-flight-takeoff/{flightId}")
    public String getAirportNameFromFlightId(@PathVariable("flightId")Integer flightId){
        return airportService.getAirportNameFromFlightId(flightId);
    }


    @GetMapping("/calculate-revenue-collected/{flightId}")
    public int calculateRevenueOfAFlight(@PathVariable("flightId")Integer flightId){
      return flightService.calculateRevenueOfAFlight(flightId);

    }


    @PostMapping("/add-passenger")
    public String addPassenger(@RequestBody Passenger passenger){
   passengerService.addPassenger(passenger);
       return "SUCCESS";
    }


}
