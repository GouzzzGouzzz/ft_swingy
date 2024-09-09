package ft.swingy.Artifacts;

import ft.swingy.Artifacts.Artifact;
import ft.swingy.Artifacts.Builder;

public class ArtifactBuilder implements Builder {
    private Artifact artifact;

    public ArtifactBuilder() {
        this.reset();
    }

    public void reset() {
        this.artifact = new Artifact();
    }

    public void setQuality(int quality) {
        this.artifact.quality = quality;
    }

    public void setType(String type) {
        this.artifact.type = type;
    }

    public void setTier(int tier) {
        this.artifact.tier = tier;
    }

    public Artifact getArtifact() {
        return this.artifact;
    }

}
