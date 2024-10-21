package ft.swingy.GUI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ft.swingy.Artifacts.Artifact;


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
        "ENEMY ENCOUNTERED", JOptionPane.YES_NO_OPTION);
        return choice;
    }

    public void gameOver(){
        showMessageDialog(frame, "Game Over !\n You have been defeated!",
        "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    }

    public void fightWon(){
        showMessageDialog(frame, "You have defeated the enemy!",
        "FIGHT WON", JOptionPane.INFORMATION_MESSAGE);
    }

    public int artifactDrop(Artifact artifact){
        int choice = 0;
        choice = showConfirmDialog(frame, "You have found an artifact for " + artifact.getType() + "of quality " + artifact.getQuality() + "\nDo you want to equip it?",
        "ARTIFACT FOUND", JOptionPane.YES_NO_OPTION);
        return choice;
    }

    public void runAway(){
        showMessageDialog(frame, "You have successfully ran away from the enemy!",
        "RUN AWAY", JOptionPane.INFORMATION_MESSAGE);
    }
}
