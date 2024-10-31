public class Safe extends Item {
    private boolean isOpen;

    public Safe() {
        super("safe", "A heavy wall safe with a combination lock. It looks quite secure.");
        this.isOpen = false;
    }

    
    public void open() {
        if (isOpen) {
            Game.print("The safe is already open and empty.");
            return;
        }

        Item combination = Game.getInventoryItem("combination");
        
        if (combination != null) {
            isOpen = true;
            Game.print("Using the combination, you open the safe and find a diamond inside! Naturally, you pocket the diamond.");
            
            Item diamond = new Item("diamond", "A brilliant cut diamond that sparkles magnificently in the light. It must be worth a fortune!");
            Game.getInventory().add(diamond);
        } else {
            Game.print("The safe is locked and you don't have the combination.");
        }
    }

   
    public String getDescription() {
        if (isOpen) {
            return super.getDescription() + " The safe is open and empty.";
        }
        return super.getDescription();
    }
}