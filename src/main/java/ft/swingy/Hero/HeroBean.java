package ft.swingy.Hero;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class HeroBean {
    @Size(min = 1, max = 20, message = "Name must be between 1 and 20 characters")
    private String name = "xxxxxx";

    @Min(value = 1, message = "Choose a correct class")
    @Max(value = 2, message = "Choose a correct class")
    private int type = 1;

    public void setType(int type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setWeapon(int quality) {
        // hero.artifacts[0] = weapon;
    }

    public void setArmor(int quality) {
        // hero.artifacts[1] = armor;
    }

    public void setHelm(int quality) {
        // ArtifactDirector director = new ArtifactDirector();
        // hero.artifacts[2] = director.buildWithQuality("Helm", quality);
    }
    
}
