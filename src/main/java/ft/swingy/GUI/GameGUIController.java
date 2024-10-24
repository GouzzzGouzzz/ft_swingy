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
    private PopUpView popup;
    private Hero hero;
    private StatsPanelView statsSelectPanel;
    private HeroCreationView heroCreation;
    private HeroLoaderView heroLoader;
    private GameModel game;
    private volatile boolean inFight;
    private volatile boolean renderTime;

    public GameGUIController() {
        setVisible(false);
        setTitle("Swingy - Dungeon Crawler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(813, 813);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
        renderTime = false;
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
        else if (popup.loadOrCreate() == JOptionPane.NO_OPTION){
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
                if (inFight == true || renderTime == true)
                    return ;
                renderTime = true;
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
                    statsPanel.setStats(game.getHero(), game.getTurn());
                    revalidate();
                    repaint();
                }
                renderTime = false;
            }
        });
        revalidate();
        repaint();
    }

    private void handleDropArtifact(){
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
        inFight = false;
        revalidate();
        repaint();
    }

    private void handlePlayerWin(){
        game.saveGame();
        if (popup.continuePlaying() == JOptionPane.YES_OPTION){
            game.createaNewMap();
            render.setNewMap(game.getMap());
            inFight = false;
            revalidate();
            repaint();
        }
        else{
            popup.goodbye();
            dispose();
        }
    }

    private void handlePlayerLost(){
        logs.setText(game.getCombatLogs());
        statsPanel.setStats(game.getHero(), game.getTurn());
        popup.gameOver();
        game.getHero().deleteSave();
        dispose();
    }

    private void gameLoop(){
        inFight = false;
        new Thread(() -> {
            MoveResult moveStatus = MoveResult.INVALID_MOVE;
            boolean result;
            statsPanel.setStats(game.getHero(), game.getTurn());
            while (true) {
                moveStatus = game.getMoveStatus();
                if (moveStatus == MoveResult.ENEMY_ENCOUNTER && renderTime == false){
                    inFight = true;
                    game.setMoveStatus(MoveResult.INVALID_MOVE);
                    if (popup.enemyEncounter() == JOptionPane.YES_OPTION){
                        result = game.playerFight();
                        if (result == false){
                            handlePlayerLost();
                            return ;
                        }
                        else{
                            handleDropArtifact();
                        }
                    }
                    else{
                        result = game.playerTryToRun();
                        if (result == false){
                            result = game.playerFight();
                            if (result == false){
                                handlePlayerLost();
                                return ;
                            }
                            else{
                                handleDropArtifact();
                            }
                        }
                        else{
                            inFight = false;
                            popup.runAway();
                        }
                    }
                }
                if (game.playerWon() && game.getMoveStatus() != MoveResult.ENEMY_ENCOUNTER){
                    handlePlayerWin();
                }
                Thread.yield();
            }
        }).start();
    }
}
