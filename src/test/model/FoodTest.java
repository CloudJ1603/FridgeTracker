package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food f1;
    private Food f2;
    private Food f3;
    private Food f4;

    @BeforeEach
    public void setup() {
        f1 = new Vegetable();
        f2 = new Fruit();
        f3 = new Meat();
        f4 = new Leftover();
    }

    @Test
    public void testConstructor() {
        assertEquals("v", f1.getType());
        assertEquals("f", f2.getType());
        assertEquals("m", f3.getType());
        assertEquals("l", f4.getType());
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
    public void testNextDay() {
        f1.setRemaining(1);
        assertEquals(1,f1.getRemaining());
        f1.nextDay();
        assertEquals(0,f1.getRemaining());
        f1.nextDay();
        assertEquals(0,f1.getRemaining());
    }

}
