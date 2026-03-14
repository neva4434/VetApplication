package prescription;

public class Dosage {
    // int yerine String yapıyoruz ki "50mg" yazılabilsin.
    private String amount;    // Örn: "50mg", "1 Tablet"
    private String frequency; // Örn: "Günde 2 kez"

    public Dosage(String amount, String frequency) {
        this.amount = amount;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return amount + " - " + frequency;
    }
}