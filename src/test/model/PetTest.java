package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
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
    void testDecreaseHealthWhenSick() {
    
        testPet.decreaseHealth(20);
        testPet.decreaseHealth(20);
        testPet.decreaseHealth(20);
        testPet.decreaseHealth(20);

        assertEquals(Pet.MIN_STAT, testPet.getHealth());
    }

    @Test
    void testEatPill() {
        testPet.decreaseHealth(40); 
        testPet.eatPill();
        assertEquals(Pet.MAX_STAT, testPet.getHealth());
    }

    @Test
    void testSetHunger() {
        assertEquals(Pet.INITIAL_STAT, testPet.getHunger());
        
        testPet.setHunger(80);
        assertEquals(80, testPet.getHunger());

        testPet.setHunger(10);
        assertEquals(10, testPet.getHunger());
    }

    @Test
    void testSetStamina() {
        assertEquals(Pet.INITIAL_STAT, testPet.getStamina());

        testPet.setStamina(100);
        assertEquals(100, testPet.getStamina());

        testPet.setStamina(0);
        assertEquals(0, testPet.getStamina());
    }

    @Test
    void testSetHappiness() {
        assertEquals(Pet.INITIAL_STAT, testPet.getHappiness());

        testPet.setHappiness(95);
        assertEquals(95, testPet.getHappiness());

        testPet.setHappiness(5);
        assertEquals(5, testPet.getHappiness());
    }

    @Test
    void testSetHealth() {
        assertEquals(Pet.INITIAL_STAT, testPet.getHealth());

        testPet.setHealth(20);
        assertEquals(20, testPet.getHealth());

        testPet.setHealth(75);
        assertEquals(75, testPet.getHealth());
    }
}