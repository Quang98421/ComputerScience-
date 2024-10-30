import java.util.HashMap;


public class Room {
    private String description;
    
    private Room east;
    private Room west;
    private Room north;
    private Room south;
    private Room up;
    private Room down;
    
    private HashMap<String, Item> items = new HashMap<>();

    
    public Room(String description) {
        this.description = description;
    }

   
    public Room getExit(char direction) {
        switch(direction) {
            case 'e': return east;
            case 'w': return west;
            case 'n': return north;
            case 's': return south;
            case 'u': return up;
            case 'd': return down;
            default: return null;
        }
    }

 
    public void addExit(Room room, char direction) {
        switch(direction) {
            case 'e': east = room; break;
            case 'w': west = room; break;
            case 'n': north = room; break;
            case 's': south = room; break;
            case 'u': up = room; break;
            case 'd': down = room; break;
        }
    }

 
    public void addItem(Item item) {
        items.put(item.getName().toLowerCase(), item);
    }

 
    public Item getItem(String itemName) {
        return items.get(itemName.toLowerCase());
    }

    public void removeItem(String itemName) {
        items.remove(itemName.toLowerCase());
    }

 
    public String toString() {
        StringBuilder sb = new StringBuilder(description);
        if (!items.isEmpty()) {
            sb.append("\nYou see:");
            for (Item item : items.values()) {
                sb.append("\n- ").append(item.getName());
            }
        }
        return sb.toString();
    }
}