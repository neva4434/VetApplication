package animal;

import chip.Chip;
import exception.InvalidAnimalDataException;
import people.PetOwner;
//Chip → hayvana otomatik çip vermek için
//InvalidAnimalDataException → hatalı veri kontrolü için
//PetOwner → hayvanın sahibini tutmak için
//Bu satırlar projenin modüler olduğunu gösterir.


public abstract class Animal {
//Animal abstract bir class.
//→ new Animal() yapılamaz.
//→ Sadece Dog, Cat, Bird gibi class’lar Animal’dan türeyebilir.
//→ Çünkü sistemde “genel hayvan” yok, her hayvanın türü var.



    private String name;
    private int age;
    private double weight;
    private Gender gender;
    private String breed;
    private PetOwner owner;
    private Chip chip;
//Bunlar tüm hayvanların ortak özellikleri.
//→ Hepsi private → dışarıdan direkt erişilemez.
//→ Bu encapsulation. (veri güvenliği)
//→ PetOwner owner → hayvanın sahibi tutuluyor.
//→ Chip chip → her hayvana otomatik kimlik.



    public Animal(String name, int age, double weight, Gender gender, String breed, PetOwner owner)
            throws InvalidAnimalDataException {
        //Bu Animal constructor’ı.
        //→ Dog, Cat, Bird oluşturulurken burası çalışır.
        //→ throws InvalidAnimalDataException → hatalı veri gelirse exception fırlatabilir.


        if (age <= 0) {
            throw new InvalidAnimalDataException("Age cannot be zero or negative.");
        }
        //Yaş 0 veya negatifse nesne oluşturulmaz.
        //→ Kendi yazdığınız custom exception devreye girer.
        //→ Bu: exception + try-catch mantığına alt

        if (weight <= 0) {
            throw new InvalidAnimalDataException("Weight cannot be zero or negative.");
        }
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.breed = breed;
        this.owner = owner;
        this.chip = new Chip();
    }
    //Gelen veriler Animal’ın field’larına atanır.
    //→ this.chip = new Chip();
    //➡ Hayvan oluşturulunca otomatik chip üretilir.
    //➡ Composition var (Animal → Chip kullanıyor).

    public String getName() {
        return name;
    }
//Name private olduğu için dışarıya kontrollü erişim.
//→ Encapsulation.

    public Chip getChip() {
        return chip;
    }

    public PetOwner getOwner() {
        return owner;
    }

    public abstract String getAnimalInfo();
}
//Abstract metot.
//→ Animal içinde gövdesi yok.
//→ Dog, Cat, Bird bunu yazmak zorunda.
//➡ Bu satır:
//polymorphism’in temeli
//override zorunluluğu
//her hayvanın bilgisini farklı vermesini sağlar
