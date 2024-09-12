package ft.swingy.GameGUI;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.RenderingHints.Key;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ft.swingy.Game.GameMap;
import ft.swingy.GameGUI.Board;


public class GameGUI {
    public static void createGui(GameMap map) {
        JFrame frame = new JFrame("Swingy - Dungeon Crawler");
        JButton switchModeBtn = new JButton("Console Mode");
        Board board = new Board(map);

        //frame
        frame.setVisible(false);
        frame.getContentPane().add(board, BorderLayout.CENTER);
        frame.getContentPane().add(switchModeBtn, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setFocusable(false);
        frame.setVisible(true);
        //board
        board.setFocusable(true);
        board.requestFocusInWindow();

        board.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                board.updatePlayerPosition();
            }
        });

        board.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (map.movePlayer(KeyEvent.VK_W))
                            board.movePlayer(KeyEvent.VK_W);
                        break;
                    case KeyEvent.VK_S:
                        if (map.movePlayer(KeyEvent.VK_S))
                            board.movePlayer(KeyEvent.VK_S);
                        break;
                    case KeyEvent.VK_A:
                        if (map.movePlayer(KeyEvent.VK_A))
                            board.movePlayer(KeyEvent.VK_A);
                        break;
                    case KeyEvent.VK_D:
                        if (map.movePlayer(KeyEvent.VK_D))
                            board.movePlayer(KeyEvent.VK_D);
                        break;
                }
            }
        });
    }
}
