package ft.swingy.Artifacts;

public class Artifact {
    int quality; // 10 - 10000
    int tier;// 1 = 10 - 1000, 2 = 1001 - 5000, 3 = 5001 - 10000
    String type; // Weapon, Armor, Helm

    public Artifact(){};

    public void printStats(){
        System.out.println("Type: " + type);
        System.out.println("Quality: " + quality);
        System.out.println("Tier: " + tier);
    }
}
