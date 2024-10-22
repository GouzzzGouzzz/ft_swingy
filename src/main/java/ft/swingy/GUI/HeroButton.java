package ft.swingy.GUI;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

import ft.swingy.Game.LoaderModel;
import ft.swingy.Hero.Hero;

public class HeroButton extends JButton{
    int id;
    StatsPanel statsPanel;

    public HeroButton(int id, StatsPanel statsPanel, GameGUI root) {
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

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoaderModel loader = new LoaderModel();
                Hero hero = loader.loadHero(id);
                if (hero != null){
                    root.setHero(hero);
                    root.removeLoadingMenu();
                    root.startGame();
                }
            }
        });
    }

    private void showStats() {
        LoaderModel loader = new LoaderModel();
        Hero hero = loader.loadHero(id);
        if (hero == null){
            statsPanel.setCorruptedSave();
            return ;
        }
        statsPanel.setStats(hero, 0);
    }
}
