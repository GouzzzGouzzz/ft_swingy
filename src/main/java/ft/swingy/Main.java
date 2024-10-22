package ft.swingy;

import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirectorModel;
import ft.swingy.Terminal.GameController;
import ft.swingy.Terminal.LoaderController;
import ft.swingy.Terminal.LoaderView;
import ft.swingy.Artifacts.ArtifactBuilder;

import java.util.Map;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import ft.swingy.Artifacts.Artifact;
import ft.swingy.Artifacts.ArtifactDirector;
import ft.swingy.GUI.GameGUI;
import ft.swingy.Game.GameModel;
import ft.swingy.Game.GameMapModel;
import ft.swingy.Game.LoaderModel;
import ft.swingy.Hero.Hero;

public class Main
{
    public static void main( String[] args )
    {
        //terminal

        // GameController game = new GameController();
        // game.gameStart();
        //GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameGUI game = new GameGUI();
            }
        });

    }
}
