package ft.swingy;

import javax.swing.SwingUtilities;

import ft.swingy.Terminal.GameController;
import ft.swingy.GUI.GameGUIController;

public class Main
{
    public static void main( String[] args )
    {
        if (args.length == 0 || args.length > 1){
            System.out.println("Please provide a mode: console or gui, and only one of them");
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
            System.out.println("Please provide a mode: console or gui");
            return ;
        }
    }
}
