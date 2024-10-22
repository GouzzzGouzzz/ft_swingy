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
import ft.swingy.Game.LoaderModel;
import ft.swingy.Game.MoveResult;
import ft.swingy.Hero.Hero;

//This contains all GUI components to interact with
public class GameGUI extends JFrame{
    private GameRender render;
    private JLayeredPane layeredPane;
    private StatsPanel statsPanel;
    private LogsPanel logs;
    private GameModel game;
    private PopUp popup;
    private Hero hero;
    private volatile boolean inFight;

    public GameGUI() {
        setVisible(false);
        setTitle("Swingy - Dungeon Crawler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(813, 813);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);

        layeredPane = new JLayeredPane();
        statsPanel = new StatsPanel(true);
        logs = new LogsPanel();

        hero = loadOrCreateHero();
        if (true)
            return ;
        game = new GameModel(hero, null);
        popup = new PopUp(this);
        game.createaNewMap();
        render = new GameRender(game.getMap());

        //frame


        //GamePane
        setupGamePane();
        addGameListener();

        setVisible(true);
        repaint();
        gameLoop();
    }

    private Hero loadOrCreateHero(){
        StatsPanel stats = new StatsPanel(false);
        LoaderModel loader = new LoaderModel();
        HeroList heroList = new HeroList(stats);
        JScrollPane scroll = new JScrollPane(heroList);

        setLayout(new BorderLayout());
        stats.setPreferredSize(new Dimension(200, this.getHeight()));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);
        add(stats, BorderLayout.WEST);
        setVisible(true);
        return null;
    }

    private void setupGamePane(){
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
                }
            }
        });
    }

    private void gameLoop(){
        inFight = false;
        new Thread(() -> {
            int choice;
            while (true) {
                if (isValid() == false){
                    // System.out.println("Invalid frame");
                }
                if (game.getMoveStatus() == MoveResult.ENEMY_ENCOUNTER){
                    game.setMoveStatus(MoveResult.INVALID_MOVE);
                    inFight = true;
                    choice = popup.enemyEncounter();
                    if (choice == JOptionPane.YES_OPTION){
                        if (game.playerFight() == false){
                            logs.setText(game.getCombatLogs());
                            popup.gameOver();
                            return ;
                        }
                        else{
                            inFight = false;
                            logs.setText(game.getCombatLogs());
                            popup.fightWon();
                            game.dropArtifact();
                            if (game.getDropArtifact() != null)
                            {
                                choice = popup.artifactDrop(game.getDropArtifact());
                                if (choice == JOptionPane.YES_OPTION){
                                    game.getHero().equipArtifact(game.getDropArtifact());
                                }
                                game.resetArtifact();
                            }
                        }
                    }
                    else{
                        if (game.playerTryToRun() == false){
                            if (game.playerFight() == false){
                                logs.setText(game.getCombatLogs());
                                popup.gameOver();
                                return ;
                            }
                            else{
                                game.dropArtifact();
                                popup.fightWon();
                                inFight = false;
                                logs.setText(game.getCombatLogs());
                                if (game.getDropArtifact() != null)
                                {
                                    choice = popup.artifactDrop(game.getDropArtifact());
                                    if (choice == JOptionPane.YES_OPTION){
                                        game.getHero().equipArtifact(game.getDropArtifact());
                                    }
                                    game.resetArtifact();
                                }
                            }
                        }
                        else{
                            inFight = false;
                            popup.runAway();
                        }
                    }
                }
                Thread.yield();
            }
        }).start();
    }
}
