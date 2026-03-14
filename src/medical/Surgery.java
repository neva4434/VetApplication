package medical;

public class Surgery extends MedicalOperation {
    public Surgery(String description, double cost) {
        super(description, cost);
    }

    @Override
    public void printDetails() {
        System.out.println("[SURGERY] " + description + " | Cost: " + cost + " TL");
    }

    @Override
    public void printReport() {
        System.out.println("Generating surgery report...");
    }
}