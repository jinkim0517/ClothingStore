package model;

import java.util.ArrayList;

// A footwear clothing item with a name, price, category, list of sizes and number of sales
public class Footwear extends Clothing {
    private ArrayList<String> sizes;

    // REQUIRES: non-empty name, 0 <= price
    // EFFECTS: creates a new piece of footwear with a given name and price
    public Footwear(String name, double price) {
        super(name, price);
        sizes = new ArrayList<>();
        initializeSizes();
    }

    // MODIFIES: this
    // EFFECTS: initializes sizes to appropriate values
    private void initializeSizes() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 6; i <= 14; i++) {
            sizes.add(Integer.toString(i));
        }
    }

    @Override
    // REQUIRES: size is in sizes
    // MODIFIES: this
    // EFFECTS: removes availability for a specific size.
    public void removeSize(String size) {
        sizes.remove(size);
    }

    @Override
    // REQUIRES: size is not already in sizes
    // MODIFIES: this
    // EFFECTS: adds availability for a specific size
    public void addSize(String size) {
        sizes.add(size);
    }

    // Getters

    @Override
    public ArrayList<String> getSizes() {
        return sizes;
    }

    @Override
    public String getType() {
        return "Footwear";
    }
}
