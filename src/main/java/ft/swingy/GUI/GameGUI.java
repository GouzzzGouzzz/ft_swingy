package ft.swingy.GUI;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ft.swingy.Game.Direction;
import ft.swingy.Game.GameMapModel;
import ft.swingy.Game.GameModel;
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
    private volatile boolean inFight;

    public GameGUI(GameMapModel map, Hero hero) {

        render = new GameRender(map);
        layeredPane = new JLayeredPane();
        statsPanel = new StatsPanel();
        logs = new LogsPanel();
        layeredPane.setPreferredSize(new Dimension(813, 813));
        game = new GameModel(hero, map);
        popup = new PopUp(this);

        //frame
        setVisible(false);
        setTitle("Swingy - Dungeon Crawler");
        setContentPane(layeredPane);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(813, 813);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
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
        //23 par 23 tiles
        render.setBounds(0, 0, 737, 737);



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
        setVisible(true);
        repaint();
        gameLoop();
    }

    private void gameLoop(){
        inFight = false;
        new Thread(() -> {
            int choice;
            while (true) {
                if (isValid() == false){
                    System.out.println("Invalid frame");
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
