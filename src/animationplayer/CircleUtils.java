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
            
            String[] color = line.split(",");
            
            int r = Integer.parseInt(color[0].replaceAll("[^0-9]", ""));
            int g = Integer.parseInt(color[1].replaceAll("[^0-9]", ""));
            int b = Integer.parseInt(color[2].replaceAll("[^0-9]", ""));
            
            circle.setFill(Color.rgb(r, g, b));
            checkExtraFeatures(reader, line, circle);
            
        } else if (line.contains("borderColor")) {
            
            String[] borderColor = line.split(",");
            
            int r = Integer.parseInt(borderColor[0].replaceAll("[^0-9]", ""));
            int g = Integer.parseInt(borderColor[1].replaceAll("[^0-9]", ""));
            int b = Integer.parseInt(borderColor[2].replaceAll("[^0-9]", ""));
            
            circle.setStroke(Color.rgb(r, g, b));
            checkExtraFeatures(reader, line, circle);
            
        }
        
        return circle;
        
    }

}
