import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class IntegrationTest {
    private InventoryManager manager;
    private ViewProducts viewProducts;
    private CheckLowStock checkLowStock;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // redirecting system output to capture printed statements
        System.setOut(new PrintStream(outputStreamCaptor));

        // initizalizing and clearing InventoryManager with test products
        manager = new InventoryManager();
        manager.clearInventory();
        manager.addProduct(new Product(1, "Laptop", "Electronics", 70, 100));
        manager.addProduct(new Product(2, "Mouse","Electronics", 50, 30));
        manager.addProduct(new Product(3, "Mouse mat","Electronics", 6, 5));
        manager.addProduct(new Product(4, "Charger","Electronics", 9, 10));

        viewProducts = new ViewProducts(manager);
        checkLowStock = new CheckLowStock(manager);
    }
        
    // 1st integration test between InvetoryManager and ViewProducts: testing that adding products using inventorymanager also works in ViewProducts-class
    @Test
    void testExecuteWithProducts() {
        viewProducts.execute();

        String expectedOutput = "Inventory:\nID: 1, Name: Laptop, Category: Electronics, Quantity: 70, Price: 100.0\nID: 2, Name: Mouse, Category: Electronics, Quantity: 50, Price: 30.0\nID: 3, Name: Mouse mat, Category: Electronics, Quantity: 6, Price: 5.0\nID: 4, Name: Charger, Category: Electronics, Quantity: 9, Price: 10.0\n";

        // asser output matches expected string which has been modified to remove extra white space
        assertEquals(expectedOutput.replaceAll("\\s+", " ").trim(),
             outputStreamCaptor.toString().replaceAll("\\s+", " ").trim());
    }

    // 2nd integration test between InvetoryManager and CheckLowStock: testing that low stock products added to inventory are shown in the CheckLowStock class
    @Test
    void testCheckLowStock() {
        checkLowStock.execute();

        String expectedOutput = "Low Stock Products (Quantity < 10):\nID: 3, Name: Mouse mat, Category: Electronics, Quantity: 6, Price: 5.0\nID: 4, Name: Charger, Category: Electronics, Quantity: 9, Price: 10.0\n";

        // asser output matches expected string which has been modified to remove extra white space
        assertEquals(expectedOutput.replaceAll("\\s+", " ").trim(),
                outputStreamCaptor.toString().replaceAll("\\s+", " ").trim());
    }

    // Integration tests for new feature. 
    // Testing to add product with category and fetching available categories
    @Test
    void testAddProductAndGetCategories() {
        manager.clearInventory();
        Product product = new Product(102, "Bottle", "Utilities", 10, 7);
        manager.addProduct(product);
        List<String> categories = new ArrayList<String>();
        categories = manager.getCategories();

        List<String> expectedCategories = new ArrayList<String>();
        expectedCategories.add("Utilities");

        assertEquals(expectedCategories, categories);
    }

    // Testing to remove products within a single category, and checking the category is not available anymore
    @Test
    void testRemoveProductDoesNotBreakCategories() {
        manager.addProduct(new Product(104, "Notebook", "Stationery", 20, 5));
        manager.addProduct(new Product(105, "Pen", "Stationery", 50, 1));

        assertTrue(manager.getCategories().contains("Stationery"));

        // removing stationery products
        manager.removeProduct(104); 
        manager.removeProduct(105);

        // checking the category cant be found anymore
        assertFalse(manager.getCategories().contains("Stationery"));
    }

}
