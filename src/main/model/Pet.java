package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a virtual pet
// Pet has name, specie, and stats that change over time,
// These stats include stamina, happiness, hunger, health
// and a sick status
public class Pet implements Writable{
    public static final int MAX_STAT = 100;
    public static final int MIN_STAT = 0;
    public static final int INITIAL_STAT = 50;
    public static final int FOOD_ENERGY = 20;
    public static final int FOOD_HAPPINESS = 5; 
    public static final int PLAY_HAPPINESS = 20;
    public static final int PLAY_STAMINA = 30;




    private int health;
    private int happiness;
    private int hunger;
    private int stamina;
    private String specie;
    private String name;

    // REQUIRES: specie.length() > 0, name.length() > 0
    // EFFECTS: Build a pet object with the given specie and name
    //          with the initial health, happiness, hunger, stamina set to INITIAL_STAT
    //          and isSick set to false
    public Pet(String name, String specie) {
        this.specie = specie;
        this.name = name;
        this.health = INITIAL_STAT;
        this.happiness = INITIAL_STAT;
        this.hunger = INITIAL_STAT;
        this.stamina = INITIAL_STAT;
    }


    // The following are getters
    public String getName() {
        return this.name; 
    }

    public String getSpecie() {
        return this.specie; 
    }

    public int getHunger() {
        return this.hunger; 
    }

    public int getHappiness() {
        return this.happiness; 
    }

    public int getStamina() {
        return this.stamina; 
    }

    public int getHealth() {
        return this.health; 
    }

    // The following are the modifies this

    // MODIFIES: this
    // EFFECTS: Increase the happiness of pet by FOOD_HAPPINESS
    //          HUNGER BY FOOD_ENERGY
    //          Stats always between [MIN_STAT, MAX_STAT]
    public void feed() {
        this.hunger = Math.min(this.hunger + FOOD_ENERGY, MAX_STAT);
        this.happiness = Math.min(this.happiness + FOOD_HAPPINESS, MAX_STAT);

    }

    // MODIFIES: this
    // EFFECTS: Increase the happiness of pet by PLAY_HAPPINESS
    //          Decrease the stamina of pet by PLAY_STAMINA
    //          Stats always between [MIN_STAT, MAX_STAT]
    public void play() {
        this.happiness = Math.min(this.happiness + PLAY_HAPPINESS, MAX_STAT);
        this.stamina = Math.max(this.stamina - PLAY_STAMINA, MIN_STAT);

    }

    // MODIFIES: this
    // EFFECTS: Set the stamina of the pet to MAX_STAT

    public void sleep() {
        this.stamina = MAX_STAT;
    }


    // MODIFIES: this
    // EFFECTS: decrease the health by amount
    //          Stats always between [MIN_STAT, MAX_STAT]
    public void decreaseHealth(int amount) {
        this.health = Math.max(this.health - amount, MIN_STAT);

    }
    
    // MODIFIES: this
    // EFFECTS: Set the health of the pet to MAX_STAT
    public void eatPill() {
        this.health = MAX_STAT; 

    }

    // EFFECTS: returns this pet as a JSON object
    @Override
    public JSONObject toJson() {
        return null;
    }
}
