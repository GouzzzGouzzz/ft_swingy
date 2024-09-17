package ft.swingy.Enemy;

public class Enemy {
    //Have to check algorythm before doing this part
    private int hitPoints;
    private int attack;
    private int defense;
    private int level;
    private int xp;

    public Enemy(int level) {
        this.level = level;
        this.hitPoints = 100 * level;
        this.attack = 10 * level;
        this.defense = 10 * level;
        this.xp = level * 350 + (level - 1) * 250;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }
}
