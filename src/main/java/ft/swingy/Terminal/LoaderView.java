package ft.swingy.Terminal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoaderView {
    Scanner read;

    public LoaderView(Scanner read){
        this.read = read;
    }

    public boolean askToLoad(){
        return UserInput.askYesOrNo(read, "Do you want to load a hero ? (y/n)");
    }

    private int heroSaved(boolean print){
        File saveFile;
        String[] statsOrder = {"Name:", "Type:", "Level:", "Experience:", "Attack:", "Defense:", "HP:", "Weapon:", "Armor:", "Helm:"};
        ArrayList<String> heroOutput = new ArrayList<String>();
        int id;

        id = 0;
        saveFile = new File("src/main/java/ft/swingy/save/saves.txt");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(saveFile));
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    for (int i = 0; i < 10; i++){
                        if (line.startsWith(statsOrder[i])){
                            if (i == 0){
                                heroOutput.add(id + " -- " + line);
                            }
                            else{
                                heroOutput.add("| " + line);
                            }
                        }
                        else{
                            heroOutput.clear();
                            break;
                        }
                        if (i == 9){
                            id++;
                            break;
                        }
                        line = fileReader.readLine();
                    }
                    if (print){
                        for (int i = 0; i < heroOutput.size(); i++){
                            System.out.println(heroOutput.get(i));
                        }
                    }
                    heroOutput.clear();
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Error: Could not read save file");
        }
        return id;
    }

    public int askHeroId(){
        System.out.println("Enter the ID of the hero you want to load :");
        heroSaved(true);
        try {
            return Integer.parseInt(read.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public int getTotalHeroFound(){
        return heroSaved(false);
    }

    public void InvalidId(){
        System.out.println("Invalid ID, please enter a valid ID");
    }

    public void NoHeroSaved(){
        System.out.println("No hero found, please create a new hero");
    }

    public void InvalidHeroSave(){
        System.out.println("Error: Could not load hero, save may be corrupted");
    }

    public void ValidHeroSave(){
        System.out.println("Hero loaded successfully");
    }
}
