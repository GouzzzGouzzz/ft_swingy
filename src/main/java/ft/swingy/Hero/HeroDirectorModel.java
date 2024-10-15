package ft.swingy.Hero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;

import ft.swingy.Artifacts.ArtifactBean;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class HeroDirectorModel {

    public Hero makeWarrior(HeroBuilder builder, String name) {
        builder.reset();
        builder.setType("Warrior");
        builder.setLevel(1);
        builder.setExperience(0);
        builder.setAttack(30);
        builder.setDefense(25);
        builder.setHP(100);
        builder.setName(name);
        return builder.getHero();
    }

    public Hero makeRogue(HeroBuilder builder, String name) {
        builder.reset();
        builder.setType("Rogue");
        builder.setLevel(1);
        builder.setExperience(0);
        builder.setAttack(75);
        builder.setDefense(15);
        builder.setHP(50);
        builder.setName(name);
        return builder.getHero();
    }

    static public Hero loadFromFile(HeroBuilder builder, int id) {
        File file = new File("src/main/java/ft/swingy/save/saves.txt");
        String line;
        BuilderBean builderB = new BuilderBean();
        ArtifactBean artifactB = new ArtifactBean();
        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<BuilderBean>> violationsBuilder;
        Set<ConstraintViolation<ArtifactBean>> violationsArtifact;
        builder.reset();

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            while ((line = fileReader.readLine()) != null) {
                if (id == 0 && line.contains("Name:")){
                    for (int i = 0; i < 10; i++){
                        if (line.contains("Name:"))
                            builderB.setName(line.substring(5));
                        if (line.contains("Type:"))
                            builderB.setType(line.substring(5));
                        if (line.contains("Level:"))
                            builderB.setLevel(Integer.parseInt(line.substring(6)));
                        if (line.contains("Experience:"))
                            builderB.setExperience(Integer.parseInt(line.substring(11)));
                        if (line.contains("Attack:"))
                            builderB.setAttack(Integer.parseInt(line.substring(7)));
                        if (line.contains("Defense:"))
                            builderB.setDefense(Integer.parseInt(line.substring(8)));
                        if (line.contains("HP:"))
                            builderB.setHP(Integer.parseInt(line.substring(3)));
                        if (line.contains("Weapon:"))
                            artifactB.setWeapon(Integer.parseInt(line.substring(7)));
                        if (line.contains("Armor:"))
                            artifactB.setArmor(Integer.parseInt(line.substring(6)));
                        if (line.contains("Helm:"))
                            artifactB.setHelm(Integer.parseInt(line.substring(5)));
                        line = fileReader.readLine();
                    }
                    break;
                }
                if (line.contains("Name:")){
                    id--;
                }
            }
            fileReader.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        violationsBuilder = validator.validate(builderB);
        if (!violationsBuilder.isEmpty()) {
            for (ConstraintViolation<BuilderBean> violation : violationsBuilder) {
                System.out.println(violation.getMessage());
            }
            return null;
        }
        violationsArtifact = validator.validate(artifactB);
        if (!violationsArtifact.isEmpty()) {
            for (ConstraintViolation<ArtifactBean> violation : violationsArtifact) {
                System.out.println(violation.getMessage());
            }
            return null;
        }
        builder.setName(builderB.getName());
        builder.setType(builderB.getType());
        builder.setLevel(builderB.getLevel());
        builder.setExperience(builderB.getExperience());
        builder.setAttack(builderB.getAttack());
        builder.setDefense(builderB.getDefense());
        builder.setHP(builderB.getHP());
        builder.setWeapon(artifactB.getWeapon());
        builder.setArmor(artifactB.getArmor());
        builder.setHelm(artifactB.getHelm());
        return builder.getHero();
    }
}
