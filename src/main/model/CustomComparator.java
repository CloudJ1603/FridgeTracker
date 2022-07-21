package model;

import java.util.Comparator;


public class CustomComparator implements Comparator<Food> {
    @Override
    public int compare(Food f1, Food f2) {
        return Integer.compare(f1.getExpiry(),f2.getExpiry());
    }

}
