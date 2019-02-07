package presentation.uicomponents.lines;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Fenster extends JFrame implements ActionListener {
    JPanel panel;
    JPanel panel2;
    Panel linez;
    List<Line> lines;

    public Fenster() {
        setSize(800,600);
        panel = new JPanel();
        panel2 = new JPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
