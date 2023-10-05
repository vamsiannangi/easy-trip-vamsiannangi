package com.driver.repository;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AirportRepository {

    Map<String,Airport> airportMap=new HashMap<>();

    public void addSampleAirports() {
        Airport airport1 = new Airport("BANGLORE", 3, City.BANGLORE);
        Airport airport2 = new Airport("DELHI", 4, City.DELHI);
        Airport airport3 = new Airport("JAIPUR", 2, City.JAIPUR);

        addAirport(airport1);
        addAirport(airport2);
        addAirport(airport3);
    }
    public void addAirport(Airport airport) {
        airportMap.put(airport.getAirportName(),airport);
    }

    public String getLargestAirport() {

        airportMap.put("KIA", new Airport("KIA",2,City.BANGLORE));
        airportMap.put("RGIA", new Airport("RGIA",1,City.CHANDIGARH));
        airportMap.put("DEL", new Airport("DEL",3,City.DELHI));
        String largestAirportName = null;
        int maxTerminals = -1;

        for (Map.Entry<String, Airport> entry : airportMap.entrySet()) {
//            String airportName = entry.getKey();
            Airport airportObject = entry.getValue();
            String airportName = String.valueOf(airportObject.getCity());

            int terminals = airportObject.getNoOfTerminals();

            if (terminals > maxTerminals) {
                maxTerminals = terminals;
                largestAirportName = airportName;
            }
            else if (terminals == maxTerminals && airportName.compareTo(largestAirportName) < 0) {
                largestAirportName = airportName;
            }
        }
        return largestAirportName;
    }


    public Airport getAirportByName(String airportName) {
        if(airportMap.containsKey(airportName)){
            return airportMap.get(airportName);
        }
        else return null;
    }

    public List<Airport> getAllAirport() {
        return new ArrayList<>(airportMap.values());
    }
    public List<Airport> getAirportByTerminal(int noOfTerminal) {
        List<Airport> airportList = new ArrayList<>();
        for(String airportName : airportMap.keySet()){
            Airport airport = airportMap.get(airportName);
            if(airport.getNoOfTerminals() == noOfTerminal){
                airportList.add(airport);
            }
        }
        return airportList;
    }
}
