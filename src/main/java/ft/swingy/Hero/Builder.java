package ft.swingy.Hero;

import ft.swingy.Artifacts.Artifact;

public interface Builder {
    public void setHP(int hp);
    public void setAttack(int attack);
    public void setDefense(int defense);
    public void setLevel(int level);
    public void setExperience(int experience);
    public void setName(String name);
    public void setType(String type);
    public void setArtifact(int index, Artifact artifact);
}
