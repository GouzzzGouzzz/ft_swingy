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

    private void displayMapWithHeroStats(GameMap gameMap){
        System.out.println(gameMap.getSize());
        for (int i = 0; i < gameMap.size; i++){
            System.out.println(gameMap.map[0][i]);
        }
        // for (int i = 0; i < map.size; i++){
        //     for (int j = 0; j < map.size; j++){
        //         System.out.println(i + " " + j);
        //         if (map.map[i][j] == ID.Player.getId()){
        //             System.out.println("P");
        //         }
        //         else{
        //             System.out.println("#");
        //         }
        //     }
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
            displayMapWithHeroStats(gameMap);
            exitGame = true;
        }
        read.close();
    }
}
