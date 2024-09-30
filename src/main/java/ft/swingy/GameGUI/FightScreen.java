package ft.swingy.GameGUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ft.swingy.Enemy.Enemy;
import ft.swingy.Hero.Hero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightScreen extends JPanel{
    private JButton fightBtn;
    private JButton runBtn;
    private JLabel fightText;
    private Hero hero;
    private StatsDisplay statsDisplay;

    public FightScreen(Board board, Hero hero, StatsDisplay statsDisplay) {
        fightBtn = new JButton("Fight");
        runBtn = new JButton("Run");
        fightText = new JLabel();
        this.hero = hero;
        this.statsDisplay = statsDisplay;

        //init
        add(fightText);
        add(fightBtn);
        add(runBtn);
        runBtn.setVisible(false);
        fightBtn.setVisible(false);
        fightText.setVisible(false);
        fightBtn.setFocusable(false);
        runBtn.setFocusable(false);
        fightText.setFocusable(false);
        fightText.setText("You have encountered an enemy! - Run or Fight?");

        fightBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                toggleVisibility();
                if (hero.fightSimulation() == false){
                    fightText.setText("You have been defeated !!!");
                    fightText.setVisible(true);
                    statsDisplay.updateStats();
                    return ;
                }
                board.inFight = false;
                statsDisplay.updateStats();
            }
        });
        //this is broken now
        runBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                toggleVisibility();
                if (hero.tryToRun() == false){
                    fightText.setText("You have been defeated");
                    fightText.setVisible(true);
                    statsDisplay.updateStats();
                    return ;
                }
                board.inFight = false;
                statsDisplay.updateStats();
            }
        });
    }

    public void toggleVisibility() {
        if (fightText.isVisible()){
            fightBtn.setVisible(false);
            runBtn.setVisible(false);
            fightText.setVisible(false);
        }
        else{
            fightText.setVisible(true);
            runBtn.setVisible(true);
            fightBtn.setVisible(true);
        }
    }
}
