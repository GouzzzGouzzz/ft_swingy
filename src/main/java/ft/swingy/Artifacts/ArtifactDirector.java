package ft.swingy.Artifacts;

import java.util.Random;

public class ArtifactDirector {
    private static final String[] types = {"Weapon", "Armor", "Helm"};

    public static Artifact randomArtifacts(ArtifactBuilder builder, int level){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        random.nextInt();
        int type = (int)(Math.random() * 3);
        builder.reset();
        builder.setType(types[type]);
        builder.setQuality(random.nextInt(1, level * 10 + 50));
        return builder.getArtifact();
    }

    public static Artifact dropRandomArtifact(int level){
        ArtifactBuilder builder = new ArtifactBuilder();
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        if (random.nextInt(1, 100) > 15){
            return null;
        }
        return randomArtifacts(builder, level);
    }

    public Artifact buildWithQuality(ArtifactBuilder builder, String type, int quality){
        builder.reset();
        builder.setType(type);
        builder.setQuality(quality);
        return builder.getArtifact();
    }
}
