package ft.swingy.Artifacts;

import java.util.Random;

public class ArtifactDirector {
    private final String[] types = {"Weapon", "Armor", "Helm"};

    public Artifact randomArtifacts(ArtifactBuilder builder, int level){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int type = (int)(Math.random() * 3);
        builder.reset();
        builder.setType(types[type]);
        builder.setQuality((int)(random.nextInt(1, level * 10 + 50)));
        return builder.getArtifact();
    }
}
