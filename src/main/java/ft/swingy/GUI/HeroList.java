package ft.swingy.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class HeroList extends JPanel{

    public HeroList(StatsPanel statsPanel) {
        File saveFile = new File("src/main/java/ft/swingy/save/saves.txt");
        int id = 0;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(saveFile));
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (line.contains("Name:")) {
                    HeroButton heroButton = new HeroButton(id, statsPanel);
                    heroButton.setText(line.substring(5));
                    id++;
                    add(heroButton);
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Error: Could not read save file");
        }
    }

}

