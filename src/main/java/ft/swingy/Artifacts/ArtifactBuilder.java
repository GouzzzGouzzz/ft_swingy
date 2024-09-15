package ft.swingy.Artifacts;

public class ArtifactBuilder implements Builder {
    private Artifact artifact;

    public ArtifactBuilder() {
        this.reset();
    }

    public void reset() {
        artifact = new Artifact();
    }

    public void setQuality(int quality) {
        artifact.quality = quality;
    }

    public void setType(String type) {
        artifact.type = type;
    }

    public void setTier(int tier) {
        artifact.tier = tier;
    }

    public Artifact getArtifact() {
        return artifact;
    }

}
