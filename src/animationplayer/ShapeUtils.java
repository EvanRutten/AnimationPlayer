package animationplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ShapeUtils {
    
    static int stringToInt(String string) {
        
        return Integer.parseInt(string.replaceAll("[^0-9]", ""));
        
    }
    
    static ArrayList<Effect> determineEffect(BufferedReader reader, String fileLine, ArrayList<Effect> effects, Circle circle, Rectangle rectangle, Line line) throws IOException {
        
        Effect effect = new Effect();
        effect.chooseShape(circle, rectangle, line);
        fileLine = reader.readLine();
        
        switch (fileLine) {
            
            case "Hide":
                
                effect.effectType = "Hide";
                fileLine = reader.readLine();
                effect.start = stringToInt(fileLine);
                effects.add(effect);
                break;
                
            case "Show":
                
                effect.effectType = "Show";
                fileLine = reader.readLine();
                effect.start = stringToInt(fileLine);
                effects.add(effect);
                break;
                
            case "Jump":
                
                effect.effectType = "Jump";
                fileLine = reader.readLine();
                effect.start = stringToInt(fileLine);
                fileLine = reader.readLine();
                effect.x = stringToInt(fileLine);
                fileLine = reader.readLine();
                effect.y = stringToInt(fileLine);
                effects.add(effect);
                break;
                
            case "ChangeColor":
                
                effect.effectType = "ChangeColor";
                fileLine = reader.readLine();
                effect.start = stringToInt(fileLine);
                fileLine = reader.readLine();
                
                String[] color = fileLine.split(",");
                int r1 = stringToInt(color[0]);
                int b1 = stringToInt(color[1]);
                int g1 = stringToInt(color[2]);
                effect.color = Color.rgb(r1, g1, b1);
                fileLine = reader.readLine();
                
                String[] borderColor = fileLine.split(",");
                int r2 = stringToInt(borderColor[0]);
                int b2 = stringToInt(borderColor[1]);
                int g2 = stringToInt(borderColor[2]);
                effect.borderColor = Color.rgb(r2, g2, b2);
                effects.add(effect);
                break;

        }
        
        return effects;
        
    }
    
}
