package animationplayer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineUtils {
    
    static Line create(int startX, int startY, int endX, int endY, int border, Color color) {
        
        Line output = new Line(startX, startY, endX, endY);
        output.setStrokeWidth(border);
        output.setStroke(color);
        
        return output;
        
    }
    
}
