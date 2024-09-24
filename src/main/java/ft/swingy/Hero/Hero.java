package ft.swingy.Hero;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import ft.swingy.Artifacts.Artifact;
import ft.swingy.Artifacts.ArtifactBuilder;
import ft.swingy.Artifacts.ArtifactDirector;
import ft.swingy.Enemy.Enemy;

public class Hero {
    public String name;
    public String type;
    public int level;
    public int experience;
    public int attack;
    public int defense;
    public int hitPoints;
    public int maxHitPoints;
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

    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public int getExperience() {
        return this.experience;
    }
    public int getAttack() {
        return this.attack;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getHitPoints() {
        return this.hitPoints;
    }
    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }
    public int getNextLevelXp() {
        return this.level * 1000 + (this.level - 1) * (this.level - 1) * 450;
    }

    public int getLevel(){
        return this.level;
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
        this.attack += 15;
        this.defense += 10;
        this.maxHitPoints += 100;
        this.hitPoints = maxHitPoints;
        System.out.println("Level " + this.level + " reached !");
        printStats();
    }

    public void earnXp(Enemy enemy) {
        this.experience += enemy.getXp();
        //Leveling up is based on the following formula level*1000+(level-1)ˆ2 * 450
        //the power of 2 "ˆ2" is done by doing "x * x"
        if (this.experience >= this.level * 1000 + (this.level - 1) * (this.level - 1) * 450) {
            levelUp();
        }
    }

    private void generateArtifact(int level) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        Artifact droppedArtifact;
        ArtifactDirector director = new ArtifactDirector();

        if (random.nextInt(100) <= 10){
            droppedArtifact = director.randomArtifacts(new ArtifactBuilder(), level);
            System.out.println("You have found a " + droppedArtifact.getType() +" of quality :" + droppedArtifact.getQuality() + " !");
        }
    }

    private void takeDamage(int damage) {
        damage -= this.defense;
        if (damage <= 0) {
            damage = 0;
            System.out.println("Your defense is too high, the enemy can't damage you !!!");
        }
        else
            System.out.println("You have taken " + damage + " damage.       remaing health (You) : " + (this.hitPoints - damage) + "!");
        this.hitPoints -= damage;
    }

    public boolean tryToRun() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        if (random.nextInt(100) <= 50) {
            System.out.println("You have successfully ran away !");
            return true;
        }
        System.out.println("You have failed to run away, you must fight !");
        return fightSimulation(new Enemy(level));
    }

    public boolean fightSimulation(Enemy enemy) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        while (this.hitPoints > 0 && enemy.getHitPoints() > 0) {
            //5% chance of "missing" the attack
            if (random.nextInt(100) > 5){
                enemy.takeDamage(this.attack);
            }
            else
                System.out.println("You have missed your attack !");
            //Check if the enemy is dead
            if (enemy.getHitPoints() <= 0) {
                System.out.println("You have defeated the enemy and regenerated 75% of your max health !");
                this.hitPoints += (int)(this.maxHitPoints * 0.75);
                if (this.hitPoints > this.maxHitPoints)
                    this.hitPoints = this.maxHitPoints;
                System.out.println("You're now at " + this.hitPoints + " HP !");
                generateArtifact(enemy.getLevel());
                earnXp(enemy);
                return true;
            }
            //10% chance of "dodging" the attack
            if (random.nextInt(90) > 10) {
                takeDamage(enemy.getAttack());
            }
            else
            System.out.println("The enemy has missed his attack !");
            if (this.hitPoints <= 0) {
                System.out.println("You have been defeated by the enemy !");
                return false;
            }
        }
        return false;
    }

    public void save(){
        File file = new File("src/main/java/ft/swingy/save/saves.txt");
        try {
            file.createNewFile();
        }
        catch (Exception e) {
            System.out.println("Error occurred while creating save file: " + e.getMessage());
        }

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("Name:" + name + "\n");
            writer.write("Type:" + type + "\n");
            writer.write("Level:" + level + "\n");
            writer.write("Experience:" + experience + "\n");
            writer.write("Attack:" + attack + "\n");
            writer.write("Defense:" + defense + "\n");
            writer.write("HP:" + hitPoints + "\n");
            writer.close();
            System.out.println("Hero saved successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while saving stats: " + e.getMessage());
        }
    }
}
