
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class InventoryManagerTest {
    InventoryManager manager = new InventoryManager();

    @BeforeEach
    public void initTest() {
        Product product = new Product(101, "Laptop", "Electronics", 10, 70);
        manager.addProduct(product);
    }


    // 1st unit test for InventoryManager. Testing to add and find product in the inventory
    @Test
    public void testAddAndFindProduct() {
        Product foundProduct = manager.findProductById(101);
        assertNotNull(foundProduct);
        assertEquals("Laptop", foundProduct.getName());
    }

    
    // 2nd unit test for InventoryManager. Testing to remove the prodcut
    @Test
    void testRemoveProduct() {
        InventoryManager manager = new InventoryManager();
        manager.removeProduct(101);

        Product foundProduct = manager.findProductById(101);
        assertNull(foundProduct);
    }

    // 3rd unit test for Product. Testing to fetch price 
    @Test
    void testProductGetPrice() {
        Product product = new Product(102, "Bottle", "Utilities", 10, 7);
        double price = product.getPrice();
        assertEquals(7.0, price);
    }
}
