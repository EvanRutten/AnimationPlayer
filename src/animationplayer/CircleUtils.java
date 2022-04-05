package animationplayer;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleUtils {

    static Circle create(BufferedReader reader, String line) throws IOException {

        Circle circle = new Circle();

        line = reader.readLine();
        circle.setRadius(Integer.parseInt(line.replaceAll("[^0-9]", "")));
        line = reader.readLine();
        circle.setCenterX(Integer.parseInt(line.replaceAll("[^0-9]", "")));
        line = reader.readLine();
        circle.setCenterY(Integer.parseInt(line.replaceAll("[^0-9]", "")));
        
        circle = checkExtraFeatures(reader, line, circle);
        return circle;

    }
    
    static Circle checkExtraFeatures(BufferedReader reader, String line, Circle circle) throws IOException {
        
        line = reader.readLine();
        
        if (line.contains("border")) {
            
            circle.setStrokeWidth(Integer.parseInt(line.replaceAll("[^0-9]", "")));
            checkExtraFeatures(reader, line, circle);
            
        } else if (line.contains("color")) {
            
            // add fill color here
            checkExtraFeatures(reader, line, circle);
            
        } else if (line.contains("borderColor")) {
            
            // add border color here
            checkExtraFeatures(reader, line, circle);
            
        }
        
        return circle;
        
    }

}
