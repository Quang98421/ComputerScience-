import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static List<Item> inventory = new ArrayList<>();
    private static Room currentRoom;
    private static Scanner scanner = new Scanner(System.in);

    public static void print(Object obj) {
        System.out.println(obj.toString());
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static Item getInventoryItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public static List<Item> getInventory() {
        return inventory;
    }

    public static void main(String[] args) {
        currentRoom = World.buildWorld();
        
        print("Welcome to the Haunted Lake House!");
        print("\nStarting on the front porch:");
        print(currentRoom);
        
        while (true) {
            System.out.print("\nEnter a command (n/s/e/w/u/d/take/use/open/look/i/quit): ");  // Updated command list
            String input = scanner.nextLine().toLowerCase();
            String[] command = input.split(" ", 2);
            
            if (command[0].equals("quit")) {
                print("Thanks for playing. Goodbye!");
                break;
            }
            processCommand(command);
        }
    }
   
    private static void processCommand(String[] command) {
        switch (command[0]) {
            case "n":
            case "s":
            case "e":
            case "w":
            case "u":
            case "d":
                move(command[0].charAt(0));
                break;
            case "take":
                if (command.length < 2) {
                    print("Take what?");
                } else {
                    takeItem(command[1]);
                }
                break;
            case "look":
                if (command.length < 2) {
                    print("Look at what?");
                } else {
                    lookAtItem(command[1]);
                }
                break;
            case "use":
                if (command.length < 2) {
                    print("Use what?");
                } else {
                    useItem(command[1]);
                }
                break;
            case "open":
                if (command.length < 2) {
                    print("Open what?");
                } else {
                    openItem(command[1]);
                }
                break;
            case "i":
                showInventory();
                break;
            default:
                print("I don't understand that command.");
        }
    }

    private static void useItem(String itemName) {
        Item inventoryItem = getInventoryItem(itemName);
        if (inventoryItem != null) {
            inventoryItem.use();
            return;
        }
        
        Item roomItem = currentRoom.getItem(itemName);
        if (roomItem != null) {
            roomItem.use();
        } else {
            print("You don't see any " + itemName + " here.");
        }
    }

    private static void openItem(String itemName) {
        Item inventoryItem = getInventoryItem(itemName);
        if (inventoryItem != null) {
            inventoryItem.open();
            return;
        }
        
        Item roomItem = currentRoom.getItem(itemName);
        if (roomItem != null) {
            roomItem.open();
        } else {
            print("You don't see any " + itemName + " here.");
        }
    }

    private static void move(char direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            print(currentRoom);
        } else {
            print("You can't go that way.");
        }
    }
 
    private static void takeItem(String itemName) {
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            inventory.add(item);
            currentRoom.removeItem(itemName);
            print("You took the " + item.getName() + ".");
        } else {
            print("There's no " + itemName + " here.");
        }
    }
  
    private static void lookAtItem(String itemName) {
        Item inventoryItem = getInventoryItem(itemName);
        if (inventoryItem != null) {
            print(inventoryItem.getDescription());
            return;
        }
        
        Item roomItem = currentRoom.getItem(itemName);
        if (roomItem != null) {
            print(roomItem.getDescription());
        } else {
            print("You don't see any " + itemName + " here.");
        }
    }
 
    private static void showInventory() {
        if (inventory.isEmpty()) {
            print("You are carrying nothing.");
        } else {
            print("You are carrying:");
            for (Item item : inventory) {
                print("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }
}