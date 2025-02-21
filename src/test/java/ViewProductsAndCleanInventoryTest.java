import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ViewProductsAndCleanInventoryTest {
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
        manager.addProduct(new Product(1, "Laptop", 70, 100));
        manager.addProduct(new Product(2, "Mouse", 50, 30));
        manager.addProduct(new Product(3, "Mouse mat", 6, 5));
        manager.addProduct(new Product(4, "Charger", 9, 10));

        viewProducts = new ViewProducts(manager);
        checkLowStock = new CheckLowStock(manager);
    }
        
    // 1st integration test between InvetoryManager and ViewProducts: testing that adding products using inventorymanager also works in ViewProducts-class
    @Test
    void testExecuteWithProducts() {
        viewProducts.execute();

        String expectedOutput = "Inventory:\nID: 1, Name: Laptop, Quantity: 70, Price: 100.0\nID: 2, Name: Mouse, Quantity: 50, Price: 30.0\nID: 3, Name: Mouse mat, Quantity: 6, Price: 5.0\nID: 4, Name: Charger, Quantity: 9, Price: 10.0\n";

        // asser output matches expected string which has been modified to remove extra white space
        assertEquals(expectedOutput.replaceAll("\\s+", " ").trim(),
             outputStreamCaptor.toString().replaceAll("\\s+", " ").trim());
    }

        // 1st integration test between InvetoryManager and CheckLowStock: testing that low stock products added to inventory are shown from the CheckLowStock class
        @Test
        void testCheckLowStock() {
            checkLowStock.execute();
    
            String expectedOutput = "Low Stock Products (Quantity < 10):\nID: 3, Name: Mouse mat, Quantity: 6, Price: 5.0\nID: 4, Name: Charger, Quantity: 9, Price: 10.0\n";
    
            // asser output matches expected string which has been modified to remove extra white space
            assertEquals(expectedOutput.replaceAll("\\s+", " ").trim(),
                 outputStreamCaptor.toString().replaceAll("\\s+", " ").trim());
        }



}
