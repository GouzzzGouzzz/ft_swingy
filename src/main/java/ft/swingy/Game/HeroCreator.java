package ft.swingy.Game;

import java.util.Scanner;

import ft.swingy.Hero.Hero;
import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirector;

public class HeroCreator {

    static public Hero createNewHero(){
        final String[] heroType = {"Warrior", "Rogue"};
        HeroDirector director = new HeroDirector();
        HeroBuilder builder = new HeroBuilder();
        Scanner read = new Scanner(System.in);
        String name = "";
        int type = -1;
        while (name == "" || type == -1) {
            System.out.println("Enter your hero's name: ");
            if (name == ""){
                name = read.nextLine();
                if (name.length() > 20) {
                    System.out.println("Name too long, please enter a name with less than 20 characters");
                    name = "";
                    continue;
                }
            }
            System.out.println("Type you're hero id class to choose one: \n1 " + heroType[0] + "\n2 " + heroType[1]);
            if (type == -1){
                try{
                    type = read.nextInt();
                }
                catch (Exception e){
                    System.out.println("Invalid class, please enter a correct id\n1 " + heroType[0] + "\n2 " + heroType[1]);
                    type = -1;
                    read.nextLine();
                    continue;
                }
                if (type < 1 || type > heroType.length){
                    System.out.println("Invalid class, please enter a correct id\n1 " + heroType[0] + "\n2 " + heroType[1]);
                    type = -1;
                    continue;
                }
            }
        }
        read.close();
        switch (type) {
            case 1:
                return director.makeWarrior(builder, name);
            case 2:
                return director.makeRogue(builder, name);
            default:
                break;
        }
        return null;
    }
}
