public class Product {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private double price;

    public Product(int id, String name, String category, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + category + "," + quantity + "," + price;
    }


    public static Product fromString(String data) {
        String[] parts = data.split(",");
        return new Product(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Integer.parseInt(parts[3]),
                Double.parseDouble(parts[4])
        );
    }

    public String display() {
        return "ID: " + id + ", Name: " + name + ", Category: " + category + ", Quantity: " + quantity + ", Price: " + price;
    }
}
