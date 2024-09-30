package ft.swingy.Hero;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BuilderBean {
    @Size(min = 1, max = 20, message = "Save corrupted : Incorrect named hero")
    private String name = "";

    @Pattern(regexp = "Warrior|Rogue", message = "Save corrupted : Invalid hero type")
    private String type = "";

    @Min(value = 1, message = "Save corrupted : Invalid hero level")
    @Max(value = 100, message = "Save corrupted : Invalid hero level")
    private int level = -1;

    @Min(value = 0, message = "Save corrupted : Invalid hero experience")
    @Max(value = Integer.MAX_VALUE, message = "Save corrupted : Invalid hero experience")
    private int experience = -1;

    @Min(value = 0, message = "Save corrupted : Invalid hero attack")
    @Max(value = Integer.MAX_VALUE, message = "Save corrupted : Invalid hero attack")
    private int attack = 1;

    @Min(value = 0, message = "Save corrupted : Invalid hero defense")
    @Max(value = Integer.MAX_VALUE, message = "Save corrupted : Invalid hero defense")
    private int defense = -1;

    @Min(value = 1, message = "Save corrupted : Invalid hero HP")
    @Max(value = Integer.MAX_VALUE, message = "Save corrupted : Invalid hero HP")
    private int HP = -1;


    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHP() {
        return HP;
    }
}
