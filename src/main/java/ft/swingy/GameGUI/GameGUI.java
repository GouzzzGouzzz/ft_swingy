package ft.swingy.GameGUI;

import javax.swing.*;

import java.awt.BorderLayout;
import ft.swingy.Game.GameMap;
import ft.swingy.Hero.Hero;

//This contains all GUI components to interact with 
public class GameGUI extends JFrame{
    private JButton switchModeBtn;
    private Board board;
    private FightScreen fightScreen;
    private StatsDisplay statsDisplay;

    public GameGUI(GameMap map, Hero hero) {
        setTitle("Swingy - Dungeon Crawler");
        switchModeBtn = new JButton("Console Mode");
        statsDisplay = new StatsDisplay(hero);
        board = new Board(map, this);
        fightScreen = new FightScreen(board, hero, statsDisplay);

        //frame
        setVisible(false);
        add(statsDisplay, BorderLayout.WEST);
        add(board, BorderLayout.CENTER);
        add(switchModeBtn, BorderLayout.SOUTH);
        add(fightScreen, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(false);
        setVisible(true);
        //board
        board.setFocusable(true);
        board.requestFocusInWindow();
    }

    public void toggleFightText() {
        fightScreen.toggleVisibility();
    }

}
