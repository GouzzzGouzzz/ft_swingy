package ft.swingy.Artifacts;

public class Artifact {
    int quality; // added stats (flat)
    String type; // Weapon, Armor, Helm

    public Artifact(){};

    public void printStats(){
        System.out.println("Type: " + type);
        System.out.println("Quality: " + quality);
    }

    public String getType() {
        return type;
    }

    public int getQuality() {
        return quality;
    }

}
