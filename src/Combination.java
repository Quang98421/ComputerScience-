public class Combination extends Item {
    public Combination() {
        super("combination", "A small piece of paper with numbers scribbled on it: 3-15-27");
    }

    public void use() {
        Game.print("If you find a safe, try opening it!");
    }
}