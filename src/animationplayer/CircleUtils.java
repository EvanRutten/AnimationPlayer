package animationplayer;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleUtils extends ShapeUtils {
    
    // method to create circle and read its data
    static Circle create(BufferedReader reader, String line) throws IOException {

        Circle circle = new Circle();
        
        // add: radius + x + y (guaranteed)
        line = reader.readLine();
        circle.setRadius(stringToInt(line));
        line = reader.readLine();
        circle.setCenterX(stringToInt(line));
        line = reader.readLine();
        circle.setCenterY(stringToInt(line));
        
        // add: border + color + borderColor (not guaranteed)
        circle = checkExtraFeatures(reader, line, circle);
        return circle;

    }
    
    // method to add non-guaranteed data to circle
    static Circle checkExtraFeatures(BufferedReader reader, String line, Circle circle) throws IOException {

        line = reader.readLine();
        
        if (line.contains("border")) {

            circle.setStrokeWidth(stringToInt(line));
            
            // run method recursively
            checkExtraFeatures(reader, line, circle);

        } else if (line.contains("color")) {
            
            // split string by commas
            String[] color = line.split(",");
            
            // add split strings to RGB values
            int r = stringToInt(color[0]);
            int g = stringToInt(color[1]);
            int b = stringToInt(color[2]);

            circle.setFill(Color.rgb(r, g, b));
            
            // run method recursively
            checkExtraFeatures(reader, line, circle);

        } else if (line.contains("borderColor")) {
            
            // split strings by commas
            String[] borderColor = line.split(",");
            
            // add split strings to RGB values
            int r = stringToInt(borderColor[0]);
            int g = stringToInt(borderColor[1]);
            int b = stringToInt(borderColor[2]);

            circle.setStroke(Color.rgb(r, g, b));
            
            // run method recursively
            checkExtraFeatures(reader, line, circle);

        }

        return circle;

    }

}
