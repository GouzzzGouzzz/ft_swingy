package ft.swingy.Artifacts;

public class Artifact {
    int quality; // added stats (flat)
    String type; // Weapon, Armor, Helm

    public Artifact(){};

    public void printArtifact(){
        // System.out.println("You have found an " + type + " artifact of quality " + quality + " !");
    }

    public String getType() {
        return type;
    }

    public int getQuality() {
        return quality;
    }

}
