package animationplayer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Effect {
    
    String effectType;
    
    int start;
    int x, y;
    Color color, borderColor;
    
    Circle circle;
    Rectangle rectangle;
    Line line;
    
    void chooseShape(Circle circle, Rectangle rectangle, Line line) {
        
        if (circle != null) { this.circle = circle; }
        else if (rectangle != null) { this.rectangle = rectangle; }
        else if (line != null) { this.line = line; }
        
    }
    
}
