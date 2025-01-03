package ft.swingy.GUI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import ft.swingy.Game.ID;
import ft.swingy.Game.GameMapModel;
import ft.swingy.Game.Direction;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameRenderVIew extends JPanel{
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

    public GameRenderVIew(GameMapModel map) {
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

    public void setNewMap(GameMapModel map){
        this.map = map;
        mapTileSize = map.getSize() * 32;
        init = false;
        mapOffsetX = 0;
        mapOffsetY = 0;
        revalidate();
        repaint();
    }

	public void revertMoveRender(Direction input)
	{
        switch (input) {
			case DOWN:
				mapStartY += 32;
				mapOffsetY += 32;
				break;
			case UP:
				mapStartY -= 32;
				mapOffsetY -= 32;
				break;
			case RIGHT:
				mapStartX += 32;
				mapOffsetX += 32;
				break;
			case LEFT:
				mapStartX -= 32;
				mapOffsetX -= 32;
				break;
		}
        revalidate();
        repaint();
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
                mapStartX -= 32;
                mapOffsetX -= 32;
                break;
        }
        revalidate();
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

        if (!init){
            mapStartX = (this.getSize().width - mapTileSize) / 2;
            mapStartY = (this.getSize().height - mapTileSize) / 2;
            playerX = mapStartX + (map.getPlayerX() * 32);
            playerY = mapStartY + (map.getPlayerY() * 32);
            //Round up to 32 to be aligned with tiles
            playerX = ((playerX + 31) / 32) * 32;
            playerY = ((playerY + 31) / 32) * 32;
            init = true;
        }
        g.drawImage(playerTile, playerX, playerY, null);
        for (int i = 0; i < this.getSize().width; i += 32) {
            for (int j = 0; j < this.getSize().height; j += 32)
            {
                if (j >= mapStartY && i >= mapStartX && j < mapTileSize + mapStartY && i < mapTileSize + mapStartX){
                    if (map.getAt((i - mapStartX) / 32, (j - mapStartY) / 32) == ID.Enemy.getId())
                        g.drawImage(enemyTile, i, j, null);
                    else if (map.getAt((i - mapStartX) / 32, (j - mapStartY) / 32) == ID.Empty.getId())
                        g.drawImage(mapTile, i, j, null);
                }
                else
                    g.drawImage(oobTile, i, j, null);
            }
        }
        revalidate();
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

    public void updateGUIOnResize(){
        mapStartX = (this.getSize().width - mapTileSize) / 2;
        mapStartY = (this.getSize().height - mapTileSize) / 2;
        playerX = mapStartX + (map.getPlayerX() * 32);
        playerY = mapStartY + (map.getPlayerY() * 32);
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

