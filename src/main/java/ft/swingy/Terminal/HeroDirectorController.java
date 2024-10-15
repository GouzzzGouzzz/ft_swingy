package ft.swingy.Terminal;

import java.util.Scanner;
import java.util.Set;

import ft.swingy.Hero.Hero;
import ft.swingy.Hero.HeroBean;
import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirectorModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class HeroDirectorController {
    HeroDirectorView view;
    HeroDirectorModel model;
    Scanner read;

    HeroDirectorController(HeroDirectorView view, HeroDirectorModel model, Scanner read) {
        this.view = view;
        this.model = model;
        this.read = read;
    }

    public Hero createHero() {
        HeroBuilder builder = new HeroBuilder();
        HeroBean heroBean = new HeroBean();
        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<HeroBean>> violations;
        int id;
        String name;

        while (true) {
            name = view.askForHeroName(read);
            heroBean.setName(name);
            violations = validator.validate(heroBean);
            if (violations.isEmpty()) {
                break;
            }
            for (ConstraintViolation<HeroBean> violation : violations) {
                System.out.println(violation.getMessage());
            }
        }
        while (true) {
            try {
                id = view.askForHeroClass(read);
                heroBean.setType(id);
                violations = validator.validate(heroBean);
                if (violations.isEmpty()) {
                    break;
                }
                for (ConstraintViolation<HeroBean> violation : violations) {
                    System.out.println(violation.getMessage());
                }
            }
            catch (Exception e) {
                System.out.println("\nInvalid class, please enter a correct id");
            }
        }
        switch (heroBean.getType()) {
            case 1:
                return model.makeWarrior(builder, heroBean.getName());
            case 2:
                return model.makeRogue(builder, heroBean.getName());
            default:
                break;
        }
        return null;
    }
}
