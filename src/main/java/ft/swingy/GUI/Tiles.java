package ft.swingy.GUI;

public enum Tiles {
    GRASS(32, 32, 32, 32),

    TOP(32, 0, 32, 32),
    BOTTOM(32, 64, 32, 32),
    LEFT(0, 32, 32, 32),
    RIGHT(64, 32, 32, 32),
    TOPLEFT(0, 0, 32, 32),
    TOPRIGHT(64, 0, 32, 32),
    BOTTOMLEFT(0, 64, 32, 32),
    BOTTOMRIGHT(64, 64, 32, 32),

    PLAYER(352, 192, 32, 32),
    WATER(224, 32, 32, 32),
    ENEMY(320, 192, 32, 32);

    private final int row;
    private final int col;
    private final int x;
    private final int y;

    Tiles(int col, int row, int x, int y) {
        this.col = col;
        this.row = row;
        this.x = x;
        this.y = y;
    }

    public int[] getPos() {
        int pos[] = {col, row, x, y};
        return pos;
    }
}
