package ft.swingy.Artifacts;

import ft.swingy.Artifacts.Artifact;

public class ArtifactDirector {
    private final String[] types = {"Weapon", "Armor", "Helm"};

    public Artifact makeT1(ArtifactBuilder builder){
        String type;
        builder.setQuality((int)(Math.random() * 1000 + 10));
        builder.setTier(1);
        type = types[(int)(Math.random() * 3)];
        builder.setType(type);
        return builder.getArtifact();
    }

    public Artifact makeT2(ArtifactBuilder builder){
        String type;
        builder.setQuality((int)(Math.random() * 4000 + 1001));
        builder.setTier(2);
        type = types[(int)(Math.random() * 3)];
        builder.setType(type);
        return builder.getArtifact();
    }

    public Artifact makeT3(ArtifactBuilder builder){
        String type;
        builder.setQuality((int)(Math.random() * 5000 + 5001));
        builder.setTier(3);
        type = types[(int)(Math.random() * 3)];
        builder.setType(type);
        return builder.getArtifact();
    }
}
