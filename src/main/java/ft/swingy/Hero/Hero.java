package ft.swingy.Hero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
                if (this.artifacts[0] != null)
                    this.attack -= this.artifacts[0].getQuality();
                else{
                    this.attack += artifact.getQuality();
                    this.artifacts[0] = artifact;
                }
                break;
            case "Armor":
                if (this.artifacts[1] != null)
                    this.defense -= this.artifacts[1].getQuality();
                else{
                    this.defense += artifact.getQuality();
                    this.artifacts[1] = artifact;
                }
                break;
            case "Helm":
                if (this.artifacts[2] != null)
                    this.hitPoints -= this.artifacts[2].getQuality();
                else{
                    this.hitPoints += artifact.getQuality();
                    this.artifacts[2] = artifact;
                }
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
        if (this.experience >= this.level * 1000 + (this.level - 1) * (this.level - 1) * 450 && this.level < 100) {
            levelUp();
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
        return false;
    }

    public boolean fightSimulation() {
        Random random = new Random();
        Enemy enemy = new Enemy(level);
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

    public void deleteSave(){
        File file = new File("src/main/java/ft/swingy/save/saves.txt");
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        int skip = -1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                if (skip > 0){
                    System.out.println("Skipped: " + line);
                    skip--;
                    continue;
                }
                if (line.contains(this.name)){
                    System.out.println("Skipped: " + line);
                    skip = 6;
                    continue;
                }
                lines.add(line);
            }
            reader.close();
        }
        catch (Exception e) {
            System.out.println("Error occurred while deleting hero from save: " + e.getMessage());
        }
        if (skip > 0){
            System.out.println("Error: Reached EOF before deleting all mandatory lines, save file might be corrupted");
        }
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < lines.size(); i++){
                System.out.println("Writing: " + lines.get(i));
                writer.write(lines.get(i) + "\n");
            }
            writer.close();
        } catch (Exception e) {
        }
    }
}
