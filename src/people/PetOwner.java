package people;

public class PetOwner extends Person {
//PetOwner bir Person’dır.
//→ Inheritance.

    public PetOwner(String name, String tc) {
        super(name, tc);
    }
//Person constructor’ı çağrılır.
//→ TC kontrolü orada yapılır.

    @Override
    public void showMenu() {
        //→ Person’daki abstract metot override edildi.
        System.out.println("1- Make an Appointment");
        System.out.println("2- My Appointments");
        System.out.println("3- Add Pet");
        System.out.println("4- My Registered Pets");
        System.out.println("5- View Prescriptions");
    }
}
//PetOwner’a özel menü.
//→ Polymorphism:
//Person p = new PetOwner(...);
//p.showMenu(); → PetOwner menüsü çıkar.