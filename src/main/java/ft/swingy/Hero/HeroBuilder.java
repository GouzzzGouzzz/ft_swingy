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
        hero.hitPoints = hp;
        hero.maxHitPoints = hp;
    }
    public void setAttack(int attack) {
        hero.attack = attack;
    }
    public void setDefense(int defense) {
        hero.defense = defense;
    }
    public void setLevel(int level) {
        hero.level = level;
    }
    public void setExperience(int experience) {
        hero.experience = experience;
    }
    public void setName(String name) {
        hero.name = name;
    }
    public void setType(String type) {
        hero.type = type;
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
