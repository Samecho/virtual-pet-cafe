package persistence;

import model.Cafe;
import model.Pet;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport

class JsonWriterTest extends JsonTest {
   
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCafe() {
        try {
            Cafe cafe = new Cafe();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCafe.json");
            writer.open();
            writer.write(cafe);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCafe.json");
            cafe = reader.read();
            assertEquals(0, cafe.getAdoptedCount());
            assertEquals(0, cafe.getPetCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCafe() {
        try {
            Cafe cafe = new Cafe();
            Pet pet1 = new Pet("Billy", "Dog");
            Pet pet2 = new Pet("Momo", "Cat");
            cafe.adoptPet(pet1);
            cafe.adoptPet(pet2);
            cafe.removePet(pet1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCafe.json");
            writer.open();
            writer.write(cafe);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralCafe.json");
            cafe = reader.read();
            assertEquals(2, cafe.getAdoptedCount());
            assertEquals(1, cafe.getPetCount());
            List<Pet> pets = cafe.getPets();
            assertEquals(1, pets.size());
            checkPet("Momo", "Cat", Pet.INITIAL_STAT, Pet.INITIAL_STAT, 
                    Pet.INITIAL_STAT, Pet.INITIAL_STAT, pets.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}