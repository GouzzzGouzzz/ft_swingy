package ft.swingy.GameGUI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.io.File;

import ft.swingy.Game.ID;
import ft.swingy.Game.GameMap;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Board extends JPanel{
    private BufferedImage tileset;
    private int mapStartX;
    private int mapStartY;
    private int playerX;
    private int playerY;
    private final int mapTileSize;
    private GameMap map;
    private boolean init;

    public Board(GameMap map){
        this.map = map;
        mapTileSize = map.getSize() * 32;
        init = false;
        try {
            tileset = ImageIO.read(new File("src/main/java/ft/swingy/Assets/tiles.png"));
        }
        catch (Exception e) {
            System.out.println("ERROR\n");
        }
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updatePlayerPosition();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (map.movePlayer(KeyEvent.VK_W))
                            movePlayer(KeyEvent.VK_W);
                        break;
                    case KeyEvent.VK_S:
                        if (map.movePlayer(KeyEvent.VK_S))
                            movePlayer(KeyEvent.VK_S);
                        break;
                    case KeyEvent.VK_A:
                        if (map.movePlayer(KeyEvent.VK_A))
                            movePlayer(KeyEvent.VK_A);
                        break;
                    case KeyEvent.VK_D:
                        if (map.movePlayer(KeyEvent.VK_D))
                            movePlayer(KeyEvent.VK_D);
                        break;
                }
            }
        });
    }

    private void movePlayer(int key){
        switch (key) {
            case KeyEvent.VK_W:
                playerY -= 32;
                break;
            case KeyEvent.VK_S:
                playerY += 32;
                break;
            case KeyEvent.VK_A:
                playerX -= 32;
                break;
            case KeyEvent.VK_D:
                playerX += 32;
                break;
        }
        repaint();
    }

    private BufferedImage getTile(int[] pos) {
        return tileset.getSubimage(pos[0], pos[1], pos[2], pos[3]);
    }

    private void drawMap(Graphics g){
        BufferedImage mapTile = getTile(Tiles.GRASS.getPos());
        BufferedImage oobTile = getTile(Tiles.WATER.getPos());
        BufferedImage playerTile = getTile(Tiles.TEMP.getPos());
        BufferedImage enemyTile = getTile(Tiles.Enemy.getPos());
        mapStartX = (this.getSize().width - mapTileSize) / 2;
        mapStartY = (this.getSize().height - mapTileSize) / 2;
        if (!init){
            playerX = mapStartX + map.getPlayerPosXAsTile();
            playerY = mapStartY + map.getPlayerPosYAsTile();
            //Round up to 32 to be aligned with tiles
            playerX = ((playerX + 31) / 32) * 32;
            playerY = ((playerY + 31) / 32) * 32;
            init = true;
        }

        for (int i = 0; i < this.getSize().width; i += 32) {
            for (int j = 0; j < this.getSize().height; j += 32) {
                if (i == playerX && j == playerY){
                    g.drawImage(playerTile, i, j, null);
                }
                else if (j >= mapStartY && i >= mapStartX && j < mapTileSize + mapStartY && i < mapTileSize + mapStartX){
                    if (map.getAt((i - mapStartX) /32, (j - mapStartY)/32) == ID.Enemy.getId()){
                        g.drawImage(enemyTile, i, j, null);
                    }
                    else
                        g.drawImage(mapTile, i, j, null);
                }
                else
                    g.drawImage(oobTile, i, j, null);
            }
        }
    }

    private void drawGrid(Graphics g){
        for (int i = 0; i < this.getSize().width; i += 32) {
            g.drawLine(i, 0, i, this.getSize().height);
        }
        for (int j = 0; j < this.getSize().height; j += 32) {
            g.drawLine(0, j, this.getSize().width, j);
        }
    }

    private void updatePlayerPosition(){
        playerX = mapStartX + map.getPlayerPosXAsTile();
        playerY = mapStartY + map.getPlayerPosYAsTile();
        playerX = ((playerX + 31) / 32) * 32;
        playerY = ((playerY + 31) / 32) * 32;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawGrid(g);
    }
}

