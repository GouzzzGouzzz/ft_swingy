package ft.swingy.GameGUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightScreen extends JPanel{
    private JButton fightBtn;
    private JButton runBtn;
    private JLabel fightText;

    public FightScreen(Board board) {
        fightBtn = new JButton("Fight");
        runBtn = new JButton("Run");
        fightText = new JLabel();

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
                System.out.println("Fight - Some algo were calle adn you won the fight");
                toggleVisibility();
                board.inFight = false;
            }
        });
        runBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Run - You ran away");
                toggleVisibility();
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
