package ft.swingy.Hero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;

import ft.swingy.Game.LoaderBean;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class HeroDirector {

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
        BuilderBean bean = new BuilderBean();
        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<BuilderBean>> violations;

        builder.reset();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            while ((line = fileReader.readLine()) != null) {
                if (id == 0 && line.contains("Name:")){
                    for (int i = 0; i < 7; i++){
                        if (line.contains("Name:"))
                            bean.setName(line.substring(5));
                        if (line.contains("Type:"))
                            bean.setType(line.substring(5));
                        if (line.contains("Level:"))
                            bean.setLevel(Integer.parseInt(line.substring(6)));
                        if (line.contains("Experience:"))
                            bean.setExperience(Integer.parseInt(line.substring(11)));
                        if (line.contains("Attack:"))
                            bean.setAttack(Integer.parseInt(line.substring(7)));
                        if (line.contains("Defense:"))
                            bean.setDefense(Integer.parseInt(line.substring(8)));
                        if (line.contains("HP:"))
                            bean.setHP(Integer.parseInt(line.substring(3)));
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
        violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<BuilderBean> violation : violations) {
                System.out.println(violation.getMessage());
            }
            return null;
        }
        builder.setName(bean.getName());
        builder.setType(bean.getType());
        builder.setLevel(bean.getLevel());
        builder.setExperience(bean.getExperience());
        builder.setAttack(bean.getAttack());
        builder.setDefense(bean.getDefense());
        builder.setHP(bean.getHP());
        return builder.getHero();
    }

    static public Hero createNewHero(Scanner read){
        final String[] heroType = {"Warrior", "Rogue"};
        HeroDirector director = new HeroDirector();
        HeroBuilder builder = new HeroBuilder();
        HeroBean heroBean = new HeroBean();

        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<HeroBean>> violations;

        while (true){
            System.out.println("Enter your hero's name: ");
            heroBean.setName(read.nextLine());
            violations = validator.validate(heroBean);
            if (violations.isEmpty()) {
                break;
            }
            for (ConstraintViolation<HeroBean> violation : violations) {
                System.out.println(violation.getMessage());
            }
        }
        while (true) {
            System.out.println("Type you're hero id class to choose one: \n1 " + heroType[0] + "\n2 " + heroType[1]);
            try{
                heroBean.setType(Integer.parseInt(read.nextLine()));
                violations = validator.validate(heroBean);
                if (violations.isEmpty()) {
                    break;
                }
                for (ConstraintViolation<HeroBean> violation : violations) {
                    System.out.println(violation.getMessage());
                }
            }
            catch (Exception e){
                System.out.println("\nInvalid class, please enter a correct id");
                read.nextLine();
                continue;
            }
        }
        switch (heroBean.getType()) {
            case 1:
                return director.makeWarrior(builder, heroBean.getName());
            case 2:
                return director.makeRogue(builder, heroBean.getName());
            default:
                break;
        }
        return null;
    }
}
