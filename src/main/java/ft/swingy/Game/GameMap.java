package ft.swingy.Game;

import java.awt.event.KeyEvent;
import java.util.Random;

public class GameMap {
    public int size;
    int playerX;
    int playerY;
    public int[][] map;

    public GameMap(int level) {
        this.size = (level - 1) * 5 + 10 - (level % 2);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        map = new int[size][size];
        playerX = size / 2;
        playerY = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (random.nextInt(100) < 50)
                    map[i][j] = ID.Enemy.getId();
                else
                    map[i][j] = ID.Empty.getId();
            }
        }
        map[playerX][playerY] = ID.Player.getId();
    }

    public int getAt(int x, int y) {
        return map[x][y];
    }

    public int getSize() {
        return size;
    }

    public int movePlayer(int key) {
        switch (key) {
            case KeyEvent.VK_W:
                if (playerY - 1 >= 0){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerY--;
                    if (map[playerX][playerY] == ID.Enemy.getId()){
                        map[playerX][playerY] = ID.Player.getId();
                        return 2;
                    }
                    map[playerX][playerY] = ID.Player.getId();
                    return 1;
                }
                break;
            case KeyEvent.VK_S:
                if (playerY + 1 < size){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerY++;
                    if (map[playerX][playerY] == ID.Enemy.getId()){
                        map[playerX][playerY] = ID.Player.getId();
                        return 2;
                    }
                    map[playerX][playerY] = ID.Player.getId();
                    return 1;
                }
                break;
            case KeyEvent.VK_A:
                if (playerX - 1 >= 0){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerX--;
                    if (map[playerX][playerY] == ID.Enemy.getId()){
                        map[playerX][playerY] = ID.Player.getId();
                        return 2;
                    }
                    map[playerX][playerY] = ID.Player.getId();
                    return 1;
                }
                break;
            case KeyEvent.VK_D:
                if (playerX + 1 < size){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerX++;
                    if (map[playerX][playerY] == ID.Enemy.getId()){
                        map[playerX][playerY] = ID.Player.getId();
                        return 2;
                    }
                    map[playerX][playerY] = ID.Player.getId();
                    return 1;
                }
                break;
        }
        return 0;
    }
}
