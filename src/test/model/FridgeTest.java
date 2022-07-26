package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test for Fridge class
class FridgeTest {

    private Fridge fridge;
    private Food f1;
    private Food f2;
    private Food f3;

    Category c1 = Category.values()[0];
    Category c2 = Category.values()[1];
    Category c3 = Category.values()[2];

    @BeforeEach
    public void setup () {
        fridge = new Fridge("myFridge");
    }

    @Test
    public void testConstructor() {
        assertTrue(fridge.getFoodList().isEmpty());
    }

    @Test
    public void testCustomSort() {
        f1 = new Food("lettuce",c1,3);
        f2 = new Food("apple", c2, 2);
        f3 = new Food("beef", c3, 1);

        fridge.putInFridge(f1);
        fridge.putInFridge(f2);
        fridge.putInFridge(f3);

        fridge.customSort();

        assertEquals(f3,fridge.getFoodList().get(0));
        assertEquals(f2,fridge.getFoodList().get(1));
        assertEquals(f1,fridge.getFoodList().get(2));
    }

    @Test
    public void testPutInFridge() {
        fridge.putInFridge(f1);
        assertEquals(f1,fridge.getFoodList().get(0));
        fridge.putInFridge(f2);
        assertEquals(f1,fridge.getFoodList().get(0));
        assertEquals(f2,fridge.getFoodList().get(1));
    }

    @Test
    public void testRemove() {
        fridge.putInFridge(f1);
        assertEquals(f1,fridge.getFoodList().get(0));

        List<Food> temp = new ArrayList<>();
        temp.add(f1);

        fridge.remove(temp);
        assertTrue(fridge.getFoodList().isEmpty());
    }

    @Test
    public void testNextDay() {
        f1 = new Food("lettuce",c1,1);
        f2 = new Food("apple", c2, 2);

        fridge.putInFridge(f1);
        fridge.putInFridge(f2);
        assertEquals(1,fridge.getFoodList().get(0).getRemaining());
        assertEquals(2,fridge.getFoodList().get(1).getRemaining());

        fridge.nextDay();
        assertEquals(0,fridge.getFoodList().get(0).getRemaining());
        assertEquals(1,fridge.getFoodList().get(1).getRemaining());

        fridge.nextDay();
        assertEquals(0,fridge.getFoodList().get(0).getRemaining());
        assertEquals(0,fridge.getFoodList().get(1).getRemaining());
    }

}