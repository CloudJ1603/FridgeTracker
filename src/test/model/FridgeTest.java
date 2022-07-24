package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test for Fridge class
class FridgeTest {

    private Fridge fridge;

    @BeforeEach
    public void setup () {
        fridge = new Fridge();
    }

    @Test
    public void testConstructor() {
        assertTrue(fridge.getFoodList().isEmpty());
    }

    @Test
    public void testCustomSort() {
        Food f1 = new Fruit();
        f1.setRemaining(3);
        Food f2 = new Vegetable();
        f2.setRemaining(2);
        Food f3 = new Meat();
        f3.setRemaining(1);

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
        Food f1 = new Fruit();
        Food f2 = new Vegetable();

        fridge.putInFridge(f1);
        assertEquals(f1,fridge.getFoodList().get(0));
        fridge.putInFridge(f2);
        assertEquals(f1,fridge.getFoodList().get(0));
        assertEquals(f2,fridge.getFoodList().get(1));
    }

    @Test
    public void testRemove() {
        Food f1 = new Fruit();
        Food f2 = new Vegetable();

        fridge.putInFridge(f1);
        assertEquals(f1,fridge.getFoodList().get(0));

        List<Food> temp = new ArrayList<>();
        temp.add(f1);

        fridge.remove(temp);
        assertTrue(fridge.getFoodList().isEmpty());
    }

    @Test
    public void testNextDay() {
        Food f1 = new Fruit();
        f1.setRemaining(1);
        Food f2 = new Vegetable();
        f2.setRemaining(2);

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