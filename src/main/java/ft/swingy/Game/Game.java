package ft.swingy.Game;

import java.io.File;
import java.util.Scanner;

import ft.swingy.Hero.Hero;
import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirector;

public class Game {
    private Hero hero;

    private boolean heroLoaderInput(File[] filesList, Scanner read) {
        String input = "";

        while (input == "") {
            input = read.nextLine();
            if (input.equals("X") || input.equals("x")) {
                return false;
            }
            try {
                int id = Integer.parseInt(input);
                if (id < 0){
                    throw new Exception();
                }
                HeroBuilder builder = new HeroBuilder();
                HeroDirector director = new HeroDirector();
                hero = director.loadFromFile(builder, filesList[id].getName());
                return true;
            }
            catch (Exception e) {
                System.out.println("Invalid input, please enter a correct ID or X to create a new hero");
                input = "";
            }
        }
        return true;
    }

    private boolean loadHero(Scanner read) {
        File saveFolder = new File("src/main/java/ft/swingy/save/");
        File[] filesList = saveFolder.listFiles();
        int id = 0;

        if(filesList != null) {
            System.out.println("List of saved heroes (Type X to create a new one or type an ID to load one):\nID - Name");
            for (int i = 0; i < filesList.length; i++) {
                if (filesList[i].isFile()) {
                    System.out.println(id + " - " +filesList[i].getName().replace(".txt", ""));
                    id++;
                }
            }
            if (heroLoaderInput(filesList, read) == false) {//create new hero instead of loading one
                return false;
            }
            return true;
        }
        else{
            System.out.println("No save found");
            return false;
        }
    }

    private void displayMapWithHeroStats(GameMap map){
        final int renderDistance = 12;
        int startX = (map.size / 2) - renderDistance;
        int startY = (map.size / 2) - renderDistance;

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
            for(int j = startY; j < map.size / 2 + renderDistance && j < map.size; j++){
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
            System.out.println(ANSI_RESET);
        }

        // \u001B[32m
        // for (int i = 0; i < map.size && i < 15; i++){
        //     for (int j = 0; j < map.size && j < 15; j++){
        //         if (map.map[i][j] == ID.Player.getId()){
        //             System.out.print("P");
        //         }
        //         else if (map.map[i][j] == ID.Enemy.getId()){
        //             System.out.print("E");
        //         }
        //         else{
        //             System.out.print("#");
        //         }
        //     }
        //     System.out.println("");
        // }

    }

    //game loop
    public void gameStart() {
        //find save if none create new hero
        Scanner read = new Scanner(System.in);
        boolean choiceArtifacts; //true take it, false leave it
        boolean exitGame = false;
        int turn;

        if (loadHero(read) == false) {
            hero = HeroDirector.createNewHero(read);
            hero.save();
        }
        System.out.println("Welcome " + hero.getName() + " the " + hero.getType() + " !\nThe game starts now !");
        GameMap gameMap = new GameMap(hero.getLevel());
        while (!exitGame) {
            System.out.println("map size: " + gameMap.size);
            displayMapWithHeroStats(gameMap);
            exitGame = true;
        }
        read.close();
    }
}
