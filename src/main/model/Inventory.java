package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;

// An inventory of clothing with a list of clothes
public class Inventory implements Writable {
    ArrayList<Clothing> inventory;

    // Creates a new list of clothes to be used as an inventory.
    public Inventory() {
        inventory = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a clothing item to the inventory
    public void addClothing(Clothing c) {
        inventory.add(c);
        EventLog.getInstance().logEvent(new Event("Added " + c.getName() + " to the inventory."));
    }

    // REQUIRES: clothing to be removed must be in the inventory already
    // MODIFIES: this
    // EFFECTS: searches for and removes a piece of clothing from the inventory
    public void removeClothing(String name, double price, String type) {
        Clothing toRemove = findClothing(name, type);
        inventory.remove(toRemove);
        EventLog.getInstance().logEvent(new Event("Removed " + toRemove.getName() + " from the inventory."));
    }

    // EFFECTS: Searches the inventory for a piece of clothing
    public Clothing findClothing(String name, String type) {
        Clothing result = null;
        for (Clothing c: inventory) {
            if (name.equals(c.getName()) && type.equals(c.getType())) {
                result = c;
            }
        }
        return result;
    }

    public void reverseOrder() {
        Collections.reverse(inventory);
        EventLog.getInstance().logEvent(new Event("Reversed the inventory's order."));
    }

    // Getters

    public Clothing getClothingAt(int index) {
        return inventory.get(index);
    }

    public ArrayList<Clothing> getInventory() {
        return inventory;
    }

    public int getSize() {
        return inventory.size();
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("clothes", clothesToJson());
        return json;
    }

    private JSONArray clothesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Clothing c : inventory) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
