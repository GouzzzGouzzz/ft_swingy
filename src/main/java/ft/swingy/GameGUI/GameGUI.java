package ft.swingy.GameGUI;

import javax.swing.*;
import java.awt.BorderLayout;
import ft.swingy.Game.GameMap;


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
    }
}
