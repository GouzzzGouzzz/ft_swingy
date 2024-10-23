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
import ft.swingy.GUI.GameGUIController;
import ft.swingy.Game.GameModel;
import ft.swingy.Game.GameMapModel;
import ft.swingy.Game.LoaderModel;
import ft.swingy.Hero.Hero;

public class Main
{
    public static void main( String[] args )
    {
        if (args.length == 0 || args.length > 1){
            System.out.println("Please provide a mode: terminal or gui, and only one of them");
            return ;
        }
        if (args[0].equals("console")){
            GameController gameController = new GameController();
            gameController.gameStart();
        }
        else if (args[0].equals("gui")){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GameGUIController game = new GameGUIController();
                }
            });
        }
        else {
            System.out.println("Please provide a mode: terminal or gui");
            return ;
        }
    }
}
