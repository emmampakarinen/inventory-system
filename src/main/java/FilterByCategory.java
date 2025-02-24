import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilterByCategory {
    private final InventoryManager inventoryManager;

    public FilterByCategory(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void execute() {
        List<String> categories = new ArrayList<>();
        categories = inventoryManager.getCategories();
        System.out.println("");
        if (categories.size() == 0) {
            System.out.println("No categories!");
            return;
        }

        System.out.println("Available categories:");
        int j = 1;
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(j + ") " + categories.get(i));
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Category number: ");
        int category = scanner.nextInt();
        category--; // subtracting one to get the correct index for list

        Boolean found = false;
        System.out.println("Products in Category **" + categories.get(category) + "**:");
        for (Product product : inventoryManager.findProductsByCategory(categories.get(category))) {
                System.out.println(product.display());
                found = true;
            }
        
        if (!found) {
            System.out.println("No products in given category.");
        }
    }
}
