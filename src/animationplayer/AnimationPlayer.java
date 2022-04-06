package animationplayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static animationplayer.ShapeUtils.stringToInt;
import static javafx.application.Application.launch;

public class AnimationPlayer extends Application {

    int frames;
    int speed;
    int elements;
    Circle circle = new Circle();
    ArrayList<Effect> effects = new ArrayList<>();
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    void loadAnimationFromFile(String fileName) {

        BufferedReader reader;

        try {

            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {

                if (line.contains("frames")) {

                    frames = stringToInt(line);
                    line = reader.readLine();
                    speed = stringToInt(line);
                    line = reader.readLine();
                    elements = stringToInt(line);

                }
                
                else if (line.equals("Circle")) { 
                    
                    circle = CircleUtils.create(reader, line);
                    
                    while (!line.equals("")) {
                        
                        if (line.equals("effect")) { CircleUtils.determineEffect(reader, line, effects, circle, null, null); }
                        line = reader.readLine();
                    
                    }
                    
                }
                
                // else if (line.equals("Rect")) { rectangle = RectangleUtils.create(reader, line); }
                // else if (line.equals("Line")) { line = LineUtils.create(reader, line); }
                
                line = reader.readLine();

            }

            reader.close();

        } catch (IOException e) {

        }

        for (Effect effect : effects) {
            
            System.out.println(effect.effectType + " " + effect.circle + " " + effect.rectangle + " " + effect.line);
            
        }
        
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void start(Stage primaryStage) {

        loadAnimationFromFile("animation.txt");

        Pane pane = new Pane();
        pane.getChildren().add(circle);

        Scene scene = new Scene(pane, 800, 450, Color.WHITESMOKE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animation Player");

        primaryStage.show();

    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static void main(String args[]) {

        launch(args);

    }

}
