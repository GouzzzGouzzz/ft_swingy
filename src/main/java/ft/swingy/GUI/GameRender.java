package ft.swingy.GUI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.io.File;

import ft.swingy.Game.ID;
import ft.swingy.Game.GameMapModel;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameRender extends JPanel{
    private BufferedImage tileset;
    private int mapStartX;
    private int mapStartY;
    private int mapOffsetX;
    private int mapOffsetY;
    private int playerX;
    private int playerY;
    private int mapTileSize;
    private GameMapModel map;
    private boolean init;
    public boolean inFight;

    public GameRender(GameMapModel map) {
        this.map = map;
        mapTileSize = map.getSize() * 32;
        init = false;
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
                mapStartY += 32;
                mapOffsetY += 32;
                break;
            case KeyEvent.VK_S:
                mapStartY -= 32;
                mapOffsetY -= 32;
                break;
            case KeyEvent.VK_A:
                mapStartX += 32;
                mapOffsetX += 32;
                break;
            case KeyEvent.VK_D:
                mapOffsetX -= 32;
                mapStartX -= 32;
                break;
        }
        repaint();
    }

    private BufferedImage getTile(int[] pos) {
        return tileset.getSubimage(pos[0], pos[1], pos[2], pos[3]);
    }

    private void drawMap(Graphics g){

        final BufferedImage mapTile = getTile(Tiles.GRASS.getPos());
        final BufferedImage oobTile = getTile(Tiles.WATER.getPos());
        final BufferedImage playerTile = getTile(Tiles.PLAYER.getPos());
        final BufferedImage enemyTile = getTile(Tiles.ENEMY.getPos());
        final BufferedImage topTile = getTile(Tiles.TOP.getPos());
        final BufferedImage bottomTile = getTile(Tiles.BOTTOM.getPos());
        final BufferedImage leftTile = getTile(Tiles.LEFT.getPos());
        final BufferedImage rightTile = getTile(Tiles.RIGHT.getPos());
        final BufferedImage topLeftTile = getTile(Tiles.TOPLEFT.getPos());
        final BufferedImage topRightTile = getTile(Tiles.TOPRIGHT.getPos());
        final BufferedImage bottomLeftTile = getTile(Tiles.BOTTOMLEFT.getPos());
        final BufferedImage bottomRightTile = getTile(Tiles.BOTTOMRIGHT.getPos());

        if (!init){
            mapStartX = (this.getSize().width - mapTileSize) / 2;
            mapStartY = (this.getSize().height - mapTileSize) / 2;
            playerX = mapStartX + ((map.getSize() / 2) * 32);
            playerY = mapStartY + ((map.getSize() / 2) * 32);
            //Round up to 32 to be aligned with tiles
            playerX = ((playerX + 31) / 32) * 32;
            playerY = ((playerY + 31) / 32) * 32;
            init = true;
        }
        g.drawImage(playerTile,playerX, playerY, null);
        for (int i = 0; i < this.getSize().width; i += 32) {
            for (int j = 0; j < this.getSize().height; j += 32)
            {
                if (j >= mapStartY && i >= mapStartX && j < mapTileSize + mapStartY && i < mapTileSize + mapStartX){
                    if (map.getAt((i - mapStartX) /32, (j - mapStartY) / 32) == ID.Enemy.getId())
                        g.drawImage(enemyTile, i, j, null);
                    else if (map.getAt((i - mapStartX) / 32, (j - mapStartY) / 32) == ID.Empty.getId())
                        g.drawImage(mapTile, i, j, null);
                }
                else if (i + 32 >= mapStartX && i < mapStartX && j >= mapStartY && j < mapStartY + mapTileSize)
                    g.drawImage(leftTile, i, j, null);
                else if (j + 32 >= mapStartY && j < mapStartY && i >= mapStartX && i < mapStartX + mapTileSize)
                    g.drawImage(topTile, i, j, null);
                else if (i - 32 >= mapStartX && i < mapStartX + mapTileSize + 32 && j >= mapStartY && j < mapStartY + mapTileSize)
                    g.drawImage(rightTile, i, j, null);
                else if (j - 32 >= mapStartY && j < mapStartY + mapTileSize + 32 && i >= mapStartX && i < mapStartX + mapTileSize)
                    g.drawImage(bottomTile, i, j, null);
                else if (i + 32 >= mapStartX && i < mapStartX && j <= mapStartY && j + 32 >= mapStartY)
                    g.drawImage(topLeftTile, i, j, null);
                else if (i - 32 <= mapStartX + mapTileSize && i > mapStartX + mapTileSize && j <= mapStartY && j + 32 >= mapStartY)
                    g.drawImage(topRightTile, i, j, null);
                else if (i + 32 >= mapStartX && i < mapStartX && j - 32 <= mapStartY + mapTileSize && j > mapStartY + mapTileSize)
                    g.drawImage(bottomLeftTile, i, j, null);
                else if (i - 32 <= mapStartX + mapTileSize && i > mapStartX + mapTileSize && j - 32 <= mapStartY + mapTileSize && j > mapStartY + mapTileSize)
                    g.drawImage(bottomRightTile, i, j, null);
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

    public void updateGUIOnResize(){
        mapStartX = (this.getSize().width - mapTileSize) / 2;
        mapStartY = (this.getSize().height - mapTileSize) / 2;
        playerX = mapStartX + ((map.getSize() / 2) * 32);
        playerY = mapStartY + ((map.getSize() / 2) * 32);
        mapStartX += mapOffsetX;
        mapStartY += mapOffsetY;
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

