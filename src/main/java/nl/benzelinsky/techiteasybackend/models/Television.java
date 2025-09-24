package nl.benzelinsky.techiteasybackend.models;

public class Television {
    private static int counter = 1;

    private int id;
    private String name;

    public Television(String name) {
        this.id = counter++;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
