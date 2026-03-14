package menu;

import animal.*;
import appointment.Appointment;
import appointment.AppointmentFileStore;
import appointment.AppointmentManager;
import appointment.AppointmentStatus;
import exception.InvalidAnimalDataException;
import medical.Checkup;
import medical.MedicalOperation;
import medical.Surgery;
import people.PetOwner;
import people.Veterinarian;
import prescription.*;
import repository.AnimalFileRepository;
import repository.VeterinarianRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);

    private final AppointmentManager appointmentManager =
            new AppointmentManager(new AppointmentFileStore("appointments.txt"));

    // ================= START =================
    public void start() {
        while (true) {
            System.out.println("\n=== SELECT ROLE ===");
            System.out.println("1- Pet Owner");
            System.out.println("2- Veterinarian");
            System.out.println("3- Exit");
            System.out.print("Choice: ");

            switch (sc.nextLine()) {
                case "1" -> petOwnerAuth();
                case "2" -> veterinarianAuth();
                case "3" -> {
                    System.out.println("Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ================= PET OWNER AUTH =================
    private void petOwnerAuth() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("TC: ");
        String tc = sc.nextLine();

        PetOwner owner = new PetOwner(name, tc); // artık try-catch yok
        petOwnerMenu(owner);
    }

    // ================= PET OWNER MENU =================
    private void petOwnerMenu(PetOwner owner) {
        while (true) {
            System.out.println("\n=== PET OWNER MENU ===");
            System.out.println("1- Register Animal");
            System.out.println("2- List My Animals");
            System.out.println("3- Create Appointment");
            System.out.println("4- View My Appointments");
            System.out.println("5- Cancel Appointment");
            System.out.println("6- View My Prescriptions");
            System.out.println("7- Logout");

            switch (sc.nextLine()) {
                case "1" -> registerAnimal(owner);
                case "2" -> listAnimals(owner);
                case "3" -> createAppointment(owner);
                case "4" -> listOwnerAppointments(owner);
                case "5" -> cancelAppointment(owner.getTc());
                case "6" -> viewMyPrescriptions(owner);
                case "7" -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ================= ANIMAL =================
    private void registerAnimal(PetOwner owner) {
        System.out.println("\n--- ANIMAL REGISTRATION ---");
        try {
            String type = "";
            while (type.isEmpty()) {
                System.out.print("Animal type (CAT/DOG/BIRD): ");
                type = sc.nextLine().trim().toUpperCase();
            }

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Weight: ");
            double weight = Double.parseDouble(sc.nextLine());

            System.out.print("Gender (MALE/FEMALE): ");
            Gender gender = Gender.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Breed: ");
            String breed = sc.nextLine();

            Animal animal = switch (type) {
                case "CAT" -> new Cat(name, age, weight, gender, breed, owner);
                case "DOG" -> new Dog(name, age, weight, gender, breed, owner);
                case "BIRD" -> new Bird(name, age, weight, gender, breed, owner);
                default -> null;
            };

            if (animal == null) {
                System.out.println("❌ Invalid animal type selected.");
                return;
            }

            AnimalFileRepository.saveAnimal(animal);
            System.out.println("✅ Animal registered successfully: " + name);

        } catch (InvalidAnimalDataException e) {
            System.out.println("❌ Invalid animal data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void listAnimals(PetOwner owner) {
        System.out.println("\n=== MY ANIMALS ===");
        AnimalFileRepository.readAllAnimals().stream()
                .filter(line -> line.contains(owner.getTc()))
                .forEach(System.out::println);
    }

    // ================= APPOINTMENT (PET OWNER) =================
    private void createAppointment(PetOwner owner) {
        try {
            System.out.print("Chip Number: ");
            String chip = sc.nextLine();

            System.out.println("Select Veterinarian:");
            List<Veterinarian> vets = VeterinarianRepository.getAllVeterinarians();
            for (int i = 0; i < vets.size(); i++) {
                System.out.println((i + 1) + "- " + vets.get(i).getName());
            }

            int index = Integer.parseInt(sc.nextLine()) - 1;
            Veterinarian vet = vets.get(index);

            System.out.print("Date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(sc.nextLine());

            System.out.print("Time (HH:MM): ");
            LocalTime time = LocalTime.parse(sc.nextLine());

            Appointment a = new Appointment(vet.getTc(), owner.getTc(), chip, date, time, AppointmentStatus.REQUESTED);
            appointmentManager.addAppointment(a);
            System.out.println("Appointment created.");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void listOwnerAppointments(PetOwner owner) {
        System.out.println("\n=== MY APPOINTMENTS ===");
        appointmentManager.getAllAppointments().stream()
                .filter(a -> a.getOwnerTc().equals(owner.getTc()))
                .forEach(System.out::println);
    }

    private void cancelAppointment(String tc) {
        List<Appointment> list = appointmentManager.getAllAppointments();
        for (int i = 0; i < list.size(); i++) {
            Appointment a = list.get(i);
            if (a.getOwnerTc().equals(tc) && a.getStatus() == AppointmentStatus.REQUESTED) {
                System.out.println((i + 1) + "- " + a.getDate() + " " + a.getTime() + " | Chip=" + a.getChipNumber());
            }
        }
        System.out.print("Select appointment to cancel: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;
        appointmentManager.cancelAppointment(list.get(index));
        System.out.println("Appointment cancelled.");
    }

    // ================= VETERINARIAN =================
    private void veterinarianAuth() {
        System.out.print("Enter TC: ");
        String tc = sc.nextLine();
        Veterinarian vet = VeterinarianRepository.getAllVeterinarians().stream()
                .filter(v -> v.getTc().equals(tc)).findFirst().orElse(null);

        if (vet == null) {
            System.out.println("Invalid veterinarian.");
            return;
        }
        veterinarianMenu(vet);
    }

    private void veterinarianMenu(Veterinarian vet) {
        while (true) {
            System.out.println("\n=== VETERINARIAN MENU ===");
            System.out.println("1- View Appointments");
            System.out.println("2- Approve Appointment");
            System.out.println("3- Cancel Appointment");
            System.out.println("4- Write Prescription (File I/O)");
            System.out.println("5- Add Medical Operation");
            System.out.println("6- View Medical History");
            System.out.println("0- Logout");

            switch (sc.nextLine()) {
                case "1" -> listVetAppointments(vet);
                case "2" -> approveAppointment(vet);
                case "3" -> cancelVetAppointment(vet);
                case "4" -> writePrescription(vet);
                case "5" -> addMedicalOperation(vet);
                case "6" -> viewMedicalHistory();
                case "0" -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void listVetAppointments(Veterinarian vet) {
        System.out.println("\n=== APPOINTMENTS ===");
        appointmentManager.getAllAppointments().stream()
                .filter(a -> a.getVetTc().equals(vet.getTc()))
                .forEach(System.out::println);
    }

    private void approveAppointment(Veterinarian vet) {
        List<Appointment> list = appointmentManager.getAllAppointments();
        for (int i = 0; i < list.size(); i++) {
            Appointment a = list.get(i);
            if (a.getVetTc().equals(vet.getTc()) && a.getStatus() == AppointmentStatus.REQUESTED) {
                System.out.println((i + 1) + "- " + a.getDate() + " " + a.getTime() + " | Chip=" + a.getChipNumber());
            }
        }
        System.out.print("Select appointment to approve: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;
        appointmentManager.approveAppointment(list.get(index), vet);
        System.out.println("Appointment approved.");
    }

    private void cancelVetAppointment(Veterinarian vet) {
        List<Appointment> list = appointmentManager.getAllAppointments();
        for (int i = 0; i < list.size(); i++) {
            Appointment a = list.get(i);
            if (a.getVetTc().equals(vet.getTc()) && a.getStatus() == AppointmentStatus.REQUESTED) {
                System.out.println((i + 1) + "- " + a.getDate() + " " + a.getTime() + " | Chip=" + a.getChipNumber());
            }
        }
        System.out.print("Select appointment to cancel: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;
        appointmentManager.cancelAppointment(list.get(index));
        System.out.println("Appointment cancelled by veterinarian.");
    }

    // ================= MEDICAL & PRESCRIPTION =================
    private void writePrescription(Veterinarian vet) {
        System.out.println("\n--- WRITE PRESCRIPTION ---");
        System.out.print("Enter Animal Chip ID: ");
        String chip = sc.nextLine();

        try {
            Animal patient = new Cat("Patient-" + chip, 1, 5.0, Gender.MALE, "Unknown", null);

            System.out.print("Medicine Name: ");
            String medName = sc.nextLine();
            System.out.print("Dose Amount: ");
            String amount = sc.nextLine();
            System.out.print("Frequency: ");
            String freq = sc.nextLine();

            Medicine med = new Medicine(medName, "General", 0.0);
            Dosage dosage = new Dosage(amount, freq);
            Prescription p = new Prescription(vet, patient, med, dosage);
            p.printToFile();
            System.out.println("✅ Prescription saved to file.");
        } catch (InvalidAnimalDataException e) {
            System.out.println("❌ Invalid animal data: " + e.getMessage());
        }
    }

    private void addMedicalOperation(Veterinarian vet) {
        System.out.println("\n--- ADD OPERATION ---");
        sc.nextLine(); // sadece bilgi amaçlı

        System.out.print("Operation Type (Surgery/Checkup): ");
        String opType = sc.nextLine();
        System.out.print("Description: ");
        String desc = sc.nextLine();
        System.out.print("Cost: ");
        double cost = Double.parseDouble(sc.nextLine());

        MedicalOperation op = opType.equalsIgnoreCase("Surgery") ?
                new Surgery(desc, cost) : new Checkup(desc, cost);

        op.printDetails();
        System.out.println("✅ Operation recorded.");
    }

    private void viewMyPrescriptions(PetOwner owner) {
        System.out.println("\n=== MY PRESCRIPTIONS (FILES) ===");
        List<String> myChips = new ArrayList<>();
        List<String> allAnimals = AnimalFileRepository.readAllAnimals();

        for (String line : allAnimals) {
            String[] parts = line.split(";");
            if (parts.length >= 4 && parts[2].trim().equals(owner.getTc())) {
                myChips.add(parts[3].trim());
            }
        }

        java.io.File folder = new java.io.File(".");
        java.io.File[] files = folder.listFiles((dir, name) -> name.startsWith("Recete_") && name.endsWith(".txt"));

        if (files != null) {
            for (java.io.File file : files) {
                for (String chip : myChips) {
                    if (file.getName().contains(chip)) {
                        System.out.println("📄 FILE: " + file.getName());
                        try (Scanner reader = new Scanner(file)) {
                            while (reader.hasNextLine()) System.out.println(reader.nextLine());
                        } catch (Exception e) { System.out.println("Error reading file."); }
                        System.out.println("-------------------");
                    }
                }
            }
        }
    }

    private void viewMedicalHistory() {
        System.out.println("Feature coming soon.");
    }
}
