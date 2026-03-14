package repository;

import people.Veterinarian;
import java.util.ArrayList;
import java.util.List;

public class VeterinarianRepository {

    private static List<Veterinarian> veterinarians = new ArrayList<>();
//Tüm veterinerler listede tutuluyor.
//→ static → her yerden aynı liste.

    static {
        veterinarians.add(new Veterinarian("Ayşe", "11111111111"));
        veterinarians.add(new Veterinarian("Ali",  "22222222222"));
    }
//Program başlarken otomatik veterinerler yüklenir.
//→ static block.

    public static List<Veterinarian> getAllVeterinarians() {
        return veterinarians;
    } //Tüm veterinerleri döndürür.

    public static Veterinarian getVeterinarianByIndex(int index) {
        //Listeden index’e göre veteriner seçer.

        if (index >= 0 && index < veterinarians.size()) {
            return veterinarians.get(index);
        }
        return null;
    }
}
//Hatalı index gelirse null döner.
//→ Programın çökmesi engellenir.