package prescription;

public class Medicine {
    private String name;
    private String type;
    private double price;

    public Medicine(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}