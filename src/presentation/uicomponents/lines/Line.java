package presentation.uicomponents.lines;

public class Line {
    private int xStart, yStart, xEnd, yEnd;

    public Line(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }
    public Line(float a, float b, float c, float d) {
        this(Math.round(a), Math.round(b), Math.round(c), Math.round(d));
    }

    public int getxStart () {
        return xStart;
    }
    public int getyStart() {
        return yStart;
    }
    public int getxEnd() {
        return xEnd;
    }
    public int getyEnd() {
        return yEnd;
    }
}

