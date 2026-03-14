package appointment;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentFileStore {

    // Dosya yolu bulma mantığımız (Aynı kalıyor)
    private static final String FILE_NAME_DEFAULT = "appointments.txt";
    private static final String FILE_NAME_NESTED = "vetApplication/appointments.txt";
//İki olası dosya yolu var.
//→ Program nereden çalışırsa çalışsın dosya bulunsun diye.


    private final String fileName;
//Gerçekte kullanılacak dosya yolu.
    public AppointmentFileStore(String fileNameIgnored) {
        this.fileName = getFilePath();
    }
    //Constructor.
    //→ Parametre önemsiz, otomatik dosya yolunu buluyor.

    private String getFilePath() {
        //Dosya sistemde var mı kontrol eder.

        File file = new File(FILE_NAME_DEFAULT);
        if (file.exists()) {
            return FILE_NAME_DEFAULT;
        }//Aynı klasörde varsa onu kullan.

        File nestedFile = new File(FILE_NAME_NESTED);
        if (nestedFile.exists()) {
            return FILE_NAME_NESTED;
        }//Yoksa alt klasöre bak.
        return FILE_NAME_DEFAULT;
    }//→ Hiçbiri yoksa default yol seç.

    // --- İŞTE DÜZELTİLEN KISIMLAR ---

    // Eskiden 'add' idi, şimdi 'append' oldu
    public void append(Appointment appointment) {
        //Yeni randevuyu dosyaya ekler.

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            //Dosya yazma
            //→ true → silmeden sona ekle
            //→ try-with-resources → otomatik kapatma
            //➡ file processing + try-catch

            writer.write(appointment.getChipNumber() + "," +
                    appointment.getVetTc() + "," +
                    appointment.getOwnerTc() + "," +
                    appointment.getDate() + "," +
                    appointment.getTime() + "," +
                    appointment.getStatus());
            writer.newLine();
            //Randevu bilgileri CSV formatında yazılır.

        } catch (IOException e) {
            System.out.println("❌ Appointment file write error: " + e.getMessage());
        }
        //Dosya hatası olursa program çökmez.
        //➡ exception handling
    }

    // Eskiden 'getAll' idi, şimdi 'loadAll' oldu
    public List<Appointment> loadAll() {
        //Dosyadaki tüm randevuları belleğe alır.

        List<Appointment> appointments = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            // Dosya yoksa boş liste dön, hata verme
            return appointments;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Basit bir koruma: Satırda eksik bilgi varsa atla
                if (parts.length >= 6) {
                    appointments.add(new Appointment(
                            parts[2], // OwnerTC
                            parts[1], // VetTC
                            parts[0], // Chip
                            LocalDate.parse(parts[3]),
                            LocalTime.parse(parts[4]),
                            AppointmentStatus.valueOf(parts[5])
                    ));
                    //Dosyadaki satırdan yeni Appointment nesnesi üretilir.
                    //➡ File → Object dönüşümü
                    //➡ Bu satır “kalıcı veri” olayının kalbi.
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Appointment file read error.");
        }
        return appointments;
    }

    // Eskiden 'update' idi, şimdi 'overwriteAll' oldu
    public void overwriteAll(List<Appointment> appointments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Appointment appointment : appointments) {
                writer.write(appointment.getChipNumber() + "," +
                        appointment.getVetTc() + "," +
                        appointment.getOwnerTc() + "," +
                        appointment.getDate() + "," +
                        appointment.getTime() + "," +
                        appointment.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ File overwrite error: " + e.getMessage());
        }
    }
}