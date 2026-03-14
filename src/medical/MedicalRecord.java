package medical;

import prescription.Prescription;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MedicalRecord {
    private ArrayList<MedicalOperation> operations;
    private ArrayList<Prescription> prescriptions;

    public MedicalRecord() {
        this.operations = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public void addOperation(MedicalOperation op) {
        this.operations.add(op);
    }

    public void addPrescription(Prescription p) {
        this.prescriptions.add(p);
    }

    // --- DOSYAYA RAPOR ÇIKARMA ÖZELLİĞİ ---
    public void saveReportToFile(String animalName) {
        String fileName = "Report_" + animalName + "_" + System.currentTimeMillis() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=== MEDICAL REPORT FOR: " + animalName + " ===\n\n");

            writer.write("--- OPERATIONS ---\n");
            for(MedicalOperation op : operations) {
                // Basitçe maliyeti ve açıklamayı yazalım
                writer.write(op.description + " (" + op.cost + " TL)\n");
            }

            writer.write("\n--- PRESCRIPTIONS ---\n");
            for(Prescription p : prescriptions) {
                writer.write(p.toString() + "\n");
            }

            System.out.println(" Rapor dosyaya kaydedildi: " + fileName);
        } catch (IOException e) {
            System.out.println(" Rapor kaydedilemedi: " + e.getMessage());
        }
    }
}