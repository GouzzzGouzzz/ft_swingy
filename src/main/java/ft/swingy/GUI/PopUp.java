package ft.swingy.GUI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class PopUp extends JOptionPane{
    JFrame frame;

    public PopUp(JFrame parentFrame) {
        setBackground(Color.GRAY);
        // setBounds(300,300,200,200);
        frame = parentFrame;
    }

    public int enemyEncounter(){
        int choice = 0;
        choice = showConfirmDialog(frame, "You have encountered an enemy! Do you want to fight?",
        null, JOptionPane.YES_NO_OPTION);
        return choice;
    }

    public void gameOver(){
        showMessageDialog(frame, "Game Over !\n You have been defeated!",
        "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    }
}
