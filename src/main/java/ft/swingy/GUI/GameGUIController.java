package ft.swingy.GUI;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ft.swingy.Game.Direction;
import ft.swingy.Game.GameModel;
import ft.swingy.Game.MoveResult;
import ft.swingy.Hero.Hero;

public class GameGUIController extends JFrame{
    private GameRenderVIew render;
    private JLayeredPane layeredPane;
    private StatsPanelView statsPanel;
    private LogsPanelView logs;
    private GameModel game;
    private PopUpView popup;
    private Hero hero;
    private volatile boolean inFight;
    private StatsPanelView statsSelectPanel;
    private HeroCreationView heroCreation;
    private HeroLoaderView heroLoader;

    public GameGUIController() {

        setVisible(false);
        setTitle("Swingy - Dungeon Crawler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(813, 813);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);

        layeredPane = new JLayeredPane();
        statsPanel = new StatsPanelView(true);
        logs = new LogsPanelView();
        popup = new PopUpView(this);
        loadOrCreateHero();
    }

    public void setHero(Hero hero){
        this.hero = hero;
    }
    public PopUpView getPopUp(){
        return popup;
    }

    public StatsPanelView getStatsSelectPanel(){
        return statsSelectPanel;
    }

    private void loadOrCreateHero(){
        statsSelectPanel = new StatsPanelView(false);
        statsSelectPanel.setPreferredSize(new Dimension(200, this.getHeight()));
        heroLoader = new HeroLoaderView(this);
        setVisible(true);
        revalidate();
        repaint();
        if (heroLoader.getHeroCount() == 0){
            showHeroCreationMenu();
        }
        if (popup.loadOrCreate() == JOptionPane.NO_OPTION){
            showHeroLoadingMenu();
        }
        else{
            showHeroCreationMenu();
        }
    }

    public void showHeroCreationMenu(){
        heroCreation = new HeroCreationView(this);
        setLayout(new BorderLayout());
        add(heroCreation.getButtonPanel(), BorderLayout.CENTER);
        add(heroCreation.getNamePanel(), BorderLayout.SOUTH);
        add(statsSelectPanel, BorderLayout.WEST);
        revalidate();
        repaint();
    }

    public void showHeroLoadingMenu(){
        add(heroLoader.getHeroScroll(), BorderLayout.CENTER);
        add(statsSelectPanel, BorderLayout.WEST);
        revalidate();
        repaint();
    }

    public void removeLoadingMenu(){
        heroLoader.removeLoadingMenu();
        revalidate();
        repaint();
    }

    public void removeHeroCreationMenu(){
        heroCreation.removeHeroCreationMenu();
        revalidate();
        repaint();
    }

    public void startGame(){
        game = new GameModel(hero, null);
        game.createaNewMap();
        render = new GameRenderVIew(game.getMap());
        //GamePane
        setupGamePane();
        addGameListener();
        setVisible(true);
        revalidate();
        repaint();
        gameLoop();
    }

    public void setupGamePane(){
        setVisible(false);
        layeredPane.setPreferredSize(new Dimension(813, 813));
        setContentPane(layeredPane);
        setLayout(null);
        requestFocusInWindow();

        //StatsPanel
        statsPanel.setBounds(0, 0, 200, 250);

        //Logs
        logs.setBounds(0, 516, 300, 200);

        //adding to layeredPane
        layeredPane.add(render, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(logs, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(statsPanel, JLayeredPane.PALETTE_LAYER);

        //popup
        layeredPane.add(popup, JLayeredPane.POPUP_LAYER);

        //rendering game
        render.setLayout(null);
        //around 23 x 23 tiles
        render.setBounds(0, 0, 737, 737);
        revalidate();
        repaint();
    }

    private void addGameListener(){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // board.setBounds(0, 0, getWidth()-76, getHeight()-76);
                // board.updateGUIOnResize();

                // System.out.println("Resized" + e.getComponent().getSize());
                setSize(813, 813);
                revalidate();
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (inFight == true)
                    return ;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        game.movePlayer(Direction.UP);
                        if (game.getMoveStatus() != MoveResult.INVALID_MOVE) {
                            render.movePlayer(KeyEvent.VK_W);
                        }
                        break;
                    case KeyEvent.VK_S:
                        game.movePlayer(Direction.DOWN);
                        if (game.getMoveStatus() != MoveResult.INVALID_MOVE) {
                            render.movePlayer(KeyEvent.VK_S);
                        }
                    break;
                    case KeyEvent.VK_A:
                        game.movePlayer(Direction.LEFT);
                        if (game.getMoveStatus() != MoveResult.INVALID_MOVE) {
                            render.movePlayer(KeyEvent.VK_A);
                        }
                    break;
                    case KeyEvent.VK_D:
                        game.movePlayer(Direction.RIGHT);
                        if (game.getMoveStatus() != MoveResult.INVALID_MOVE) {
                            render.movePlayer(KeyEvent.VK_D);
                        }
                    break;
                }
                if (game.getMoveStatus() != MoveResult.INVALID_MOVE){
                    game.incrementTurn();
                    revalidate();
                    repaint();
                }
            }
        });
        revalidate();
        repaint();
    }

    private void gameLoop(){
        inFight = false;
        new Thread(() -> {
            statsPanel.setStats(game.getHero(), game.getTurn());
            while (true) {
                if (game.getMoveStatus() == MoveResult.ENEMY_ENCOUNTER){
                    game.setMoveStatus(MoveResult.INVALID_MOVE);
                    inFight = true;
                    if (popup.enemyEncounter() == JOptionPane.YES_OPTION){
                        if (game.playerFight() == false){
                            logs.setText(game.getCombatLogs());
                            statsPanel.setStats(game.getHero(), game.getTurn());
                            popup.gameOver();
                            game.getHero().deleteSave();
                            dispose();
                            return ;
                        }
                        else{
                            inFight = false;
                            logs.setText(game.getCombatLogs());
                            popup.fightWon();
                            game.dropArtifact();
                            if (game.getDropArtifact() != null)
                            {
                                if (popup.artifactDrop(game.getDropArtifact()) == JOptionPane.YES_OPTION){
                                    game.getHero().equipArtifact(game.getDropArtifact());
                                }
                                game.resetArtifact();
                            }
                            statsPanel.setStats(game.getHero(), game.getTurn());
                            revalidate();
                            repaint();
                        }
                    }
                    else{
                        if (game.playerTryToRun() == false){
                            if (game.playerFight() == false){
                                logs.setText(game.getCombatLogs());
                                statsPanel.setStats(game.getHero(), game.getTurn());
                                popup.gameOver();
                                game.getHero().deleteSave();
                                dispose();
                                return ;
                            }
                            else{
                                game.dropArtifact();
                                popup.fightWon();
                                inFight = false;
                                logs.setText(game.getCombatLogs());
                                if (game.getDropArtifact() != null)
                                {
                                    if (popup.artifactDrop(game.getDropArtifact()) == JOptionPane.YES_OPTION){
                                        game.getHero().equipArtifact(game.getDropArtifact());
                                    }
                                    game.resetArtifact();
                                }
                                statsPanel.setStats(game.getHero(), game.getTurn());
                            }
                        }
                        else{
                            inFight = false;
                            popup.runAway();
                        }
                    }
                }
                if (game.playerWon() && game.getMoveStatus() != MoveResult.ENEMY_ENCOUNTER){
                    game.saveGame();
                    if (popup.continuePlaying() == JOptionPane.YES_OPTION){
                        game.createaNewMap();
                        render.setNewMap(game.getMap());
                        inFight = false;
                    }
                    else{
                        popup.goodbye();
                        dispose();
                        return ;
                    }
                }
                Thread.yield();
            }
        }).start();
    }
}
