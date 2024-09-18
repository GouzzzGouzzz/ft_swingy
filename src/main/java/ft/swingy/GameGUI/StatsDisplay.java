package ft.swingy.GameGUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ft.swingy.Hero.Hero;

public class StatsDisplay extends JPanel{
    private JLabel name;
    private JLabel classType;
    private JLabel heatlh;
    private JLabel attack;
    private JLabel defense;
    private JLabel level;
    private JLabel xp;
    private Hero hero;

    public StatsDisplay(Hero hero) {
        this.hero = hero;
        setLayout(new GridLayout(7, 1));
        name = new JLabel();
        classType = new JLabel();
        heatlh = new JLabel();
        attack = new JLabel();
        defense = new JLabel();
        level = new JLabel();
        xp = new JLabel();
        name.setText("<html>Name: <br>" + hero.getName() + "</html>");
        classType.setText("<html>Class: <br>" + hero.getType() + "</html>");
        heatlh.setText("<html>Health: <br>" + hero.getHitPoints() + "/" + hero.getMaxHitPoints() + "</html>");
        attack.setText("<html>Attack: <br>" + hero.getAttack() + "</html>");
        defense.setText("<html>Defense: <br>" + hero.getDefense() + "</html>");
        level.setText("<html>Level: <br>" + hero.getLevel() + "</html>");
        xp.setText("<html>XP: <br>" + hero.getExperience() + "/" + hero.getNextLevelXp() + "</html>");
        add(classType);
        add(name);
        add(heatlh);
        add(attack);
        add(defense);
        add(level);
        add(xp);
    }

    public void updateStats(){
        name.setText("<html>Name: <br>" + hero.getName() + "</html>");
        classType.setText("<html>Class: <br>" + hero.getType() + "</html>");
        heatlh.setText("<html>Health: <br>" + hero.getHitPoints() + "/" + hero.getMaxHitPoints() + "</html>");
        attack.setText("<html>Attack: <br>" + hero.getAttack() + "</html>");
        defense.setText("<html>Defense: <br>" + hero.getDefense() + "</html>");
        level.setText("<html>Level: <br>" + hero.getLevel() + "</html>");
        xp.setText("<html>XP: <br>" + hero.getExperience() + "/" + hero.getNextLevelXp() + "</html>");
    }
}
