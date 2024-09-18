package ft.swingy.GameGUI;

import javax.swing.*;

import java.awt.BorderLayout;
import ft.swingy.Game.GameMap;
import ft.swingy.Hero.Hero;


public class GameGUI {
    private JFrame frame;
    private JButton switchModeBtn;
    private Board board;
    private FightScreen fightScreen;
    private StatsDisplay statsDisplay;

    public GameGUI(GameMap map, Hero hero) {
        frame = new JFrame("Swingy - Dungeon Crawler");
        switchModeBtn = new JButton("Console Mode");
        statsDisplay = new StatsDisplay(hero);
        board = new Board(map, this);
        fightScreen = new FightScreen(board, hero, statsDisplay);

        //frame
        frame.setVisible(false);
        frame.add(statsDisplay, BorderLayout.WEST);
        frame.add(board, BorderLayout.CENTER);
        frame.add(switchModeBtn, BorderLayout.SOUTH);
        frame.add(fightScreen, BorderLayout.NORTH);
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

    public void toggleFightText() {
        fightScreen.toggleVisibility();
    }

}
