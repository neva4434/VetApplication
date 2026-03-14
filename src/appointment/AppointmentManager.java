package appointment;

import exception.AppointmentConflictException;
import people.Veterinarian;

import java.util.List;

public class AppointmentManager {

    private final AppointmentFileStore fileStore;
    private final List<Appointment> appointments;

    public AppointmentManager(AppointmentFileStore fileStore) {
        this.fileStore = fileStore;
        this.appointments = fileStore.loadAll();
    }

    public void addAppointment(Appointment appointment) throws AppointmentConflictException {
        // Çakışma kontrolü
        for (Appointment existing : appointments) {
            if (existing.getVetTc().equals(appointment.getVetTc()) &&
                    existing.getDate().equals(appointment.getDate()) &&
                    existing.getTime().equals(appointment.getTime()) &&
                    existing.getStatus() == AppointmentStatus.REQUESTED) {
                throw new AppointmentConflictException(
                        "This veterinarian already has an appointment at this time."
                );
            }
        }

        appointments.add(appointment);
        fileStore.append(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public void cancelAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        fileStore.overwriteAll(appointments);
    }

    public void approveAppointment(Appointment appointment, Veterinarian vet) {
        appointment.setStatus(AppointmentStatus.APPROVED);
        fileStore.overwriteAll(appointments);
    }
}

