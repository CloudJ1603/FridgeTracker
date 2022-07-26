package persistence;

import model.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkFood(String name, Category category, int remaining, Food food) {
        assertEquals(name, food.getName());
        assertEquals(category, food.getCategory());
        assertEquals(remaining, food.getRemaining());
    }
}
