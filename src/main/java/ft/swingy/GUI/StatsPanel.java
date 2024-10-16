package ft.swingy.GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import ft.swingy.Hero.Hero;

public class StatsPanel extends JPanel{
    CustomLabel name;
    CustomLabel type;
    CustomLabel level;
    CustomLabel experience;
    CustomLabel attack;
    CustomLabel defense;
    CustomLabel hitPoints;
    CustomLabel weapon;
    CustomLabel armor;
    CustomLabel helm;
    CustomLabel turn;

    public StatsPanel() {
        setLayout(new GridLayout(11, 1));
        name = new CustomLabel("Name: ");
        type = new CustomLabel("Type: ");
        level = new CustomLabel("Level: ");
        experience = new CustomLabel("Experience: ");
        attack = new CustomLabel("Attack: ");
        defense = new CustomLabel("Defense: ");
        hitPoints = new CustomLabel("HP: ");
        weapon = new CustomLabel("Weapon: ");
        armor = new CustomLabel("Armor: ");
        helm = new CustomLabel("Helm: ");
        turn = new CustomLabel("Turn: ");
        add(name);
        add(type);
        add(level);
        add(experience);
        add(attack);
        add(defense);
        add(hitPoints);
        add(weapon);
        add(armor);
        add(helm);
        add(turn);
        setBackground(Color.GRAY);
        revalidate();
        repaint();
    }

    public void setStats(Hero hero, int turnNumber){
        name.setText("Name: " + hero.getName());
        type.setText("Type: " + hero.getType());
        level.setText("Level: " + hero.getLevel());
        experience.setText("Experience: " + hero.getExperience());
        attack.setText("Attack: " + hero.getAttack());
        defense.setText("Defense: " + hero.getDefense());
        hitPoints.setText("HP: " + hero.getHitPoints() + "/" + hero.getMaxHitPoints());
        weapon.setText("Weapon: " + hero.getArtifact(0).getQuality());
        armor.setText("Armor: " + hero.getArtifact(1).getQuality());
        helm.setText("Helm: " + hero.getArtifact(2).getQuality());
        turn.setText("Turn: " + turnNumber);
        revalidate();
        repaint();
    }
}
