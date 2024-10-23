package ft.swingy.GUI;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

import ft.swingy.Game.LoaderModel;
import ft.swingy.Hero.Hero;

public class HeroButtonView extends JButton{
    int id;
    GameGUIController root;

    public HeroButtonView(int id , GameGUIController root) {
        this.id = id;
        this.root = root;
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
                else
                    root.getPopUp().invalidHero();
            }
        });
    }

    private void showStats() {
        LoaderModel loader = new LoaderModel();
        Hero hero = loader.loadHero(id);
        if (hero == null){
            root.getStatsSelectPanel().setCorruptedSave();
            return ;
        }
        root.getStatsSelectPanel().setStats(hero, 0);
    }
}
