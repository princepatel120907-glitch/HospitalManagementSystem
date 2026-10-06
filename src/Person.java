// ================= PARENT CLASS: PERSON (Inheritance) =================
class Person {
    protected String name;
    protected String id;
    protected String contact;

    Person(String name, String id, String contact) {
        this.name = name;
        this.id = id;
        this.contact = contact;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Contact: " + contact);
    }

    // Getter methods
    String getName() { return name; }
    String getId() { return id; }
    String getContact() { return contact; }
}

