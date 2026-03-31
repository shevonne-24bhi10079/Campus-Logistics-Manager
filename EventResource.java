import java.io.Serializable;

/**
 * Represents a physical resource used during a campus event.
 * Implements Serializable to allow saving to a data file.
 */
public class EventResource implements Serializable {
    private String itemName;
    private int quantity;
    private String category;

    public EventResource(String itemName, int quantity, String category) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.category = category;
    }

    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    
    public void updateQuantity(int change) {
        this.quantity += change;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-18s | Available: %d", 
                             category.toUpperCase(), itemName, quantity);
    }
}