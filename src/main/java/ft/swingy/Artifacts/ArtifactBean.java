package ft.swingy.Artifacts;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ArtifactBean {
    @Min(value = 0, message = "Save corrupted : Invalid artifact quality")
    @Max(value = 1050, message = "Save corrupted : Invalid artifact quality")
    int weaponQuality, armorQuality, helmQuality;

    public void setWeapon(int quality) {
        this.weaponQuality = quality;
    }

    public void setArmor(int quality) {
        this.armorQuality = quality;
    }

    public void setHelm(int quality) {
        this.helmQuality = quality;
    }

    public int getWeapon() {
        return weaponQuality;
    }

    public int getArmor() {
        return armorQuality;
    }

    public int getHelm() {
        return helmQuality;
    }
}
