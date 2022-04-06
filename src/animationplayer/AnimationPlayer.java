package animationplayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import static animationplayer.ShapeUtils.stringToInt;
import static javafx.application.Application.launch;

public class AnimationPlayer extends Application {

    int frames;
    int speed;
    int elements;
    
    int nodeCounter = -1;
    ArrayList<Node> nodes = new ArrayList<>();
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
                    
                    nodes.add(CircleUtils.create(reader, line));
                    nodeCounter++;
                    
                    while (!line.equals("")) {
                        
                        if (line.equals("effect")) { CircleUtils.determineEffect(reader, line, effects, (Circle) nodes.get(nodeCounter), null, null); }
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
    public void start(Stage primaryStage) throws InterruptedException {

        loadAnimationFromFile("animation.txt");
        
        int seconds = frames / speed;
        
        Group root = new Group();
        for (Node node : nodes) { root.getChildren().add(node); }
        
        Scene scene = new Scene(root, 800, 450, Color.WHITESMOKE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animation Player");
        
        Timeline timeline = new Timeline();
        
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO,
                new KeyValue(nodes.get(0).translateXProperty(), 100)),
            new KeyFrame(new Duration(5000),
                new KeyValue(nodes.get(0).translateXProperty(), 500)));
        
        primaryStage.show();
        timeline.play();
        timeline.setOnFinished(event -> primaryStage.close());

    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static void main(String args[]) {

        launch(args);

    }

}
