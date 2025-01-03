package ft.swingy.Terminal;

import java.io.File;

import ft.swingy.Game.LoaderModel;
import ft.swingy.Hero.Hero;

public class LoaderController {
    LoaderModel model;
    LoaderView view;

    public LoaderController(LoaderView view, LoaderModel model) {
        this.view = view;
        this.model = model;
    }

    public Hero loadHero() {
        int totalHeroSaved, id;
        Hero hero;
        File saveFile;

        saveFile = new File("src/main/java/ft/swingy/save/saves.txt");
        totalHeroSaved = view.getTotalHeroFound();
        if (!saveFile.exists() || saveFile.length() == 0) {
            view.NoHeroSaved();
            return null;
        }

        if (!view.askToLoad())
            return null;
        while (true) {
            id = view.askHeroId();
            if (id < 0 || id >= totalHeroSaved) {
                view.InvalidId();
                continue;
            }
            hero = model.loadHero(id);
            if (hero == null) {
                view.InvalidId();
                continue;
            }
            break;
        }
        view.ValidHeroSave();
        return hero;
    }
}
