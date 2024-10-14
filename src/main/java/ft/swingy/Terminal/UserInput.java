package ft.swingy.Terminal;

import java.util.Scanner;

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
}
