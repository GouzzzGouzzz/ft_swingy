package ft.swingy.Game;

import jakarta.validation.constraints.Min;

public class LoaderBean {

    @Min(value = 0, message = "ID must be a positive number")
    private int id = 0;

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

}
