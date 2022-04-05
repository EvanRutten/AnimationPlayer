package animationplayer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleUtils {
    
    static Rectangle create(int length, int width, int x, int y, int border, Color color, Color borderColor) {
        
        Rectangle output = new Rectangle(x, y, length, width);
        output.setStrokeWidth(border);
        output.setFill(color);
        output.setStroke(borderColor);
        
        return output;
        
    }
    
}
