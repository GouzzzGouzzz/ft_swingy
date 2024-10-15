package ft.swingy.Game;
import java.util.Random;

public class GameMapModel {
    public int size;
    int playerX;
    int playerY;
    public int[][] map;

    public GameMapModel(int level) {
        this.size = (level - 1) * 5 + 10 - (level % 2);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        map = new int[size][size];
        playerX = size / 2;
        playerY = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (random.nextInt(100) < 25)
                    map[i][j] = ID.Enemy.getId();
                else
                    map[i][j] = ID.Empty.getId();
            }
        }
        map[playerX][playerY] = ID.Player.getId();
        System.out.println("coord: " + playerX + " " + playerY);
        System.out.println("Size: " + size);
    }

    public int getAt(int x, int y) {
        return map[x][y];
    }

    public int getSize() {
        return size;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public MoveResult movePlayer(Direction direction) {
        switch (direction) {
            case UP:
                if (playerY - 1 >= 0){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerY--;
                    if (map[playerX][playerY] == ID.Enemy.getId()){
                        map[playerX][playerY] = ID.Player.getId();
                        return MoveResult.ENEMY_ENCOUNTER;
                    }
                    map[playerX][playerY] = ID.Player.getId();
                    return MoveResult.VALID_MOVE;
                }
                break;
            case DOWN:
                if (playerY + 1 < size){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerY++;
                    if (map[playerX][playerY] == ID.Enemy.getId()){
                        map[playerX][playerY] = ID.Player.getId();
                        return MoveResult.ENEMY_ENCOUNTER;
                    }
                    map[playerX][playerY] = ID.Player.getId();
                    return MoveResult.VALID_MOVE;
                }
                break;
            case LEFT:
                if (playerX - 1 >= 0){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerX--;
                    if (map[playerX][playerY] == ID.Enemy.getId()){
                        map[playerX][playerY] = ID.Player.getId();
                        return MoveResult.ENEMY_ENCOUNTER;
                    }
                    map[playerX][playerY] = ID.Player.getId();
                    return MoveResult.VALID_MOVE;
                }
                break;
            case RIGHT:
                if (playerX + 1 < size){
                    map[playerX][playerY] = ID.Empty.getId();
                    playerX++;
                    if (map[playerX][playerY] == ID.Enemy.getId()){
                        map[playerX][playerY] = ID.Player.getId();
                        return MoveResult.ENEMY_ENCOUNTER;
                    }
                    map[playerX][playerY] = ID.Player.getId();
                    return MoveResult.VALID_MOVE;
                }
                break;
        }
        return MoveResult.INVALID_MOVE;
    }

    public boolean playerReachedEdge() {
        if (playerX == 0 || playerX == size - 1 || playerY == 0 || playerY == size - 1)
            return true;
        return false;
    }
}
