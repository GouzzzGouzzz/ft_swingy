package ft.swingy.GameGUI;

public enum Tiles {
    GRASS(32, 32, 32, 32),
    TEMP(0, 0, 32, 32),
    WATER(224, 32, 32, 32),
    Enemy(320, 192, 32, 32);

    private int row;
    private int col;
    private int x;
    private int y;

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
