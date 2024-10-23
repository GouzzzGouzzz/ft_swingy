package ft.swingy.GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import ft.swingy.Hero.Hero;

public class StatsPanelView extends JPanel{
    CustomLabelView name;
    CustomLabelView type;
    CustomLabelView level;
    CustomLabelView experience;
    CustomLabelView attack;
    CustomLabelView defense;
    CustomLabelView hitPoints;
    CustomLabelView weapon;
    CustomLabelView armor;
    CustomLabelView helm;
    CustomLabelView turn;
    boolean showTurn;

    public StatsPanelView(boolean showTurn) {
        this.showTurn = showTurn;
        setLayout(new GridLayout(11, 1));
        name = new CustomLabelView("Name: ");
        type = new CustomLabelView("Type: ");
        level = new CustomLabelView("Level: ");
        experience = new CustomLabelView("Experience: ");
        attack = new CustomLabelView("Attack: ");
        defense = new CustomLabelView("Defense: ");
        hitPoints = new CustomLabelView("HP: ");
        weapon = new CustomLabelView("Weapon: ");
        armor = new CustomLabelView("Armor: ");
        helm = new CustomLabelView("Helm: ");
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
        if (showTurn){
            turn = new CustomLabelView("Turn: ");
            add(turn);
        }
        setBackground(Color.GRAY);
        revalidate();
        repaint();
    }

    public void setStats(Hero hero, int turnNumber){
        name.setText("Name: " + hero.getName());
        type.setText("Type: " + hero.getType());
        level.setText("Level: " + hero.getLevel());
        experience.setText("Experience: " + hero.getExperience() + "/" + hero.getNextLevelXp());
        attack.setText("Attack: " + hero.getAttack());
        defense.setText("Defense: " + hero.getDefense());
        hitPoints.setText("HP: " + hero.getHitPoints() + "/" + hero.getMaxHitPoints());
        if (hero.getArtifact(0) != null)
            weapon.setText("Weapon: " + hero.getArtifact(0).getQuality());
        else
            weapon.setText("Weapon: None");
        if (hero.getArtifact(1) != null)
            armor.setText("Armor: " + hero.getArtifact(1).getQuality());
        else
            armor.setText("Armor: None");
        if (hero.getArtifact(2) != null)
            helm.setText("Helm: " + hero.getArtifact(2).getQuality());
        else
            helm.setText("Helm: None");
        if (showTurn){
            turn.setText("Turn: " + turnNumber);
        }
        revalidate();
        repaint();
    }

    public void setCorruptedSave(){
        name.setText("Name: Corrupted Save");
        type.setText("Type: Corrupted Save");
        level.setText("Level: Corrupted Save");
        experience.setText("Experience: Corrupted Save");
        attack.setText("Attack: Corrupted Save");
        defense.setText("Defense: Corrupted Save");
        hitPoints.setText("HP: Corrupted Save");
        weapon.setText("Weapon: Corrupted Save");
        armor.setText("Armor: Corrupted Save");
        helm.setText("Helm: Corrupted Save");
        if (showTurn){
            turn.setText("Turn: Corrupted Save");
        }
        revalidate();
        repaint();
    }

}
