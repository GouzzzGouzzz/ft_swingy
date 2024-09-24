package ft.swingy.Game;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class LoaderBean {

    @Min(value = 0, message = "ID must be a positive number")
    private int id = 0;

    @Pattern(regexp = "yes|no", message = "You must enter 'yes' or 'no'")
    private String loadChoice;


    public void setloadChoice(String loadChoice) {
        this.loadChoice = loadChoice;
    }

    public String getloadChoice() {
        return this.loadChoice;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

}
