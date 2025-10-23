package persistence;

import model.Cafe;
import model.Pet;

//import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
class JsonReaderTest extends JsonTest {


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Cafe cafe = reader.read();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCafe() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCafe.json");
        try {
            Cafe cafe = reader.read();
            assertEquals(0, cafe.getAdoptedCount());
            assertEquals(0, cafe.getPetCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    void testReaderGeneralCafe() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCafe.json");
        try {
            Cafe cafe = reader.read();
            assertEquals(3, cafe.getAdoptedCount());
            assertEquals(2, cafe.getPetCount());

            List<Pet> pets = cafe.getPets();
            assertEquals(2, pets.size());
            
            checkPet("Momo", "Dog", 80, 95, 75, 90, pets.get(0));
            checkPet("Billy", "Dog", 50, 50, 50, 50, pets.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}