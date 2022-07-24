package model;

import java.util.Comparator;

// a customized comparator which compares the field "expiry" of Food object
public class CustomComparator implements Comparator<Food> {
    @Override
    public int compare(Food f1, Food f2) {
        return Integer.compare(f1.getRemaining(),f2.getRemaining());
    }

}
