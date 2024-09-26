package lab09.ex03.src;
import java.util.HashMap;
import java.util.Map;
import ex03.src.starTypes.*;

public class FlyweightFactory {
    static int CANVAS_SIZE = 1200;
    private static Map<Character, StarType> flyweight = new HashMap<>();

    public static Star getStarType(char type) {
        StarType star = flyweight.get(type);
        
        int x = random(0, CANVAS_SIZE);
        int y = random(0, CANVAS_SIZE);

        if (star == null) {
            switch (type) {
                case 'O':
                    star = new OStar();
                    break;

                case 'A':
                    star = new AStar();
                    break;

                case 'B':
                    star = new BStar();
                    break;

                case 'F':
                    star = new FStar();
                    break;

                case 'G':
                    star = new GStar();
                    break;
                
                case 'K':
                    star = new KStar();
                    break;

                case 'M':
                    star = new MStar();
                    break;
            }
            flyweight.put(type, star);
        }
        return new Star(star, x, y);
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
