package ft.swingy.GameGUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ft.swingy.Enemy.Enemy;
import ft.swingy.Hero.Hero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FightScreen extends JPanel{
    private JButton fightBtn;
    private JButton runBtn;
    private JLabel fightText;
    private Hero hero;

    public FightScreen(Board board, Hero hero) {
        fightBtn = new JButton("Fight");
        runBtn = new JButton("Run");
        fightText = new JLabel();
        this.hero = hero;
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
                if (hero.fightSimulation(new Enemy(hero.getLevel())) == false){
                    fightText.setText("You have been defeated");
                    return ;
                }
                board.inFight = false;
            }
        });
        runBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                toggleVisibility();
                if (hero.tryToRun() == false){
                    fightText.setText("You have been defeated");
                    return ;
                }
                board.inFight = false;
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
