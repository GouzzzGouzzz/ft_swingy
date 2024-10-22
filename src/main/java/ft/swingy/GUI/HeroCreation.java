package ft.swingy.GUI;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ft.swingy.Hero.Hero;
import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirectorModel;

public class HeroCreation {
    private JTextField heroName;
    private JPanel buttonPanel;
    private JPanel namePanel;
    private JButton rogue;
    private JButton warrior;
    private JButton confirm;
    private StatsPanel statsPanel;
    private GameGUI root;
    private int classId;

    public HeroCreation(StatsPanel statsPanel, GameGUI root){
        this.statsPanel = statsPanel;
        this.root = root;
        classId = 1;
        heroName = new JTextField();
        confirm = new JButton("Create");
        rogue = new JButton("Rogue");
        warrior = new JButton("Warrior");
        //panel for the class selection
        buttonPanel = new JPanel();
        buttonPanel.add(rogue);
        buttonPanel.add(warrior);
        //panel for the name input
        namePanel = new JPanel();
        namePanel.add(heroName);
        namePanel.add(confirm);
        heroName.setPreferredSize(new Dimension(100, 30));
        setupListener();
    }

    private void setupListener(){
        rogue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                HeroDirectorModel model = new HeroDirectorModel();
                Hero defaultHero = model.makeRogue(new HeroBuilder(), heroName.getText());
                statsPanel.setStats(defaultHero, 0);
            }
        });
        warrior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                HeroDirectorModel model = new HeroDirectorModel();
                Hero defaultHero = model.makeWarrior(new HeroBuilder(), heroName.getText());
                statsPanel.setStats(defaultHero, 0);
            }
        });
        rogue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classId = 1;
            }
        });
        warrior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classId = 2;
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HeroDirectorModel model = new HeroDirectorModel();
                Hero hero = null;
                //need hibernate validator stuff here
                if (classId == 1)
                    hero = model.makeRogue(new HeroBuilder(), heroName.getText());
                else if (classId == 2)
                    hero = model.makeWarrior(new HeroBuilder(), heroName.getText());
                if (hero != null){
                    root.setHero(hero);
                    root.removeHeroCreationMenu();
                    root.startGame();
                }
            }
        });
    }

    public JPanel getButtonPanel(){
        return buttonPanel;
    }

    public JPanel getNamePanel(){
        return namePanel;
    }
}
