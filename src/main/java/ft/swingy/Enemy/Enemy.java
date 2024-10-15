package ft.swingy.Enemy;

import java.util.ArrayList;

public class Enemy {
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
    }

    public int getXp() {
        return xp;
    }

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

    public void takeDamage(int damage, ArrayList<String> combatLogs) {
        damage -= this.defense;
        if (damage <= 0) {
            damage = 0;
            combatLogs.add("The enemy's defense is too high, you can't damage it !!!");
        }
        else{
            this.hitPoints -= damage;
            if (this.hitPoints <= 0)
                this.hitPoints = 0;
            combatLogs.add("The enemy has taken " + damage + " damage.  remaing health (Mob) : " + (this.hitPoints) + "!");
        }
        this.hitPoints -= damage;
    }
}
