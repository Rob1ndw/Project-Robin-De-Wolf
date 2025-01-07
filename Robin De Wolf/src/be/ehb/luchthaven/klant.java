package be.ehb.luchthaven;

public class klant {
    private String name;
    private String passportNumber;

    public klant(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getNaam() {
        return name;
    }

    public String getKaartNummer() {
        return passportNumber;
    }

    @Override
    public String toString() {
        return "Naam: " + name + ", Passport: " + passportNumber;
    }
}
