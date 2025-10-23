package persistence;

import model.Cafe;
import model.Pet;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads a pet cafe from JSON data stored in a file.

public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads pet cafe from file and returns it;
    //          throws IOException if an error occurs reading data from the file
    public Cafe read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCafe(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses pet cafe from JSON object and returns it
    private Cafe parseCafe(JSONObject jsonObject) {
        Cafe cafe = new Cafe();
        addPets(cafe, jsonObject);
        // Note: We need a way to set adoptedCount. Let's add a helper method in Cafe.
        int adoptedCount = jsonObject.getInt("adoptedCount");
        cafe.setAdoptedCount(adoptedCount); 
        return cafe;
    }

    // MODIFIES: cafe
    // EFFECTS: parses pets from JSON object and adds them to the pet cafe
    private void addPets(Cafe cafe, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pets");
        for (Object json : jsonArray) {
            JSONObject nextPetJson = (JSONObject) json;
            addPet(cafe, nextPetJson);
        }
    }

    // MODIFIES: cafe
    // EFFECTS: parses a pet from JSON object and adds it to the pet cafe
    private void addPet(Cafe cafe, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String specie = jsonObject.getString("specie");
        
        Pet pet = new Pet(name, specie);
        pet.setHunger(jsonObject.getInt("hunger"));
        pet.setStamina(jsonObject.getInt("stamina"));
        pet.setHappiness(jsonObject.getInt("happiness"));
        pet.setHealth(jsonObject.getInt("health"));
        cafe.forceAddPet(pet);
    }
}