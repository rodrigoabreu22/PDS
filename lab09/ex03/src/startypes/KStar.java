package lab09.ex03.src.startypes;
import java.awt.Color;

public class KStar extends StarType{
    
    public KStar() {
        super(1, new Color(230, 160, 10));
        this.description = "This is a long description of the K type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
