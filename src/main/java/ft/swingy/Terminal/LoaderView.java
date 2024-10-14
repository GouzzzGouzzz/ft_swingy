package ft.swingy.Terminal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class LoaderView {
    Scanner read;

    public LoaderView(Scanner read){
        this.read = read;
    }

    public boolean askToLoad(){
        if(UserInput.askYesOrNo(read, "Do you want to load a hero ? (y/n)") == false){
            return false;
        }
        return true;
    }

    private void printHeroStats(BufferedReader reader) {
        for (int i = 0; i < 9; i++) {
            try {
                System.out.println("   |" + reader.readLine());
            } catch (Exception e) {
                System.out.println("Error: Could not read save file");
            }
        }
    }

    private int heroSaved(boolean print){
        File saveFile;
        int id;

        id = 0;
        saveFile = new File("src/main/java/ft/swingy/save/saves.txt");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(saveFile));
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (line.contains("Name:") && print == true) {
                    System.out.println(id + " | " + line.substring(5));
                    printHeroStats(fileReader);
                    id++;
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
