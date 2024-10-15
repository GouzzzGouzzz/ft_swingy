package ft.swingy.Terminal;

import java.util.Scanner;

public class HeroDirectorView {
    public String askForHeroName(Scanner read){
        String name;

        System.out.println("Enter your hero's name: ");
        name = read.nextLine();
        return name;
    }

    public int askForHeroClass(Scanner read){
        int id;

        System.out.println("Type you're hero id class to choose one:");
        System.out.println("1. Warrior (Attack: 30, Defense: 25, HP: 100)");
        System.out.println("2. Rogue (Attack: 75, Defense: 15, HP: 50)");
        id = Integer.parseInt(read.nextLine());
        return id;
    }
}

