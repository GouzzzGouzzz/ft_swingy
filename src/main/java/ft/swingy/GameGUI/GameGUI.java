package ft.swingy.GameGUI;

import javax.swing.*;

import java.awt.BorderLayout;
import ft.swingy.Game.GameMapModel;
import ft.swingy.Hero.Hero;

//This contains all GUI components to interact with
public class GameGUI extends JFrame{
    private Board board;
    // private FightScreen fightScreen;
    // private StatsDisplay statsDisplay;

    public GameGUI(GameMapModel map, Hero hero) {
        setTitle("Swingy - Dungeon Crawler");
        // statsDisplay = new StatsDisplay(hero);
        board = new Board(map, this);
        // fightScreen = new FightScreen(board, hero, statsDisplay);

        //frame
        setVisible(false);
        // add(statsDisplay, BorderLayout.WEST);
        add(board, BorderLayout.CENTER);
        // add(fightScreen, BorderLayout.NORTH);
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

    // public void toggleFightText() {
    //     fightScreen.toggleVisibility();
    // }

}
