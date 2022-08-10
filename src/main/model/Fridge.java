package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// a fridge tracker which holds and manipulates a list of food
public class Fridge implements Writable {
    // fields
    private List<Food> foods;
    private String name;

    // construct Fridge object
    public Fridge(String name) {
        this.name = name;
        this.foods = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: order the Food items in the list<Food> based on
    //          the remaining days in descending order
    public void customSort() {
        foods.sort(new CustomComparator());
    }

    // MODIFIES: this
    // EFFECTS: add new food item to the fridge tracker
    public void putInFridge(Food food) {
        this.foods.add(food);
        EventLog.getInstance().logEvent(new Event("[Name: " + food.getName() + "]"
                + "[Category: " + food.getCategory() + "]"
                + "[Remaining Days: " + food.getRemaining() + "]"
                + " - added to the fridge tracker"));
    }

    // MODIFIES: this
    // EFFECTS: remove the expired food item from the fridge tracker
    public void remove(List<Food> foodToRemove) {
        foods.removeAll(foodToRemove);
        String description = "";
        for (Food food : foodToRemove) {
            description += "[Name: " + food.getName() + "]"
                    + "[Category: " + food.getCategory() + "]"
                    + "[Remaining Days: " + food.getRemaining() + "]";
            if (foodToRemove.size() > 1) {
                description += "\n";
            }
        }
        description += " removed from the fridge tracker";
        EventLog.getInstance().logEvent(new Event(description));
    }

    // MODIFIES: this
    // EFFECTS: change the remaining days of all food items in the fridge tracker
    public void nextDay() {
        int n = foods.size();
        for (Food food : foods) {
            food.nextDay();
        }
        EventLog.getInstance().logEvent(new Event("Forward to NextDay"));
    }

    /* ----------------------- getter ----------------------------- */
    // EFFECTS: return a list of all food in the fridge
    public List<Food> getFoodList() {
        return foods;
    }

    // EFFECTS: return the name of the Fridge object
    public String getName() {
        return name;
    }

    /* ----------------------- Json ------------------------------ */
    // EFFECTS: override the toJson method, to convert the fields of
    //          Fridge object to Json document
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("foods", foodToJson());
        return json;
    }

    // EFFECTS: convert all Food item in List<Food> foods to JSONObject
    //          , add them to JSONArray, and return the JSONArray
    public JSONArray foodToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food food : foods) {
            jsonArray.put(food.toJson());
        }

        return jsonArray;
    }

}
