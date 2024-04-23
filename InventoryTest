import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InventoryTest {
    private static Inventory inventory;

    @BeforeAll
    public static void setUp() {
        inventory = new Inventory();
        inventory.loadInventoryFromFile("D:/test_inventory.txt");
    }
    
    @AfterAll
    public static void tearDown() {
        inventory.saveInventoryToFile("D:/test_inventory.txt");
        inventory = null;
    }

    @Test
    public void testSaveAndLoadInventory() throws IOException {
        // Adding items to inventory
        inventory.addItemToInventory("Item1", 5);
        inventory.addItemToInventory("Item2", 10);

        // Saving inventory to file
        String fileName = "D:/test_inventory.txt";
        inventory.saveInventoryToFile(fileName);

        // Creating a new instance of Inventory to load from the file
        Inventory newInventory = new Inventory();
        newInventory.loadInventoryFromFile(fileName);

        // Asserting the loaded inventory
        assertEquals(5, newInventory.getInventorySize());
        // increase the number by 2 when rerun 
        assertEquals(5, newInventory.getItemQuantity("Item1"));
        assertEquals(10, newInventory.getItemQuantity("Item2"));

        // Deleting the test file
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
    @Test
    public void testAddItemToInventory() {
        inventory.addItemToInventory("Gloves", 700);
        inventory.addItemToInventory("Wheelchairs", 10);
        inventory.addItemToInventory("PainKillers", 400); // Adding more to an existing item

        // increase the number by 2 when rerun 
        assertEquals(10, inventory.findItem("Wheelchairs").getQuantity());
        assertEquals("Gloves", inventory.findItem("Gloves").getName());
    }
    
}
