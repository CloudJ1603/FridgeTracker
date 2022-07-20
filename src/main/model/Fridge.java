package model;

import java.util.ArrayList;
import java.util.List;

public class Fridge {
    // fields
    private static final int CAPACITY = 100;
    private List<Food> foods = new ArrayList<>();
    private int storage;

    public Fridge() {
        this.storage = 0;
    }

    // add Food item to the fridge
    // want this method to throw an exception !!!!!!!!!!!!!!!!!!!!!! NO sout print here
    public void putInFridge(Food food) {
        this.foods.add(food);
        storage += food.getSize();
    }

    // remove the expired food
    public void remove() {
        List<Food> foodToRemove = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            if (!foods.get(i).isEdible()) {
                foodToRemove.add(foods.get(i));
            }
        }
        foods.removeAll(foodToRemove);
    }

    // sort the list the food based on their expiry date
    public void sort() {

    }

    /* ---------------- getter ----------------------------- */
    // return a list of all food in the fridge
    public List<Food> getFoodList() {
        return foods;
    }

    // return the current storage condition of the fridge
    public int getStorage() {
        return storage;
    }

    // return the capacity of the fridge
    public int getCapacity() {
        return CAPACITY;
    }




}
