package ft.swingy.GUI;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

public class HeroLoaderView {
    private JScrollPane heroScroll;
    private HeroListView heroList;
    private GameGUIController root;

    public HeroLoaderView(GameGUIController root){
        this.root = root;
        heroList = new HeroListView(root);
        heroScroll = new JScrollPane(heroList);
        root.setLayout(new BorderLayout());
        heroScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        heroScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public JScrollPane getHeroScroll(){
        return heroScroll;
    }

    public void removeLoadingMenu(){
        root.remove(root.getStatsSelectPanel());
        root.remove(heroScroll);
        root.revalidate();
        root.repaint();
    }

    public int getHeroCount(){
        return heroList.getHeroCount();
    }
}
