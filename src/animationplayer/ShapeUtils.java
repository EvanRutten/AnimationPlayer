package animationplayer;

import java.io.BufferedReader;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class ShapeUtils {
    
    // method to convert string to integer
    static int stringToInt(String string) {
        
        // replace all non-numerical characters with empty space and parse remaining integers
        return Integer.parseInt(string.replaceAll("[^0-9]", ""));

    }
    
    // method to determine effect and read its data
    static Effect determineEffect(BufferedReader reader, String line, Node node) throws IOException {
        
        // assign effect to inputted node
        Effect effect = new Effect(node);
        line = reader.readLine();
        
        switch (line) {
            
            // hide (only needs: effectType + start)
            case "Hide":

                effect.effectType = "Hide";
                line = reader.readLine();
                effect.start = stringToInt(line);
                break;
            
            // show (only needs: effectType + start)
            case "Show":

                effect.effectType = "Show";
                line = reader.readLine();
                effect.start = stringToInt(line);
                break;
            
            // jump (needs: effectType + start + x + y)
            case "Jump":

                effect.effectType = "Jump";
                line = reader.readLine();
                effect.start = stringToInt(line);
                line = reader.readLine();
                effect.x = stringToInt(line);
                line = reader.readLine();
                effect.y = stringToInt(line);
                break;
            
            // change color (needs: effectType + start + color + borderColor)
            case "ChangeColor":

                effect.effectType = "ChangeColor";
                line = reader.readLine();
                effect.start = stringToInt(line);
                line = reader.readLine();
                
                // split line by commas and add split strings to RGB values
                String[] color = line.split(",");
                int r1 = stringToInt(color[0]);
                int b1 = stringToInt(color[1]);
                int g1 = stringToInt(color[2]);
                effect.color = Color.rgb(r1, b1, g1);
                line = reader.readLine();

                if (line.contains("borderColor")) {
                    
                    // split line by commas and add split strings to RGB values
                    String[] borderColor = line.split(",");
                    int r2 = stringToInt(borderColor[0]);
                    int b2 = stringToInt(borderColor[1]);
                    int g2 = stringToInt(borderColor[2]);
                    effect.borderColor = Color.rgb(r2, b2, g2);

                }

                break;

        }

        return effect;

    }

}
