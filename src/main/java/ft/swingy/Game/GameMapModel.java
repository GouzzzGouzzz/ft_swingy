package ft.swingy.Game;
import java.util.Random;

public class GameMapModel {
    public int size;
	Direction lastMove;
    int playerX;
    int playerY;
    public int[][] map;

    public GameMapModel(int level) {
		lastMove = null;
        this.size = (level - 1) * 5 + 10 - (level % 2);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        map = new int[size][size];
        playerX = size / 2;
        playerY = size / 2;
        for (int i = 1; i < size-1; i++) {
            for (int j = 1; j < size-1; j++) {
                if (random.nextInt(100) < 25)
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

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

	public Direction getLastMove(){
		return lastMove;
	}

	public Direction revertInput(Direction input){
		switch (input){
			case UP:
				return Direction.DOWN;
			case DOWN:
				return Direction.UP;
			case LEFT:
				return Direction.RIGHT;
			case RIGHT:
				return Direction.LEFT;
		}
		return input;
	}

	public void revertPlayerMove(){
		Direction reverted;
		int prevX, prevY;

		prevX = playerX;
		prevY = playerY;
		reverted = revertInput(lastMove);
		movePlayer(reverted);
		map[prevX][prevY] = ID.Enemy.getId();
	}

    public MoveResult movePlayer(Direction direction) {
        switch (direction) {
            case UP:
                if (playerY - 1 >= 0){
					lastMove = Direction.UP;
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
					lastMove = Direction.DOWN;
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
					lastMove = Direction.LEFT;
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
					lastMove = Direction.RIGHT;
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
