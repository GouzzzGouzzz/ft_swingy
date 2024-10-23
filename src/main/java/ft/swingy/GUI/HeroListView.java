package ft.swingy.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class HeroListView extends JPanel{
    int heroSaved = 0;

    public HeroListView(GameGUIController root) {
        File saveFile = new File("src/main/java/ft/swingy/save/saves.txt");
        int id = 0;
        String statsOrder[] = {"Name:", "Type:", "Level:", "Experience:", "Attack:", "Defense:", "HP:", "Weapon:", "Armor:", "Helm:"};
        boolean skip = false;
        String name;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(saveFile));
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    name = line.substring(5);
                    for (int i = 0; i < 10; i++){
                        if (!line.startsWith(statsOrder[i])){
                            skip = true;
                            break;
                        }
                        line = fileReader.readLine();
                    }
                    if (skip == false){
                        HeroButtonView heroButton = new HeroButtonView(id, root);
                        heroButton.setText(name);
                        id++;
                        heroSaved++;
                        add(heroButton);
                    }
                }
                skip = false;
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Error: Could not read save file");
        }
    }

    public int getHeroCount(){
        return heroSaved;
    }
}

