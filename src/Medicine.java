// ================= CLASS: MEDICINE =================
class Medicine {
    String name;
    double price;

    Medicine(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters
    String getName() { return name; }
    double getPrice() { return price; }

    // Setters
    void setName(String name) { this.name = name; }
    void setPrice(double price) { this.price = price; }
}