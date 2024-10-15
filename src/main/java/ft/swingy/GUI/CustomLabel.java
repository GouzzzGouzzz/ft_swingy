package ft.swingy.GUI;

import javax.swing.JLabel;

public class CustomLabel extends JLabel {
    public CustomLabel(String text) {
        super(text);
        setFont(getFont().deriveFont(10.0f));
    }

}
