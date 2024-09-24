package ft.swingy.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;

import ft.swingy.Hero.Hero;
import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirector;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class Game {
    private Hero hero = null;
    Scanner read;

    private void printHeroSaved(File savFile) {
        int id = 0;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(savFile));
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (line.contains("Name:")){
                    System.out.println(id + " | " + line.substring(5));
                    id++;
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Error: Could not read save file");
        }
    }

    private boolean loadHero() {
        File saveFile = new File("src/main/java/ft/swingy/save/saves.txt");
        LoaderBean loader = new LoaderBean();
        HeroBuilder builder = new HeroBuilder();

        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<LoaderBean>> violations;
        if (!saveFile.exists()) {
            return false;
        }
        while (true) {
            System.out.println("Do you want to load a hero ? (yes/no)");
            loader.setloadChoice(read.nextLine());
            violations = validator.validate(loader);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<LoaderBean> violation : violations) {
                    System.out.println(violation.getMessage());
                }
                continue;
            }
            break;
        }
        if (loader.getloadChoice().equals("no")) {
            return false;
        }
        while (true) {
            System.out.println("Enter the ID of the hero you want to load :");
            printHeroSaved(saveFile);
            loader.setID(read.nextInt());
            violations = validator.validate(loader);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<LoaderBean> violation : violations) {
                    System.out.println(violation.getMessage());
                }
                continue;
            }
            hero = HeroDirector.loadFromFile(builder, loader.getID());
            if (hero == null) {
                //probleme
                break;
            }
        }
        return true;
    }

    private void printStatsAtIndex(int index){
        String[] stats = new String[7];
        stats[0] = "    Name: " + hero.getName();
        stats[1] = "    Type: " + hero.getType();
        stats[2] = "    Level: " + hero.getLevel();
        stats[3] = "    Experience: " + hero.getExperience();
        stats[4] = "    Attack: " + hero.getAttack();
        stats[5] = "    Defense: " + hero.getDefense();
        stats[6] = "    HP: " + hero.getHitPoints();
        System.out.print(stats[index]);
    }

    private void printMapWithHeroStats(GameMap map){
        final int renderDistance = 13;
        int startX = (map.size / 2) - renderDistance;
        int startY = (map.size / 2) - renderDistance;
        int statsLine = 0;
        //https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_RED_TEXT = "\033[1;91m";
        final String BLACK_BOLD_BRIGHT = "\033[1;90m";

        if (startX < 0)
            startX = 0;
        if (startY < 0)
            startY = 0;
        for(int i = startX; i < map.size / 2 + renderDistance && i < map.size; i++){
            for(int j = startY; j < map.size / 2 + renderDistance + 1 && j < map.size; j++){
                if (map.map[i][j] == ID.Player.getId()){
                    System.out.print(BLACK_BOLD_BRIGHT + ANSI_GREEN_BACKGROUND + "P"+ ANSI_RESET);
                }
                else if (map.map[i][j] == ID.Enemy.getId()){
                    System.out.print(ANSI_RED_TEXT + ANSI_GREEN_BACKGROUND + "X" + ANSI_RESET);
                }
                else{
                    System.out.print(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
                }
            }
            if (statsLine < 7 && i + 7 >= map.size / 2 + renderDistance){
                printStatsAtIndex(statsLine);
                statsLine++;
            }
            System.out.println(ANSI_RESET);
        }
    }

    // private void playerMove(){

    // }

    //game loop
    public void gameStart() {
        //find save if none create new hero
        read = new Scanner(System.in);
        boolean choiceArtifacts; //true take it, false leave it
        boolean exitGame = false;
        int turn;

        if (loadHero() == false) {
            hero = HeroDirector.createNewHero(read);
            hero.save();
        }
        System.out.println("Welcome " + hero.getName() + " the " + hero.getType() + " !\nThe game starts now !");
        GameMap gameMap = new GameMap(hero.getLevel());
        while (!exitGame) {
            printMapWithHeroStats(gameMap);
            // playerMove();


        }
        read.close();
    }
}
