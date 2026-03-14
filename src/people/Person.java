package people;

public abstract class Person {
//Person abstract.
//→ Direkt insan oluşturulmaz.
//→ PetOwner ve Veterinarian türetilir.


    private String name;
    private String tc;
//private: dışarıdan direkt erişilemez.
//→ Veri güvenliği sağlar (encapsulation).


    public Person(String name, String tc) {
//Person constructor’ı.
//→ Alt class’lar bunu çağırır.


        if (tc == null || tc.length() != 11) {
            throw new IllegalArgumentException("National ID number must be 11 digits.");
        }
        //TC kontrolü.
        //→ Hatalıysa sistem baştan engelliyor.
        //→ Bu da exception mantığı.

        this.name = name;
        this.tc = tc;
    }

    public String getName() {
        return name;
    }

    public String getTc() {
        return tc;
    }

    public boolean login(String tc) {
        return this.tc.equals(tc);
    }
//Girilen TC ile nesnedeki TC karşılaştırılır.
//→ Basit login kontrolü.

    public abstract void showMenu();
}
//Her kullanıcı kendi menüsünü göstermek zorunda.
//→ Veterinarian ve PetOwner bunu override eder.