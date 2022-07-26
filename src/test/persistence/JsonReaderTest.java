package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    private JsonReader reader;
    @Test
    void testReaderNonExistentFile() {
        reader = new JsonReader("./data/noSuchFile.json");
        try {
            Fridge fridge = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFridge() {
        reader = new JsonReader("./data/testReaderEmptyFridge.json");
        try {
            Fridge fridge = reader.read();
            assertEquals("My Fridge One", fridge.getName());
            assertEquals(0, fridge.getFoodList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFridge() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFridge.json");
        try {
            Fridge fridge = reader.read();
            assertEquals("My Fridge One", fridge.getName());
            List<Food> foods = fridge.getFoodList();
            assertEquals(2, foods.size());
            checkFood("apple", Category.FRUIT, 10, foods.get(0));
            checkFood("lettuce", Category.VEGETABLE, 20, foods.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}