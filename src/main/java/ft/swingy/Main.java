package ft.swingy;

import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirector;
import ft.swingy.Artifacts.ArtifactBuilder;

import java.util.Map;

import javax.swing.SwingUtilities;

import ft.swingy.Artifacts.Artifact;
import ft.swingy.Artifacts.ArtifactDirector;
import ft.swingy.Game.Game;
import ft.swingy.Game.GameMap;
import ft.swingy.GameGUI.GameGUI;
import ft.swingy.Hero.Hero;

public class Main
{
    public static void main( String[] args )
    {
        Game game = new Game();
        game.gameStart();

        // Hero test = HeroCreator.createNewHero();
        // test.printStats();
        // test.save();

        // GameMap map = new GameMap(13);
        // map.displayTerminal();
        // ArtifactDirector artifactDirector = new ArtifactDirector();
        // ArtifactBuilder artifactBuilder = new ArtifactBuilder();
        // Artifact art;
        // for (int i = 0; i < 10; i++) {
        //     art = artifactDirector.makeT1(artifactBuilder);
        //     art.printStats();
        // }
        // SwingUtilities.invokeLater(new Runnable() {
        //     public void run() {
                // GameMap map = new GameMap(13);
                // GameGUI game = new GameGUI(map, HeroDirector.makeWarrior(new HeroBuilder(), "Banger"));
        //     }
        // });

    }
}
