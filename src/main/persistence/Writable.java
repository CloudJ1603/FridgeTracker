package persistence;

import org.json.JSONObject;

// supertype of JsonReader and JsonWriter, force them to implement toJson method
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
