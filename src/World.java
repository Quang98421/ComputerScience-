
public class World {
    
    public static Room buildWorld() {
      
        Room frontPorch = new Room("You're on the front porch of the lake house.");
        Room sunRoom = new Room("You're in a chilly sun room with large, grimy windows.");
        Room livingRoom = new Room("You're in the old living room.");
        Room kitchen = new Room("You're in the messy kitchen.");
        Room bedroom = new Room("You're in a dark bedroom.");
        Room attic = new Room("You're in the cluttered attic.");
        Room basement = new Room("You're in the damp basement.");
        Room backyard = new Room("You're in the overgrown backyard by the lake.");

      
        frontPorch.addExit(sunRoom, 'n');
        sunRoom.addExit(frontPorch, 's');
        sunRoom.addExit(livingRoom, 'n');
        sunRoom.addExit(kitchen, 'e');
        livingRoom.addExit(sunRoom, 's');
        livingRoom.addExit(bedroom, 'w');
        livingRoom.addExit(attic, 'u');
        livingRoom.addExit(basement, 'd');
        kitchen.addExit(sunRoom, 'w');
        kitchen.addExit(backyard, 'e');
        bedroom.addExit(livingRoom, 'e');
        attic.addExit(livingRoom, 'd');
        basement.addExit(livingRoom, 'u');
        backyard.addExit(kitchen, 'w');

        frontPorch.addItem(new Item("key", "An old rusty key"));
        livingRoom.addItem(new Item("book", "A dusty old book"));
        attic.addItem(new Item("lantern", "An antique oil lantern"));
        
        basement.addItem(new Item("shovel", "A rusted garden shovel"));
        basement.addItem(new Item("box", "A mysterious wooden box covered in strange symbols"));
        basement.addItem(new Item("bottle", "An old glass bottle with some liquid still inside"));

        return frontPorch;
    }
}