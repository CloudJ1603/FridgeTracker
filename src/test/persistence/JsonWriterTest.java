package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    private Fridge fridge;

    @BeforeEach
    public void setup() {
        fridge = new Fridge("My Fridge One");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFridge() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFridge.json");
            writer.open();
            writer.write(fridge);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFridge.json");
            fridge = reader.read();
            assertEquals("My Fridge One", fridge.getName());
            assertEquals(0, fridge.getFoodList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFridge() {
        try {
//            wr.addThingy(new Thingy("saw", Category.METALWORK));
//            wr.addThingy(new Thingy("needle", Category.STITCHING));
            fridge.putInFridge(new Food("apple", Category.FRUIT, 10));
            fridge.putInFridge(new Food("lettuce", Category.VEGETABLE, 20));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFridge.json");
            writer.open();
            writer.write(fridge);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFridge.json");
            fridge = reader.read();
            assertEquals("My Fridge One", fridge.getName());
            List<Food> foods = fridge.getFoodList();
            assertEquals(2, foods.size());
            checkFood("apple", Category.FRUIT, 10, foods.get(0));
            checkFood("lettuce", Category.VEGETABLE, 20, foods.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}