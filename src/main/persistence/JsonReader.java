package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads fridge from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads fridge from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Fridge read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFridge(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses foods from JSON object and returns it
    private Fridge parseFridge(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Fridge fridge = new Fridge(name);
        addThingies(fridge, jsonObject);
        return fridge;
    }

    // MODIFIES: fridge
    // EFFECTS: parses thingies from JSON object and adds them to fridge
    private void addThingies(Fridge fridge, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foods");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFridge(fridge, nextFood);
        }
    }

    // MODIFIES: fridge
    // EFFECTS: parses thingy from JSON object and adds it to fridge
    private void addFridge(Fridge fridge, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int remaining = jsonObject.getInt("remaining");
        Category category = Category.valueOf(jsonObject.getString("category"));
        Food food = new Food(name, category, remaining);
        fridge.putInFridge(food);
    }
}
