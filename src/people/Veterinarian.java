package people;

public class Veterinarian extends Person {

    public Veterinarian(String name, String tc) {
        super(name, tc);
    }

    @Override
    public void showMenu() {
        System.out.println("1- Registered Appointments");
        System.out.println("2- Registered Animals");
        System.out.println("3- Operation History");
        System.out.println("4- Add Operation");
        System.out.println("5- Prescribe Medication");
    }
}
//Abstract metot override edildi.