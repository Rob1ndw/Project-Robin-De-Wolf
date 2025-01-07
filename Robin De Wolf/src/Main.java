//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import be.ehb.luchthaven.Vlucht;
import be.ehb.luchthaven.klant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<klant> passengers = new ArrayList<>();
    private static final ArrayList<Vlucht> flights = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- RobinAirMiles System ---");
            System.out.println("1. Maak nieuwe passagier aan");
            System.out.println("2. Maak nieuwe vlucht");
            System.out.println("3. Maak nieuwe ticket");
            System.out.println("4. Passagier aan boord van de vlucht");
            System.out.println("5. personeel toe aan vlucht");
            System.out.println("6. Print vlucht info");
            System.out.println("7. Sluit");
            System.out.print("Kies een optie: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createPassenger(scanner);
                case 2 -> createFlight(scanner);
                case 3 -> createTicket(scanner);
                case 4 -> boardPassenger(scanner);
                case 5 -> assignStaff(scanner);
                case 6 -> printFlightInfo(scanner);
                case 7 -> running = false;
                default -> System.out.println("Ongeldige. Probeer het opnieuw..");
            }
        }
        scanner.close();
    }

    private static void createPassenger(Scanner scanner) {
        System.out.print("Vul naam in: ");
        String name = scanner.nextLine();
        System.out.print("Vul paspoort nummer in: ");
        String passport = scanner.nextLine();
        passengers.add(new klant(name, passport));
        System.out.println("Klant succesvol aangemaakt!");
    }

    private static void createFlight(Scanner scanner) {
        System.out.print("Vul vlucht code in: ");
        String code = scanner.nextLine();
        System.out.print("Vul bestemming in: ");
        String destination = scanner.nextLine();
        System.out.print("vul aantal economy stoels: ");
        int economy = scanner.nextInt();
        System.out.print("Vil aantal business stoels: ");
        int business = scanner.nextInt();
        flights.add(new Vlucht(code, destination, economy, business));
        System.out.println("Vlucht succesvol aangemaakt!");
    }

    private static void createTicket(Scanner scanner) {
        System.out.print("Vul klant paspoort numemr in: ");
        String passport = scanner.nextLine();
        klant passenger = passengers.stream()
                .filter(p -> p.getKaartNummer().equals(passport))
                .findFirst()
                .orElse(null);
        if (passenger == null) {
            System.out.println("Klant niet gevonden!");
            return;
        }

        System.out.print("Vul vlucht code in: ");
        String flightCode = scanner.nextLine();
        Vlucht flight = flights.stream()
                .filter(f -> f.getFlightCode().equals(flightCode))
                .findFirst()
                .orElse(null);
        if (flight == null) {
            System.out.println("Vluch niet gevonden!");
            return;
        }

        System.out.print("Vul stoel class (economy/business): ");
        String seatClass = scanner.nextLine();
        if (flight.addPassenger(passenger, seatClass)) {
            System.out.println("Ticket aangemaakt en klant toegevoegd aan vlucht!");
        } else {
            System.out.println("Geen stoel beschikbaar " + seatClass + " class.");
        }
    }

    private static void boardPassenger(Scanner scanner) {
        System.out.print("vul vlucht code in: ");
        String flightCode = scanner.nextLine();
        Vlucht flight = flights.stream()
                .filter(f -> f.getFlightCode().equals(flightCode))
                .findFirst()
                .orElse(null);
        if (flight == null) {
            System.out.println("Vlucht niet gevonden!");
            return;
        }

        System.out.print("Vul klant paspoort nummer in: ");
        String passport = scanner.nextLine();
        klant passenger = passengers.stream()
                .filter(p -> p.getKaartNummer().equals(passport))
                .findFirst()
                .orElse(null);
        if (passenger == null) {
            System.out.println("Klant niet gevonden!");
            return;
        }

        System.out.print("Vul stoel class (economy/business): ");
        String seatClass = scanner.nextLine();
        if (flight.addPassenger(passenger, seatClass)) {
            System.out.println("kalnt is succesvol aan boord!");
        } else {
            System.out.println("Geen stoel beschikbaar in " + seatClass + " class.");
        }
    }

    private static void assignStaff(Scanner scanner) {
        System.out.print("Vul vlucht code in: ");
        String flightCode = scanner.nextLine();
        Vlucht flight = flights.stream()
                .filter(f -> f.getFlightCode().equals(flightCode))
                .findFirst()
                .orElse(null);
        if (flight == null) {
            System.out.println("Vlucht niet gevonden!");
            return;
        }

        System.out.print("Vul personeel naam in: ");
        String staffName = scanner.nextLine();
        flight.assignStaff(staffName);
        System.out.println("Persoon succesvol toegevoegd!");
    }

    private static void printFlightInfo(Scanner scanner) {
        System.out.print("Vul vlucht code in: ");
        String flightCode = scanner.nextLine();
        Vlucht flight = flights.stream()
                .filter(f -> f.getFlightCode().equals(flightCode))
                .findFirst()
                .orElse(null);
        if (flight == null) {
            System.out.println("Vlucht niet gevonden!");
            return;
        }

        try (FileWriter writer = new FileWriter(flightCode + "_info.txt")) {
            writer.write("Vlucht Info:\n");
            writer.write(flight.toString() + "\n");
            writer.write("Klant:\n");
            for (klant p : flight.getPassengers()) {
                writer.write(p.toString() + "\n");
            }
            System.out.println("Vlucht info opgeslagen!");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Probleem met de file.");
        }

    }
}
