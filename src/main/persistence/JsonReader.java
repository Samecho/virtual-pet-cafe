package persistence;

import model.Cafe;
import org.json.JSONObject;
import java.io.IOException;


// Represents a reader that reads a pet cafe from JSON data stored in a file.

public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from the source file
    public JsonReader(String source) {
        // Stub
    }

    // EFFECTS: reads pet cafe from file and returns it;
    //          throws IOException if an error occurs reading data from the file
    public Cafe read() throws IOException {
        return null; // Stub
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        return null; // Stub
    }

    // EFFECTS: parses pet cafe from JSON object and returns it
    private Cafe parsePetCafe(JSONObject jsonObject) {
        return null; // Stub
    }

    // MODIFIES: cafe
    // EFFECTS: parses pets from JSON object and adds them to the pet cafe
    private void addPets(Cafe cafe, JSONObject jsonObject) {
        // Stub
    }

    // MODIFIES: cafe
    // EFFECTS: parses a pet from JSON object and adds it to the pet cafe
    private void addPet(Cafe cafe, JSONObject jsonObject) {
        // Stub
    }
}