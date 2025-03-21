package ft.swingy.GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogsPanelView extends JPanel {
    JTextArea logs;
    JScrollPane scroll;

    public LogsPanelView() {
        setLayout(new BorderLayout());
        logs = new JTextArea();
        scroll = new JScrollPane(logs);
        logs.setEditable(false);
        logs.setLineWrap(true);
        logs.setWrapStyleWord(true);
        logs.getCaret().setVisible(false);
        logs.setFont(getFont().deriveFont(10.0f));
        logs.setBackground(Color.GRAY);
        logs.setForeground(Color.WHITE);
        logs.setOpaque(true);
        logs.setFocusable(false);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setText(ArrayList<String> text) {
        logs.setText("");
        for (int i = 0; i < text.size(); i++) {
            logs.append(text.get(i) + "\n");
        }
        scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
        revalidate();
        repaint();
    }
}
