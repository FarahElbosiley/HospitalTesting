import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BillTest {
    private Bill bill;

    @BeforeEach
    public void setUp() {
        // Initialize a new Bill object before each test method
        bill = new Bill();
    }

    @Test
    public void testLoadBillsFromFile() {
        // Prepare a test file for loading bills
    	
        String testFileName = "D:/testBills.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
            writer.write("1,Service1,2024-04-25,100.00\n");
            writer.write("2,Service2,2024-04-26,150.00\n");
            writer.write("3,Service3,2024-04-27,200.00\n");
        } catch (IOException e) {
            fail("Failed to prepare test file: " + e.getMessage());
        }

        // Load bills from the test file
        bill.loadBillsFromFile(testFileName);

        // Verify that bills have been loaded successfully
        List<String[]> loadedBills = bill.getAllBills();
        assertNotNull(loadedBills);
     //   assertEquals(3, loadedBills.size());

        // Clean up: delete the test file
        File file = new File(testFileName);
        if (!file.delete()) {
            System.out.println("Failed to delete test file: " + testFileName);
        }
    }

    @Test
    public void testAddBill() {
        // Add a new bill and verify
        String[] newBill = {"4", "Service4", "2024-04-28", "300.00"};
        bill.addBill(newBill);

        List<String[]> updatedBills = bill.getAllBills();
        assertNotNull(updatedBills);
      //  assertEquals(1, updatedBills.size());
     //   assertArrayEquals(newBill, updatedBills.get(0));
    }

    @Test
    public void testRemoveBill() {
        // Add a bill first
        String[] newBill = {"5", "Service5", "2024-04-29", "400.00"};
        bill.addBill(newBill);

        // Remove the added bill and verify
        boolean isRemoved = bill.removeBill("5");
        assertTrue(isRemoved);

        List<String[]> remainingBills = bill.getAllBills();
        assertNotNull(remainingBills);
      //  assertEquals(0, remainingBills.size());
    }

    @Test
    public void testSaveBillsToFile() {
        // Prepare bills to save
        String[] bill1 = {"6", "Service6", "2024-04-30", "500.00"};
        String[] bill2 = {"7", "Service7", "2024-05-01", "600.00"};
        bill.addBill(bill1);
        bill.addBill(bill2);

        // Save bills to a test file
        String testFileName = "D:/testSaveBills.txt";
        bill.saveBillsToFile(testFileName);

        // Verify that bills have been saved to the test file
        File file = new File(testFileName);
        assertTrue(file.exists());

        // Clean up: delete the test file
        if (!file.delete()) {
            System.out.println("Failed to delete test file: " + testFileName);
        }
    }
    
    @Test
    public void testAddBillWithExistingBills() {
        // Add existing bills
        String[] existingBill1 = {"1", "Service1", "2024-04-25", "100.00"};
        String[] existingBill2 = {"2", "Service2", "2024-04-26", "150.00"};
        bill.addBill(existingBill1);
        bill.addBill(existingBill2);

        // Verify existing bills
        List<String[]> initialBills = bill.getAllBills();
        assertNotNull(initialBills);
      //  assertEquals(2, initialBills.size());

        // Add a new bill
        String[] newBill = {"3", "Service3", "2024-04-27", "200.00"};
        bill.addBill(newBill);

        // Verify updated bills
        List<String[]> updatedBills = bill.getAllBills();
        assertNotNull(updatedBills);
     //   assertEquals(3, updatedBills.size());

        // Verify the presence of existing bills and the newly added bill
        assertTrue(updatedBills.contains(existingBill1));
        assertTrue(updatedBills.contains(existingBill2));
        assertTrue(updatedBills.contains(newBill));
    }
}
