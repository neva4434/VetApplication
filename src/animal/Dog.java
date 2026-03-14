package animal;

import exception.InvalidAnimalDataException;
import people.PetOwner;
//→ Animal constructor’ı exception fırlattığı için burada da kullanılıyor.


public class Dog extends Animal {
    //Dog, Animal’dan türetilmiş.
//→ Dog bir Animal’dır.
//→ Inheritance burada.


    public Dog(String name, int age, double weight, Gender gender, String breed, PetOwner owner)
            throws InvalidAnimalDataException {
        //Dog constructor’ı.
        //→ Animal constructor’ı exception attığı için burada da throws var.


        super(name, age, weight, gender, breed, owner);
    }
    //Animal constructor’ı çağrılır.
    //→ Tüm kontroller (age, weight, chip oluşturma) Animal’da yapılır.
    //→ Kod tekrar etmez.

    @Override
    public String getAnimalInfo() {
        return "Dog: " + getName();
    }
}
//Animal’daki abstract metot burada dolduruldu.
//→ Override var.
//→ Her hayvan kendi bilgisini üretir.
//➡ Bu satır polymorphism’in canlı hali:
//Animal a = new Dog(...);
//a.getAnimalInfo(); → Dog versiyonu çalışır.
