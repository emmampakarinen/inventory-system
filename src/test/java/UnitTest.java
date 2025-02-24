
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class UnitTest {
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
    
    // 2nd unit test for InventoryManager. Testing to remove the product
    @Test
    void testRemoveProduct() {
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

    // 4th unit test. Test to fing a product that does not exist
    @Test
    void testFindNonExistentProduct() {
        Product foundProduct = manager.findProductById(999);
        assertNull(foundProduct);
    }


    // Unit tests for the new feature (categories for products)
    @Test
    void testProductGetCategory() {
        Product product = new Product(102, "Bottle", "Utilities", 10, 7);
        String category = product.getCategory();
        assertEquals("Utilities", category);
    }

    @Test
    void testListCategories() {
        Product product = new Product(102, "Bottle", "Utilities", 10, 7);
        manager.addProduct(product);
    
        List<String> categoriesAvailable = manager.getCategories();
    
        assertTrue(categoriesAvailable.contains("Electronics"));
        assertTrue(categoriesAvailable.contains("Utilities"));
    }

    @Test
    void testAddNewCategory() {
        manager.addProduct(new Product(103, "Desk Lamp", "Office Supplies", 15, 25));

        List<String> categoriesAvailable = manager.getCategories();
        
        assertTrue(categoriesAvailable.contains("Office Supplies"));
    }

}
