package ft.swingy.Game;

public enum ID {
    Empty(0),
    Player(1),
    Enemy(2);

    private final int id;

    ID(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
