package ft.swingy.Hero;

public class Hero {
    public String name;
    public String type;
    public int level;
    public int experience;
    public int attack;
    public int defense;
    public int hitPoints;

    public Hero(){};

    public void printStats() {
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Level: " + level);
        System.out.println("Experience: " + experience);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("HP: " + hitPoints);
    }
}
