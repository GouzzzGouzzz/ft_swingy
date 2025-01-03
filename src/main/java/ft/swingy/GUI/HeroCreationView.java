package ft.swingy.GUI;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ft.swingy.Hero.Hero;
import ft.swingy.Hero.HeroBean;
import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirectorModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class HeroCreationView {
    private final JTextField heroName;
    private final JPanel buttonPanel;
    private final JPanel namePanel;
    private final JButton rogue;
    private final JButton warrior;
    private final JButton confirm;
    private final GameGUIController root;
    private int classId;
    private final CustomLabelView selectedClass;

    public HeroCreationView(GameGUIController root){
        this.root = root;
        selectedClass = new CustomLabelView("No Class Selected");
        classId = 1;
        heroName = new JTextField();
        confirm = new JButton("Create");
        rogue = new JButton("Rogue");
        warrior = new JButton("Warrior");
        //panel for the class selection
        buttonPanel = new JPanel();
        buttonPanel.add(rogue);
        buttonPanel.add(warrior);
        //panel for the name input
        namePanel = new JPanel();
        namePanel.add(heroName);
        namePanel.add(confirm);
        namePanel.add(selectedClass);
        heroName.setPreferredSize(new Dimension(100, 30));
        setupListener();
    }

    private void setupListener(){
        rogue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                HeroDirectorModel model = new HeroDirectorModel();
                Hero defaultHero = model.makeRogue(new HeroBuilder(), heroName.getText());
                root.getStatsSelectPanel().setStats(defaultHero, 0);
            }
        });
        warrior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                HeroDirectorModel model = new HeroDirectorModel();
                Hero defaultHero = model.makeWarrior(new HeroBuilder(), heroName.getText());
                root.getStatsSelectPanel().setStats(defaultHero, 0);
            }
        });
        rogue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classId = 1;
                selectedClass.setText("Rogue");
            }
        });
        warrior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classId = 2;
                selectedClass.setText("Warrior");
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HeroDirectorModel model = new HeroDirectorModel();
                Hero hero = null;
                //
                HeroBuilder builder = new HeroBuilder();
                HeroBean heroBean = new HeroBean();
                Validator validator;
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                validator = factory.getValidator();
                Set<ConstraintViolation<HeroBean>> violations;
                //
                heroBean.setType(classId);
                heroBean.setName(heroName.getText());
                violations = validator.validate(heroBean);
                if (!violations.isEmpty() || model.nameAlreadyTaken(heroName.getText())) {
                    root.getPopUp().invalidHero();
                    return ;
                }
                switch (heroBean.getType()) {
                    case 1:
                        hero = model.makeWarrior(builder, heroBean.getName());
                    case 2:
                        hero = model.makeRogue(builder, heroBean.getName());
                    default:
                        break;
                }
                if (hero != null){
                    root.setHero(hero);
                    root.removeHeroCreationMenu();
                    root.startGame();
                }
            }
        });
    }

    public void removeHeroCreationMenu(){
        root.remove(root.getStatsSelectPanel());
        root.remove(getButtonPanel());
        root.remove(getNamePanel());
        root.revalidate();
        root.repaint();
    }

    public JPanel getButtonPanel(){
        return buttonPanel;
    }

    public JPanel getNamePanel(){
        return namePanel;
    }
}
