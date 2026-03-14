package chip;

import java.time.LocalDate;
import java.util.UUID;
//Java hazır kütüphaneleri.
//UUID → benzersiz numara üretmek için
//LocalDate → kayıt tarihini tutmak için

public class Chip {
//Chip normal bir class.
//→ Hayvanlara ait kimlik nesnesi.


    private final String chipNumber;
    private final LocalDate registerDate;
//private → encapsulation
//→ final → bir kez atanır, bir daha değişmez
//→ Gerçek hayattaki çip mantığına uygun.


    public Chip() {
        this.chipNumber = UUID.randomUUID().toString();
        this.registerDate = LocalDate.now();
    }
    //Chip oluşturulunca:
    //otomatik benzersiz numara üretir
    //otomatik bugünün tarihi atanır
    //➡ Animal constructor’ında new Chip() çağrıldığı için
    //her hayvan otomatik çiplenir.
    //➡ Bu: composition (Animal → Chip kullanıyor)



    public String getChipNumber() {
        return chipNumber;
    }
//Private alan kontrollü şekilde okunur.
//→ Encapsulation.


    public LocalDate getRegisterDate() {
        return registerDate;
    }

    @Override
    public String toString() {
        return "Chip No: " + chipNumber;
    }
}
//Object class’ındaki toString() override edildi.
//→ Chip ekrana veya dosyaya düzgün yazdırılabilir.
//➡ Override burada.