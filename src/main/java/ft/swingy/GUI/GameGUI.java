package ft.swingy.GUI;

import javax.swing.*;

import java.awt.Color;
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
    JLayeredPane layeredPane;
    StatsPanel statsPanel;
    LogsPanel logs;
    GameMapModel map;
    GameModel game;

    public GameGUI(GameMapModel map, Hero hero) {

        render = new GameRender(map);
        layeredPane = new JLayeredPane();
        statsPanel = new StatsPanel();
        logs = new LogsPanel();
        layeredPane.setPreferredSize(new Dimension(813, 813));
        game = new GameModel(hero, map);

        setVisible(false);
        setTitle("Swingy - Dungeon Crawler");
        setContentPane(layeredPane);

        //frame
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

        //board
        render.setLayout(null);
        // render.requestFocusInWindow();
        //23 par 23 tiles
        render.setBounds(0, 0, 737, 737);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // board.setBounds(0, 0, getWidth()-76, getHeight()-76);
                // board.updateGUIOnResize();

                // System.out.println("Resized" + e.getComponent().getSize());
                setSize(813, 813);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
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
                        game.movePlayer(Direction.RIGHT);
                        if (game.getMoveStatus() != MoveResult.INVALID_MOVE) {
                            render.movePlayer(KeyEvent.VK_A);
                        }
                    break;
                    case KeyEvent.VK_D:
                        game.movePlayer(Direction.LEFT);
                        if (game.getMoveStatus() != MoveResult.INVALID_MOVE) {
                            render.movePlayer(KeyEvent.VK_D);
                        }
                    break;
                }
            }
        });
        setVisible(true);
    }
}
