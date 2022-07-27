package model;

import org.json.JSONObject;
import persistence.Writable;

// an abstract class which is the superclass of all types of food
public class Food implements Writable {
    // field
    protected String name;
    protected int remaining;
    private Category category;

    // EFFECTS: construct Food object
    public Food(String name, Category category, int remaining) {
        this.name = name;
        this.category = category;
        this.remaining = remaining;
    }

    /* ----------------- getter ----------------------------  */
    // EFFECTS: return the name of the Food object
    public String getName() {
        return name;
    }

    // EFFECTS: return the remaining days of the Food object
    public int getRemaining() {
        return remaining;
    }

    // EFFECTS: return the type of the Food object
    public Category getCategory() {
        return category;
    }

    /* ----------------- setter ----------------------------  */
    // MODIFIES: this
    // EFFECTS: set up the name of the Food object
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: the input parameter must be a positive integer
    // MODIFIES: this
    // EFFECTS: set up the remaining (days) of the Food object
    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }


    /* ----------------- modifier ----------------------------  */
    // MODIFIES: this
    // EFFECTS: forward to next day, equivalent to decrement the remaining (days)
    public void nextDay() {
        if (remaining != 0) {
            remaining--;
        }
    }

    /* --------------------- json ----------------------- */
    // EFFECTS: override the toJson method, to convert the fields of
    //          Food object to Json document
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("category", category);
        json.put("remaining", remaining);
        return json;
    }
}
