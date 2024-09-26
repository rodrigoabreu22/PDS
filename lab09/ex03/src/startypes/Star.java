package lab09.ex03.src.startypes;

import java.awt.*;

public class Star {
    private int x;
    private int y;
    private StarType starType;

    public Star(StarType starType, int x, int y) {
        this.starType = starType;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(starType.getColor());
        g.fillOval(x, y , starType.getSize(), starType.getSize());
    } 
}
