package appointment;
import java.time.LocalDate;
import java.time.LocalTime;
//Tarih ve saat ayrı ayrı tutuluyor.
//→ String yerine LocalDate/LocalTime → daha güvenli ve profesyonel.

public class Appointment {

    private String vetTc;
    private String ownerTc;
    private String chipNumber;
    private LocalDate date;
    private LocalTime time;
    private AppointmentStatus status;

    public Appointment(String vetTc,
                       String ownerTc,
                       String chipNumber,
                       LocalDate date,
                       LocalTime time,
                       AppointmentStatus status) {

        this.vetTc = vetTc;
        this.ownerTc = ownerTc;
        this.chipNumber = chipNumber;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getVetTc() {
        return vetTc;
    }

    public String getOwnerTc() {
        return ownerTc;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public AppointmentStatus getStatus() {
        return status;
    }
    //Getter metotları.
    //→ private alanlara kontrollü erişim.
    //→ encapsulation.

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
//Sadece status değiştirilebilir.
//→ Randevu onaylama / iptal etme buradan yapılır.

    public String toString() {
        //Object class’ındaki toString() override edildi.
        //→ Randevu ekrana düzgün formatta basılır.
        //➡ Override burada.

        return "Date=" + date +
                " Time=" + time +
                " | Chip=" + chipNumber +
                " | OwnerTC=" + ownerTc +
                " | VetTC=" + vetTc +
                " | Status=" + status;
    }
//→ Randevu bilgileri okunabilir formatta döndürülür.
}