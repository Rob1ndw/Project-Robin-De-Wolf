package be.ehb.luchthaven;

public class Ticket {
    private klant passenger;
    private Vlucht flight;
    private String seatClass;

    public Ticket(klant passenger, Vlucht flight, String seatClass) {
        this.passenger = passenger;
        this.flight = flight;
        this.seatClass = seatClass;
    }

    @Override
    public String toString() {
        return "Ticket voor " + passenger.getNaam() + " op vlucht " + flight.getFlightCode() +
                " in " + seatClass + " class.";
    }
}

