package ft.swingy.Game;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import ft.swingy.Artifacts.Artifact;
import ft.swingy.Artifacts.ArtifactDirector;
import ft.swingy.Hero.Hero;

public class UserInput {

    public static boolean askYesOrNo(Scanner read, String message) {
        String choice;
        while (true) {
            System.out.println(message);
            choice = read.nextLine();
            if (choice.equals("y")) {
                return true;
            }
            else if (choice.equals("n")) {
                return false;
            }
            else {
                System.out.println("Invalid input, please enter a (y/n)");
            }
        }
    }

    public static void takeArtifactsInput(Hero hero, Artifact artifactDrop, Scanner read) {
        boolean choice;
        while (true) {
            choice = askYesOrNo(read, "Do you want to take the artifact ? (y/n)")
            if (choice == true) {
                hero.equipArtifact(artifactDrop);
            }
            else {
                break;
            }
        }
    }

    public static int playerMoveInput(Scanner read, GameMap gameMap) {
        String input;
        int status = 0;

        while (true) {
            System.out.println("Enter your move (w, a, s, d) or type 'exit' to quit the game (doesn t save) !");
            input = read.nextLine();
            System.out.print(input);
            switch (input) {
                case "w":
                    status = gameMap.movePlayer(KeyEvent.VK_W);
                    break;
                case "s":
                    status = gameMap.movePlayer(KeyEvent.VK_S);
                    break;
                case "d":
                    status = gameMap.movePlayer(KeyEvent.VK_D);
                    break;
                case "a":
                    status = gameMap.movePlayer(KeyEvent.VK_A);
                    break;
                case "exit":
                    System.out.println("Goodbye !");
                    return -1;
                default:
                    break;
            }
            if (status == 0) {
                System.out.println("Invalid move, please enter a valid move !");
                continue;
            }
            break;
        }
        return status;
    }

    public static boolean enemyEncounter(Scanner read, Hero hero) {
        Artifact artifactDrop = null;
        boolean choice;

        while (true) {
            choice = UserInput.askYesOrNo(read, "You've encountered an enemy, do you want to fight ? (y/n)");
            if (choice == true){
                if (hero.fightSimulation() == false) {
                    hero.deleteSave();
                    read.close();
                    return false;
                }
                System.out.println("You have defeated the enemy !");
                artifactDrop = ArtifactDirector.dropRandomArtifact(hero.getLevel());
                artifactDrop.printArtifact();
                UserInput.takeArtifactsInput(hero, artifactDrop, read);
                break;
            }
            else{
                if (hero.tryToRun() == false) {
                    if (hero.fightSimulation() == false) {
                        hero.deleteSave();
                        read.close();
                        return false;
                    }
                    System.out.println("You have defeated the enemy !");
                    artifactDrop = ArtifactDirector.dropRandomArtifact(hero.getLevel());
                    artifactDrop.printArtifact();
                    UserInput.takeArtifactsInput(hero, artifactDrop, read);
                    break;
                }
                break;
            }
        }
        return true;
    }
}
