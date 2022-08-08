package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food f1;

    @BeforeEach
    public void setup() {
        Category c1 = Category.values()[0];
        f1 = new Food("lettuce",c1,10);
    }

    @Test
    public void testConstructor() {
        assertEquals(Category.values()[0], f1.getCategory());
        assertEquals("lettuce",f1.getName());
        assertEquals(10,f1.getRemaining());
    }

    @Test
    public void testSetName() {
        f1.setName("lettuce");
        assertEquals("lettuce", f1.getName());
    }

    @Test
    public void testSetRemaining() {
        f1.setRemaining(5);
        assertEquals(5,f1.getRemaining());
    }

    @Test
    public void testSetCategory() {
        f1.setCategory(Category.MEAT);
        assertEquals(Category.MEAT, f1.getCategory());
    }

    @Test
    public void testNextDay() {
        f1.setRemaining(1);
        assertEquals(1,f1.getRemaining());
        f1.nextDay();
        assertEquals(0,f1.getRemaining());
        f1.nextDay();
        assertEquals(0,f1.getRemaining());
    }

}
