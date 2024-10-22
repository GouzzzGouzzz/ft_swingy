package ft.swingy.GUI;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;

import ft.swingy.Game.LoaderModel;
import ft.swingy.Hero.Hero;

import java.awt.event.MouseEvent;

public class HeroButton extends JButton{
    int id;
    StatsPanel statsPanel;

    public HeroButton(int id, StatsPanel statsPanel) {
        this.id = id;
        this.statsPanel = statsPanel;
        setPreferredSize(new Dimension(200, 50));
        setMaximumSize(new Dimension(200, 50));
        setAlignmentX(CENTER_ALIGNMENT);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                showStats();
            }
        });
    }

    private void showStats() {
        //Yes this is bad, i open a file and go through it
        //every time i want to show stat, but it works
        LoaderModel loader = new LoaderModel();
        Hero hero = loader.loadHero(id);
        if (hero == null){
            statsPanel.setCorruptedSave();
            return ;
        }
        statsPanel.setStats(hero, 0);
    }
}
