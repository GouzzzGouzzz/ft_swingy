package ft.swingy.GUI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ft.swingy.Artifacts.Artifact;


public class PopUpView extends JOptionPane{
    JFrame frame;

    public PopUpView(JFrame parentFrame) {
        setBackground(Color.GRAY);
        frame = parentFrame;
    }

    public int enemyEncounter(){
        int choice = 0;
        frame.revalidate();
        frame.repaint();
        frame.toFront();
        frame.requestFocusInWindow();
        choice = showConfirmDialog(frame, "You have encountered an enemy! Do you want to fight?",
        "ENEMY ENCOUNTERED", JOptionPane.YES_NO_OPTION);
        return choice;
    }

    public void gameOver(){
        frame.revalidate();
        frame.repaint();
        frame.requestFocus();
        showMessageDialog(frame, "Game Over !\n You have been defeated!",
        "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    }

    public void fightWon(){
        frame.revalidate();
        frame.repaint();
        frame.requestFocus();
        showMessageDialog(frame, "You have defeated the enemy!",
        "FIGHT WON", JOptionPane.INFORMATION_MESSAGE);
    }

    public int artifactDrop(Artifact artifact){
        int choice = 0;
        frame.revalidate();
        frame.repaint();
        frame.requestFocus();
        choice = showConfirmDialog(frame, "You have found an artifact for " + artifact.getType() + " of quality " + artifact.getQuality() + "\nDo you want to equip it?",
        "ARTIFACT FOUND", JOptionPane.YES_NO_OPTION);
        return choice;
    }

    public void runAway(){
        frame.revalidate();
        frame.repaint();
        frame.requestFocus();
        showMessageDialog(frame, "You have successfully ran away from the enemy!",
        "RUN AWAY", JOptionPane.INFORMATION_MESSAGE);
    }

    public void invalidHero(){
        frame.revalidate();
        frame.repaint();
        showMessageDialog(frame, "This hero is invalid !",
        "INVALID HERO", JOptionPane.INFORMATION_MESSAGE);
    }

    public void goodbye(){
        frame.revalidate();
        frame.repaint();
        frame.requestFocus();
        showMessageDialog(frame, "Goodbye !",
        "GOODBYE", JOptionPane.INFORMATION_MESSAGE);
    }

    public int continuePlaying(){
        int choice = 0;
        frame.revalidate();
        frame.repaint();
        frame.requestFocus();
        choice = showConfirmDialog(frame, "You won !, continue to the next level or leave here ?",
        "YOU WON", JOptionPane.YES_NO_OPTION);
        return choice;
    }

    public int loadOrCreate(){
        int choice = 0;
        Object[] options = {"Create", "Load"};
        frame.revalidate();
        frame.repaint();
        frame.requestFocus();
        choice = showOptionDialog(
        frame,
        "Do you want to laod or create a new hero ?",
        "Hero Loading",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[1]
        );
        return choice;
    }
}
