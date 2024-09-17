package ft.swingy.Hero;

import ft.swingy.Artifacts.Artifact;
import ft.swingy.Enemy.Enemy;

public class Hero {
    public String name;
    public String type;
    public int level;
    public int experience;
    public int attack;
    public int defense;
    public int hitPoints;
    final Artifact[] artifacts = new Artifact[3];
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

    public void equipArtifact(Artifact artifact) {
        switch (artifact.getType()) {
            case "Weapon":
                this.attack += artifact.getQuality();
                this.artifacts[0] = artifact;
                break;
            case "Armor":
                this.defense += artifact.getQuality();
                this.artifacts[1] = artifact;
                break;
            case "Helm":
                this.hitPoints += artifact.getQuality();
                this.artifacts[2] = artifact;
                break;
            default:
                break;
        }
    }

    private void levelUp() {
        this.level++;
        this.attack += 10;
        this.defense += 10;
        this.hitPoints += 100;
    }

    public void earnXp(Enemy enemy) {
        this.experience += enemy.getXp();
        //Leveling up is based on the following formula level*1000+(level-1)ˆ2 * 450
        //the power of 2 "ˆ2" is done by doing "x * x"
        if (this.experience >= this.level * 1000 + (this.level - 1) * (this.level - 1) * 450) {
            levelUp();
        }
    }
}
