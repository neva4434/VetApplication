package prescription;

import people.Veterinarian;
import animal.Animal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Prescription {

    private Veterinarian doctor;
    private Animal patient;
    private Medicine medicine;
    private Dosage dosage;
    private Date date;

    public Prescription(Veterinarian doctor,
                        Animal patient,
                        Medicine medicine,
                        Dosage dosage) {
        this.doctor = doctor;
        this.patient = patient;
        this.medicine = medicine;
        this.dosage = dosage;
        this.date = new Date();
    }

    public Veterinarian getDoctor() {
        return doctor;
    }

    public Animal getPatient() {
        return patient;
    }

    // ✅ BURASI
    public void printToFile() {

        String filePath = "prescriptions.txt";

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(this.toString());

        } catch (IOException e) {
            System.out.println("❌ File write error.");
        }
    }

    @Override
    public String toString() {
        String docName = (doctor != null) ? doctor.getName() : "Unknown Vet";

        // GÜVENLİK KONTROLLERİ: Eğer hasta veya sahibi yoksa program çökmesin
        String patName = "Unknown Patient";
        String ownerTc = "Unknown TC";
        String chip = "Unknown Chip";

        if (patient != null) {
            patName = patient.getName();
            // Sahibini kontrol et
            if (patient.getOwner() != null) {
                ownerTc = patient.getOwner().getTc();
            }
            // Çipini kontrol et
            if (patient.getChip() != null) {
                chip = patient.getChip().getChipNumber();
            }
        }

        return "=== PRESCRIPTION ===\n" +
                "Owner TC: " + ownerTc + "\n" +
                "Doctor: " + docName + "\n" +
                "Patient: " + patName + "\n" +
                "Chip: " + chip + "\n" +
                "Medicine: " + medicine + "\n" +
                "Dosage: " + dosage + "\n" +
                "------------------\n";
    }
}
