package ft.swingy.Game;

import java.awt.event.KeyEvent;

public class GameMap {
    int size;
    int playerX;
    int playerY;
    int[][] map;

    public GameMap(int size) {
        this.size = size;
        map = new int[size][size];
        playerX = size / 2;
        playerY = size / 2;
        map[playerX][playerY] = ID.Player.getId();
    }

    public int getSize() {
        return size;
    }

    public int getPlayerPosXAsTile() {
        return playerX * 32;
    }

    public int getPlayerPosYAsTile() {
        return playerY * 32;
    }

    public boolean movePlayer(int key) {
        switch (key) {
            case KeyEvent.VK_W:
                if (playerY + 1 < size){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerY++;
                    map[playerX][playerY] = ID.Player.getId();
                    return true;
                }
                break;
            case KeyEvent.VK_S:
                if (playerY - 1 >= 0){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerY--;
                    map[playerX][playerY] = ID.Player.getId();
                    return true;
                }
                break;
            case KeyEvent.VK_A:
                if (playerX - 1 >= 0){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerX--;
                    map[playerX][playerY] = ID.Player.getId();
                    return true;
                }
                break;
            case KeyEvent.VK_D:
                if (playerX + 1 < size){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerX++;
                    map[playerX][playerY] = ID.Player.getId();
                    return true;
                }
                break;
        }
        return false;
    }
}
