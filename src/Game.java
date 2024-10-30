import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {
 
    private static List<Item> inventory = new ArrayList<>();
    
    private static Room currentRoom;
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        currentRoom = World.buildWorld();
        
        System.out.println("Welcome to the Haunted Lake House!");
        System.out.println("\nStarting on the front porch:");
        System.out.println(currentRoom);
        
        while (true) {
            System.out.print("\nEnter a command (n/s/e/w/u/d/take/look/i/quit): ");
            String input = scanner.nextLine().toLowerCase();
            String[] command = input.split(" ", 2);
            
            if (command[0].equals("quit")) {
                System.out.println("Thanks for playing. Goodbye!");
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
                    System.out.println("Take what?");
                } else {
                    takeItem(command[1]);
                }
                break;
            case "look":
                if (command.length < 2) {
                    System.out.println("Look at what?");
                } else {
                    lookAtItem(command[1]);
                }
                break;
            case "i":
                showInventory();
                break;
            default:
                System.out.println("I don't understand that command.");
        }
    }

   
    private static void move(char direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println(currentRoom);
        } else {
            System.out.println("You can't go that way.");
        }
    }

 
    private static void takeItem(String itemName) {
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            inventory.add(item);
            currentRoom.removeItem(itemName);
            System.out.println("You took the " + item.getName() + ".");
        } else {
            System.out.println("There's no " + itemName + " here.");
        }
    }

  
    private static void lookAtItem(String itemName) {
        // First check if the item is in inventory
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println(item.getDescription());
                return;
            }
        }
        
  
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            System.out.println(item.getDescription());
        } else {
            System.out.println("You don't see any " + itemName + " here.");
        }
    }

 
    private static void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("You are carrying nothing.");
        } else {
            System.out.println("You are carrying:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }
}