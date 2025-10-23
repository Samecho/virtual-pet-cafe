package persistence;

import model.Cafe;
import org.json.JSONObject;
import java.io.*;

// Represents a writer that writes a JSON representation of a pet cafe to a file.

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a writer to write to the destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer; throws FileNotFoundException if the destination file cannot
    //          be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes a JSON representation of the pet cafe to the file
    public void write(Cafe cafe) {
        JSONObject json = cafe.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes a string to the file
    private void saveToFile(String json) {
        writer.print(json);
    }
}