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
        this.hitPoints = 200 * level;
        this.attack = 30 * level;
        this.defense = 10 * level;
        this.xp = level * 350 + (level - 1) * 250;
        printStats();
    }

    private void printStats() {
        System.out.println("Level: " + level);
        System.out.println("HP: " + hitPoints);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("XP: " + xp);
    }

    public int getXp() {
        return xp;
    }

    //to generate artifacts
    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void takeDamage(int damage) {
        damage -= this.defense;
        if (damage <= 0) {
            damage = 0;
            System.out.println("The enemy's defense is too high, you can't damage it !!!");
        }
        else{
            this.hitPoints -= damage;
            if (this.hitPoints <= 0)
                this.hitPoints = 0;
            System.out.println("The enemy has taken " + damage + " damage.  remaing health (Mob) : " + (this.hitPoints - damage) + "!");
        }
        this.hitPoints -= damage;
    }
}
