package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CafeTest {
    private Cafe testCafe;
    private Pet pet1;
    private Pet pet2;

    @BeforeEach
    void runBefore() {
        testCafe = new Cafe();
        pet1 = new Pet("Cat", "Momo");
        pet2 = new Pet("Dog", "Billy");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCafe.getPetCount());
        assertEquals(0, testCafe.getAdoptedCount());
        assertTrue(testCafe.getPets().isEmpty());
    }

    @Test
    void testAdoptPet() {
        testCafe.adoptPet(pet1);
        assertEquals(1, testCafe.getPetCount());
        assertEquals(1, testCafe.getAdoptedCount());
    }

    @Test
    void testRemovePet() {
        testCafe.adoptPet(pet1);
        testCafe.adoptPet(pet2);

        testCafe.removePet(pet1);
        assertEquals(1, testCafe.getPetCount());
        assertEquals(2, testCafe.getAdoptedCount());
        assertEquals(false, testCafe.getPets().contains(pet1));
    }

    @Test
    void testGetPets() {
        testCafe.adoptPet(pet1);
        testCafe.adoptPet(pet2);
        List<Pet> pets = testCafe.getPets();
        assertEquals(2, pets.size());
        assertTrue(pets.contains(pet1));
        assertTrue(pets.contains(pet2));
    }

    @Test
    void testGetPetsWithLowHealthNoneAreLow() {
        testCafe.adoptPet(pet1);
        testCafe.adoptPet(pet2);
        assertTrue(testCafe.getPetsWithLowHealth().isEmpty());
    }

    @Test
    void testGetPetsWithLowHealthSomeAreLow() {
        pet1.decreaseHealth(40);

        testCafe.adoptPet(pet1);
        testCafe.adoptPet(pet2);

        List<Pet> lowHealthPets = testCafe.getPetsWithLowHealth();
        assertEquals(1, lowHealthPets.size());
        assertTrue(lowHealthPets.contains(pet1));
    }
}