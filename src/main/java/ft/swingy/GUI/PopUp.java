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
        choice = showConfirmDialog(frame, "MESSAGE",
        "TITRE", JOptionPane.YES_NO_OPTION);

        return choice;
    }
}
