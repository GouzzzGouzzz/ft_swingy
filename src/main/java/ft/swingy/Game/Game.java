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

    private int printHeroSaved(File savFile) {
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
        return id;
    }

    private int loadHero() {
        File saveFile = new File("src/main/java/ft/swingy/save/saves.txt");
        LoaderBean loader = new LoaderBean();
        HeroBuilder builder = new HeroBuilder();
        int totalHeroFound;
        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<LoaderBean>> violations;

        if (!saveFile.exists() || saveFile.length() == 0) {
            return -1;
        }

        if(UserInput.askYesOrNo(read, "Do you want to load a hero ? (y/n)") == false){
            return -1;
        }

        while (true) {
            System.out.println("Enter the ID of the hero you want to load :");
            totalHeroFound = printHeroSaved(saveFile);
            try {
                loader.setID(Integer.parseInt(read.nextLine()));
            }
            catch (Exception e) {
                System.out.println("Invalid ID, please enter a valid ID");
                continue;
            }
            violations = validator.validate(loader);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<LoaderBean> violation : violations) {
                    System.out.println(violation.getMessage());
                }
                continue;
            }
            if (loader.getID() > totalHeroFound) {
                System.out.println("Invalid ID, please enter a valid ID");
                continue;
            }
            hero = HeroDirector.loadFromFile(builder, loader.getID());
            if (hero == null) {
                System.out.println("Error: Could not load hero, save may be corrupted");
                return 2;
            }
            break;
        }
        return 1;
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

    private void printMapWithHeroStats(GameMap map, int turn){
        final int renderDistance = 13;
        int startX = (map.size / 2) - renderDistance;
        int statsLine = 0;
        //https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_RED_TEXT = "\033[1;91m";
        final String BLACK_BOLD_BRIGHT = "\033[1;90m";

        if (startX < 0)
            startX = 0;
        for(int i = startX; i < map.size / 2 + renderDistance && i < map.size; i++){
            for(int j = startX; j < map.size / 2 + renderDistance + 1 && j < map.size; j++){
                if (map.map[j][i] == ID.Player.getId()){
                    System.out.print(BLACK_BOLD_BRIGHT + ANSI_GREEN_BACKGROUND + "P" + ANSI_RESET);
                }
                else if (map.map[j][i] == ID.Enemy.getId()){
                    System.out.print(ANSI_RED_TEXT + ANSI_GREEN_BACKGROUND + "X" + ANSI_RESET);
                }
                else{
                    System.out.print(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
                }
            }
            if (statsLine < 7){
                printStatsAtIndex(statsLine);
                statsLine++;
            }
            else if (statsLine == 7){
                System.out.print("    turn: " + turn);
                statsLine++;
            }
            System.out.println(ANSI_RESET);
        }
    }

    public void gameStart() {
        read = new Scanner(System.in);
        boolean exitGame;
        int turn;
        int moveStatus;
        int loadStatus;
        GameMap gameMap;
        final String ANSI_CLEAR_SCREEN = "\033[H\033[2J";

        exitGame = false;
        turn = 0;
        loadStatus = loadHero();
        if (loadStatus == -1) {
            hero = HeroDirector.createNewHero(read);
            hero.save();
        }
        else if (loadStatus == 2) {
            return;
        }
        gameMap = new GameMap(hero.getLevel());
        System.out.println(ANSI_CLEAR_SCREEN);
        System.out.println("Welcome " + hero.getName() + " the " + hero.getType() + " !\nThe game starts now !");
        while (!exitGame) {
            printMapWithHeroStats(gameMap, turn);
            moveStatus = UserInput.playerMoveInput(read, gameMap);
            if (moveStatus == -1)
                break;
            if (moveStatus == 2){
                if (UserInput.enemyEncounter(read, hero) == false)
                    break;
            }
            //si player a gagné
            // save et relance
            turn++;
            System.out.println(ANSI_CLEAR_SCREEN);
        }
        read.close();
    }
}
