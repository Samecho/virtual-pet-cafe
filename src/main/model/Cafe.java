package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents the Pet Cafe, where the owner adopts pets to live in the cafe
public class Cafe implements Writable {
    public static final int LOW_HEALTH_THRESHOLD = 20;
    private List<Pet> pets;
    private int adoptedCount; 
    
    // EFFECTS: constructs a new Cafe with an empty list of pets and an adopted count of 0
    public Cafe() {
        pets = new ArrayList<>();
        adoptedCount = 0;
    }

    // MODIFIES: this
    // EFFECTS:  Adds a newly adopted pet to the cafe's list
    //           and increments the total adopted count by 1
    public void adoptPet(Pet pet) {
        pets.add(pet);
        adoptedCount++;
    }

    // REQUIRES: pets.contains(pet) is true
    // MODIFIES: this
    // EFFECTS:  Removes a pet from the cafe's list. Does not affect the adopted count
    public void removePet(Pet pet) {
        pets.remove(pet);
    }

    // EFFECTS: returns the number of pets currently in the cafe
    public int getPetCount() {
        return pets.size();
    }

    // EFFECTS: returns the total number of pets that have ever been adopted into the cafe
    public int getAdoptedCount() {
        return adoptedCount;
    }

    // EFFECTS: returns a list of all pets in the cafe
    public List<Pet> getPets() {
        return pets;
    }

    // EFFECTS: returns a new list containing all pets with health is below LOW_HEALTH_THRESHOLD
    public List<Pet> getPetsWithLowHealth() {
        List<Pet> lowHealthPets = new ArrayList<>();
        for (Pet p : pets) {
            if (p.getHealth() < LOW_HEALTH_THRESHOLD) {
                lowHealthPets.add(p);
            }
        }
        return lowHealthPets;
    }

    // MODIFIES: this
    // EFFECTS: sets the adopted count; this is only for use by the JsonReader
    public void setAdoptedCount(int count) {
        this.adoptedCount = count;
    }

    // MODIFIES: this
    // EFFECTS: adds a pet to the cafe without incrementing the adopted count;
    //          this is only for use by the JsonReader
    public void forceAddPet(Pet pet) {
        pets.add(pet);
    }

    // EFFECTS: returns this pet cafe as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("adoptedCount", adoptedCount);
        json.put("pets", petsToJson());
        return json;
    }

    // EFFECTS: returns pets in this cafe as a JSON array
    private JSONArray petsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pet p : pets) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}