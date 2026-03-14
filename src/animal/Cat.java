package animal;

import exception.InvalidAnimalDataException;
import people.PetOwner;

public class Cat extends Animal {

    public Cat(String name, int age, double weight, Gender gender, String breed, PetOwner owner)
            throws InvalidAnimalDataException {
        super(name, age, weight, gender, breed, owner);
    }

    @Override
    public String getAnimalInfo() {
        return "Cat: " + getName();
    }
}

