package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
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
    }

    // MODIFIES: this
    // EFFECTS: remove the expired food item from the fridge tracker
    public void remove(List<Food> foodToRemove) {
        foods.removeAll(foodToRemove);
    }

    // MODIFIES: this
    // EFFECTS: change the remaining days of all food items in the fridge tracker
    public void nextDay() {
        int n = foods.size();
        for (Food food : foods) {
            food.nextDay();
        }
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
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("foods", foodToJson());
        return json;
    }

    public JSONArray foodToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food food: foods) {
            jsonArray.put(food.toJson());
        }

        return jsonArray;
    }


}
