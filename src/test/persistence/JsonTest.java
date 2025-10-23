package persistence;

import model.Pet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkPet(String name, String specie, int hunger, int health, int stamina, int happiness, Pet pet) {
        assertEquals(name, pet.getName());
        assertEquals(specie, pet.getSpecie());
        assertEquals(hunger, pet.getHunger());
        assertEquals(health, pet.getHealth());
        assertEquals(stamina, pet.getStamina());
        assertEquals(happiness, pet.getHappiness());
    }
}