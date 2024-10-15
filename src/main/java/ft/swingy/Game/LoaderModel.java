package ft.swingy.Game;

import java.io.File;

import ft.swingy.Hero.Hero;
import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirectorModel;

public class LoaderModel {

    public Hero loadHero(int id){
        File saveFile = new File("src/main/java/ft/swingy/save/saves.txt");
        HeroBuilder builder = new HeroBuilder();

        if (!saveFile.exists() || saveFile.length() == 0) {
            return null;
        }
        return HeroDirectorModel.loadFromFile(builder, id);
    }
}
