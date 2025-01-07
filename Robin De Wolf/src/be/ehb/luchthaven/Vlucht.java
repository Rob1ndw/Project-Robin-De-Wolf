package be.ehb.luchthaven;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.ArrayList;

public class Vlucht {
    private String flightCode;
    private String destination;
    private int economySeats;
    private int businessSeats;
    private ArrayList<klant> passengers;
    private ArrayList<String> staff;

    public Vlucht(String flightCode, String destination, int economySeats, int businessSeats) {
        this.flightCode = flightCode;
        this.destination = destination;
        this.economySeats = economySeats;
        this.businessSeats = businessSeats;
        this.passengers = new ArrayList<>();
        this.staff = new ArrayList<>();
    }

    public String getFlightCode() {
        return flightCode;
    }

    public boolean addPassenger(klant passenger, String seatClass) {
        if (seatClass.equalsIgnoreCase("economy") && economySeats > 0) {
            economySeats--;
            passengers.add(passenger);
            return true;
        } else if (seatClass.equalsIgnoreCase("business") && businessSeats > 0) {
            businessSeats--;
            passengers.add(passenger);
            return true;
        }
        return false; // No seats available
    }

    public void assignStaff(String staffName) {
        staff.add(staffName);
    }

    public ArrayList<klant> getPassengers() {
        return passengers;
    }

    @Override
    public String toString() {
        return "Vluchtcode: " + flightCode + ", bestemming: " + destination +
                ", Economy: " + economySeats + ", Business: " + businessSeats;
    }
}

