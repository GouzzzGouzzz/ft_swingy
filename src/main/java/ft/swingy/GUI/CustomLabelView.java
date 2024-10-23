package ft.swingy.GUI;

import javax.swing.JLabel;

public class CustomLabelView extends JLabel {
    public CustomLabelView(String text) {
        super(text);
        setFont(getFont().deriveFont(10.0f));
    }

}
