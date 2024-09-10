package ft.swingy.GUI;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class GUI {
    public static void createGui() {
        JFrame frame = new JFrame("Swingy - Dungeon Crawler");
        JButton switchModeBtn = new JButton("Console Mode");
        //frame
        frame.setVisible(false);
        frame.getContentPane().add(switchModeBtn, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
