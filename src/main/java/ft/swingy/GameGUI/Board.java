package ft.swingy.GameGUI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private boolean init = false;

    public Board(GameMap map){
        this.map = map;
        mapTileSize = map.getSize() * 32;
        try {
            tileset = ImageIO.read(new File("src/main/java/ft/swingy/Assets/tiles.png"));
        }
        catch (Exception e) {
            System.out.println("ERROR\n");
        }
    }

    public void movePlayer(int key){
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
                else if (j >= mapStartY && i >= mapStartX && j < mapTileSize + mapStartY && i < mapTileSize + mapStartX)
                    g.drawImage(mapTile, i, j, null);
                else
                    g.drawImage(oobTile, i, j, null);
            }
        }
    }

    //this doesn't work as intended
    //BUG : player position is not correctly updated
    public void updatePlayerPosition(){
        if (!init)
            return;
        mapStartX = (this.getSize().width - mapTileSize) / 2;
        mapStartY = (this.getSize().height - mapTileSize) / 2;
        playerX = mapStartX + map.getPlayerPosXAsTile();
        playerY = mapStartY + map.getPlayerPosYAsTile();
        //Round up to 32 to be aligned with tiles
        playerX = ((playerX + 31) / 32) * 32;
        playerY = ((playerY + 31) / 32) * 32;
        repaint();
    }

    private void drawGrid(Graphics g){
        for (int i = 0; i < this.getSize().width; i += 32) {
            g.drawLine(i, 0, i, this.getSize().height);
        }
        for (int j = 0; j < this.getSize().height; j += 32) {
            g.drawLine(0, j, this.getSize().width, j);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawGrid(g);
    }
}

