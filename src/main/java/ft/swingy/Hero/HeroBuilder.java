package ft.swingy.Hero;

import ft.swingy.Artifacts.Artifact;
import ft.swingy.Artifacts.ArtifactBuilder;
import ft.swingy.Artifacts.ArtifactDirector;

public class HeroBuilder implements Builder {
    private Hero hero;

    public HeroBuilder(){
        this.reset();
    }

    public void reset() {
        hero = new Hero();
    }

    public void setHP(int hp) {
        hero.setHitPoints(hp);
        hero.setMaxHitPoints(hp);
    }
    public void setAttack(int attack) {
        hero.setAttack(attack);
    }
    public void setDefense(int defense) {
        hero.setDefense(defense);
    }
    public void setLevel(int level) {
        hero.setLevel(level);
    }
    public void setExperience(int experience) {
        hero.setExperience(experience);;
    }
    public void setName(String name) {
        hero.setName(name);
    }
    public void setType(String type) {
        hero.setType(type);
    }
    public void setArtifact(int index, Artifact artifact) {
        hero.artifacts[index] = artifact;
    }
    public Hero getHero() {
        return hero;
    }

    public void setWeapon(int quality) {
        ArtifactDirector director = new ArtifactDirector();
        ArtifactBuilder builder = new ArtifactBuilder();
        hero.artifacts[0] = director.buildWithQuality(builder ,"Weapon", quality);
    }

    public void setArmor(int quality) {
        ArtifactDirector director = new ArtifactDirector();
        ArtifactBuilder builder = new ArtifactBuilder();
        hero.artifacts[1] = director.buildWithQuality(builder ,"Armor", quality);
    }

    public void setHelm(int quality) {
        ArtifactDirector director = new ArtifactDirector();
        ArtifactBuilder builder = new ArtifactBuilder();
        hero.artifacts[2] = director.buildWithQuality(builder ,"Helm", quality);
    }
}
