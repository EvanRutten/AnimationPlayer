package animationplayer;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineUtils extends ShapeUtils {
    
    // method to create line and read its data
    static Line create(BufferedReader reader, String line) throws IOException {

        Line lineShape = new Line();
        
        // add: startX + startY + endX + endY (guaranteed)
        line = reader.readLine();
        lineShape.setStartX(stringToInt(line));
        line = reader.readLine();
        lineShape.setStartY(stringToInt(line));
        line = reader.readLine();
        lineShape.setEndX(stringToInt(line));
        line = reader.readLine();
        lineShape.setEndY(stringToInt(line));
        
        // add: border + color (not guaranteed)
        lineShape = checkExtraFeatures(reader, line, lineShape);
        return lineShape;

    }
    
    // add non-guaranteed data to line
    static Line checkExtraFeatures(BufferedReader reader, String line, Line lineShape) throws IOException {

        line = reader.readLine();

        if (line.contains("border")) {

            lineShape.setStrokeWidth(stringToInt(line));
            
            // run method recursively
            checkExtraFeatures(reader, line, lineShape);

        } else if (line.contains("color")) {
            
            // split strings by commas
            String[] color = line.split(",");
            
            // add split strings to RGB values
            int r = stringToInt(color[0]);
            int g = stringToInt(color[1]);
            int b = stringToInt(color[2]);

            lineShape.setFill(Color.rgb(r, g, b));
            
            // run method recursively
            checkExtraFeatures(reader, line, lineShape);

        }

        return lineShape;

    }

}
