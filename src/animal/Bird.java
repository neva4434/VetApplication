package animal;

import exception.InvalidAnimalDataException;
import people.PetOwner;

public class Bird extends Animal {

    public Bird(String name, int age, double weight, Gender gender, String breed, PetOwner owner)
            throws InvalidAnimalDataException {
        super(name, age, weight, gender, breed, owner);
    }

    @Override
    public String getAnimalInfo() {
        return "Bird: " + getName();
    }
}


