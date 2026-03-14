package medical;

public class Vaccination extends MedicalOperation {
    private String vaccineType;

    public Vaccination(String description, double cost, String vaccineType) {
        // super(...) kısmında sadece 2 parametre olmalı
        super(description, cost);
        this.vaccineType = vaccineType;
    }

    @Override
    public void printDetails() {
        System.out.println("[VACCINATION] " + description + " | Type: " + vaccineType + " | Cost: " + cost + " TL");
    }

    @Override
    public void printReport() {
        System.out.println("Generating vaccination report for: " + vaccineType);
    }
}