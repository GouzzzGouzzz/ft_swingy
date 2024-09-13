package ft.swingy.Hero;

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
    public Hero getHero() {
        return hero;
    }
}
