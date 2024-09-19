package ft.swingy.Hero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class HeroDirector {
    public Hero makeWarrior(HeroBuilder builder, String name) {
        builder.reset();
        builder.setType("Warrior");
        builder.setLevel(1);
        builder.setExperience(0);
        builder.setAttack(30);
        builder.setDefense(25);
        builder.setHP(100);
        builder.setName(name);
        return builder.getHero();
    }

    public Hero makeRogue(HeroBuilder builder, String name) {
        builder.reset();
        builder.setType("Rogue");
        builder.setLevel(1);
        builder.setExperience(0);
        builder.setAttack(75);
        builder.setDefense(15);
        builder.setHP(50);
        builder.setName(name);
        return builder.getHero();
    }

    public Hero loadFromFile(HeroBuilder builder, String name) {
        File file = new File("src/main/java/ft/swingy/save/"+ name + ".txt");
        String line;

        builder.reset();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String [] info = line.split(":");
                if (info.length != 2){
                    System.out.println("Error: Invalid save file format");
                    reader.close();
                    return null;
                }
                switch (info[0]) {
                    case "Name":
                        builder.setName(info[1]);
                        break;
                    case "Type":
                        builder.setType(info[1]);
                        break;
                    case "Level":
                        builder.setLevel(Integer.parseInt(info[1]));
                        break;
                    case "Experience":
                        builder.setExperience(Integer.parseInt(info[1]));
                        break;
                    case "Attack":
                        builder.setAttack(Integer.parseInt(info[1]));
                        break;
                    case "Defense":
                        builder.setDefense(Integer.parseInt(info[1]));
                        break;
                    case "HP":
                        builder.setHP(Integer.parseInt(info[1]));
                        break;
                    default:
                        System.out.println("Error: Invalid save file format");
                        reader.close();
                        return null;
                }
            }
            reader.close();
        }
        catch (Exception e) {
            System.out.println("Error occurred while reading save file: " + e.getMessage());
        }
        System.out.println("Hero loaded successfully!");
        return builder.getHero();
    }
}
