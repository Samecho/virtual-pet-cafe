package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetTest {

    private Pet testPet;

    @BeforeEach
    void runBefore() {
        testPet = new Pet("Momo","Cat");
    }

    @Test
    void testConstructor() {
        assertEquals("Momo", testPet.getName());
        assertEquals("Cat", testPet.getSpecie());
        assertEquals(Pet.INITIAL_STAT, testPet.getHealth());
        assertEquals(Pet.INITIAL_STAT, testPet.getHappiness());
        assertEquals(Pet.INITIAL_STAT, testPet.getHunger());
        assertEquals(Pet.INITIAL_STAT, testPet.getStamina());
    }

    @Test
    void testFeed() {
        testPet.feed();
        assertEquals(Pet.INITIAL_STAT + Pet.FOOD_HAPPINESS, testPet.getHappiness());
        assertEquals(Pet.INITIAL_STAT + Pet.FOOD_ENERGY, testPet.getHunger());
    }

    @Test
    void testFeedWhenFull() {
        testPet.feed();
        testPet.feed();
        testPet.feed();
        testPet.feed();
        assertEquals(Pet.MAX_STAT, testPet.getHunger());
    }

    @Test
    void testPlay() {
        testPet.play();
        assertEquals(Pet.INITIAL_STAT + Pet.PLAY_HAPPINESS, testPet.getHappiness());
        assertEquals(Pet.INITIAL_STAT - Pet.PLAY_STAMINA, testPet.getStamina());
    }

    @Test
    void testPlayWhenTired() {
        testPet.play();
        testPet.play();
        assertEquals(Pet.MIN_STAT, testPet.getStamina());
    }

    @Test
    void testPlayWhenHappy() {
        testPet.play();
        testPet.play();
        testPet.play();
        testPet.play();
        assertEquals(Pet.MAX_STAT, testPet.getHappiness());
    }

    @Test
    void testSleep() {
        testPet.play();
        assertTrue(testPet.getStamina() < Pet.MAX_STAT);

        testPet.sleep();
        assertEquals(Pet.MAX_STAT, testPet.getStamina());
    }

    @Test
    void testDecreaseHealth() {
    
        testPet.decreaseHealth(20);
        assertEquals(30, testPet.getHealth());
    }

    @Test
    void testEatPill() {
        testPet.decreaseHealth(40); // Health is 10
        testPet.eatPill();
        assertEquals(Pet.MAX_STAT, testPet.getHealth());
    }
}