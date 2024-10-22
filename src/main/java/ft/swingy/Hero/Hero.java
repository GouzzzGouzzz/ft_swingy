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
    private String name;
    private String type;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;
    private int maxHitPoints;
    private ArrayList<String> combatLogs;
    final Artifact[] artifacts = new Artifact[3];
    public Hero(){};

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

    public Artifact getArtifact(int index){
        return this.artifacts[index];
    }

    public int getLevel(){
        return this.level;
    }

    public ArrayList<String> getCombatLogs(){
        return this.combatLogs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
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

    public void regenerate(){
        this.hitPoints = this.maxHitPoints;
    }

    private void levelUp() {
        this.level++;
        this.attack += 15;
        this.defense += 10;
        this.maxHitPoints += 100;
        this.hitPoints = maxHitPoints;
        combatLogs.add("Level " + this.level + " reached !");
    }

    public void earnXp(Enemy enemy) {
        this.experience += enemy.getXp();
        //Leveling up is based on the following formula level*1000+(level-1)ˆ2 * 450
        //the power of 2 "ˆ2" is done by doing "x * x"
        if (this.experience >= getNextLevelXp()) {
            levelUp();
        }
    }

    private void takeDamage(int damage) {
        damage -= this.defense;
        if (damage <= 0) {
            damage = 0;
            combatLogs.add("Your defense is too high, the enemy can't damage you !");
        }
        else{
            this.hitPoints -= damage;
            if (this.hitPoints < 0)
                this.hitPoints = 0;
            combatLogs.add("You have taken " + damage + " damage.       remaing health (You) : " + (this.hitPoints) + "!");
        }
    }

    public boolean tryToRun() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        combatLogs = new ArrayList<String>();
        if (random.nextInt(100) <= 50) {
            combatLogs.add("You have successfully ran away !");
            return true;
        }
        combatLogs.add("You have failed to run away, you must fight !");
        return false;
    }

    public boolean fightSimulation() {
        Random random = new Random();
        Enemy enemy = new Enemy(level);

        random.setSeed(System.currentTimeMillis());
        combatLogs = new ArrayList<String>();
        while (this.hitPoints > 0 && enemy.getHitPoints() > 0) {
            //5% chance of "missing" the attack
            if (random.nextInt(100) > 5){
                enemy.takeDamage(this.attack, combatLogs);
            }
            else
                combatLogs.add("You have missed your attack !");
            //Check if the enemy is dead
            if (enemy.getHitPoints() <= 0) {
                combatLogs.add("You have defeated the enemy and regenerated 75% of your max health !");
                this.hitPoints += (int)(this.maxHitPoints * 0.75);
                if (this.hitPoints > this.maxHitPoints)
                    this.hitPoints = this.maxHitPoints;
                combatLogs.add("You're now at " + this.hitPoints + " HP !");
                earnXp(enemy);
                return true;
            }
            //10% chance of "dodging" the attack
            if (random.nextInt(90) > 10) {
                takeDamage(enemy.getAttack());
            }
            else
                combatLogs.add("The enemy has missed his attack !");
            if (this.hitPoints <= 0) {
                combatLogs.add(name + " has been defeated by the enemy !");
                return false;
            }
        }
        return false;
    }

    private void addStatsToList(ArrayList<String> lines){
        lines.add("Name:" + name);
        lines.add("Type:" + type);
        lines.add("Level:" + level);
        lines.add("Experience:" + experience);
        lines.add("Attack:" + attack);
        lines.add("Defense:" + defense);
        lines.add("HP:" + hitPoints);
        if (artifacts[0] != null)
            lines.add("Weapon:" + artifacts[0].getQuality());
        else
            lines.add("Weapon:0");
        if (artifacts[1] != null)
            lines.add("Armor:" + artifacts[1].getQuality());
        else
            lines.add("Armor:0");
        if (artifacts[2] != null)
            lines.add("Helm:" + artifacts[2].getQuality());
        else
            lines.add("Helm:0");
    }

    public void save(){
        File file = new File("src/main/java/ft/swingy/save/saves.txt");
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        int skip;

        skip = -1;
        try {
            file.createNewFile();
        }
        catch (Exception e) {
            System.out.println("Error occurred while creating save file: " + e.getMessage());
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null){
                if (skip > 0){
                    skip--;
                    continue;
                }
                if (line.contains(this.name) && skip == -1){
                    skip = 9;
                    addStatsToList(lines);
                }
                else
                    lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving stats: " + e.getMessage());
        }
        if (skip == -1) // new hero
            addStatsToList(lines);
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < lines.size(); i++){
                writer.write(lines.get(i) + "\n");
            }
            writer.close();
        } catch (Exception e) {
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
                    skip--;
                    continue;
                }
                if (line.contains(this.name) && skip == -1){
                    skip = 9;
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
            System.out.println("Potential Error: Reached EOF before deleting all mandatory lines, save file might be corrupted");
        }
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < lines.size(); i++){
                writer.write(lines.get(i) + "\n");
            }
            writer.close();
        } catch (Exception e) {
        }
    }
}
