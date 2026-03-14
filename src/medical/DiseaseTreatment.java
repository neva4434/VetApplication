package medical;

public class DiseaseTreatment extends MedicalOperation {

    public DiseaseTreatment(String description, double cost) {
        super(description, cost);
    }

    @Override
    public void printDetails() {
        System.out.println("[TREATMENT] " + description + " | Cost: " + cost + " TL");
    }

    @Override
    public void printReport() {
        System.out.println("Generating treatment report for: " + description);
    }
}