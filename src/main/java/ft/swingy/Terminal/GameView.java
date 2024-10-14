package ft.swingy.Terminal;

import java.util.Scanner;
import ft.swingy.Game.ANSI;
import ft.swingy.Game.Direction;
import ft.swingy.Hero.Hero;

public class GameView {

    public void startGame(Hero hero){
        System.out.println(ANSI.CLEAR_SCREEN);
        System.out.println("Welcome " + hero.getName() + " the " + hero.getType() + " !\nThe game starts now !");
    }

     public boolean takeArtifactsInput(Scanner read) {
        return UserInput.askYesOrNo(read, "Do you want to take the artifact ? (y/n)");
    }

    public Direction playerMoveInput(Scanner read) {
        String input;

        while (true) {
            System.out.println("Enter your move (w, a, s, d) !");
            input = read.nextLine();
            switch (input) {
                case "w":
                    return Direction.UP;
                case "s":
                    return Direction.DOWN;
                case "d":
                    return Direction.RIGHT;
                case "a":
                    return Direction.LEFT;
                default:
                    return null;
            }
        }
    }

    public boolean enemyEncounter(Scanner read) {
        boolean choice;
        choice = UserInput.askYesOrNo(read, "You've encountered an enemy, do you want to fight ? (y/n)");
        return choice;
    }

    public boolean continuePlaying(Scanner read){
        return UserInput.askYesOrNo(read, "Do you want to continue playing ? (y/n)");
    }
}
