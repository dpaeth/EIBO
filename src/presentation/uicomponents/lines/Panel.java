package presentation.uicomponents.lines;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {

    private List<Line> lines = new ArrayList<Line>();

    public Panel(List lines) {
        this.lines = lines;
    }

    public void addLine(Line x) {
        lines.add(x);
    }

    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.WHITE);
        for(Line line : lines) {
            g.drawLine(line.getxStart(), line.getyStart(),line.getxEnd(),line.getyEnd());
        }
    }

    public List<Line> getLines() {
        return lines;
    }


}
