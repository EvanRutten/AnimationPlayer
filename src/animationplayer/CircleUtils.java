package animationplayer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleUtils {
    
    static Circle create(int r, int x, int y, int border, Color color, Color borderColor) {
        
        Circle output = new Circle(x, y, r, color);
        output.setStrokeWidth(border);
        output.setStroke(borderColor);
        
        return output;
        
    }
    
}
