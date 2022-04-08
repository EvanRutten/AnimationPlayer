package animationplayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static animationplayer.ShapeUtils.stringToInt;
import static javafx.application.Application.launch;

/**
 * @author Evan Rutten
 */

public class AnimationPlayer extends Application {

    int frames;
    int speed;
    int elements;

    int nodeCounter = -1;
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Effect> effects = new ArrayList<>();

    void loadAnimationFromFile(String fileName) {

        BufferedReader reader;

        try {
            
            // create reader and start receiving lines
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                
                // obtain initial information
                if (line.contains("frames")) {

                    frames = stringToInt(line);
                    line = reader.readLine();
                    speed = stringToInt(line);
                    line = reader.readLine();
                    elements = stringToInt(line);
                
                // check for and add circle
                } else if (line.equals("Circle")) {

                    nodes.add(CircleUtils.create(reader, line));
                    nodeCounter++;
                    
                    // set node to hide by default
                    effects.add(new Effect("Hide", nodes.get(nodeCounter), 0));
                    
                    // check for and add effects
                    while (!line.equals("")) {

                        if (line.equals("effect")) { effects.add(ShapeUtils.determineEffect(reader, line, nodes.get(nodeCounter))); }
                        line = reader.readLine();

                    }
                
                // check for and add rectangle
                } else if (line.equals("Rect")) {

                    nodes.add(RectangleUtils.create(reader, line));
                    nodeCounter++;
                    
                    // set node to hide by default
                    effects.add(new Effect("Hide", nodes.get(nodeCounter), 0));
                    
                    // check for and add effects
                    while (!line.equals("")) {

                        if (line.equals("effect")) { effects.add(ShapeUtils.determineEffect(reader, line, nodes.get(nodeCounter))); }
                        line = reader.readLine();

                    }
                
                // check for and add line
                } else if (line.equals("Line")) {

                    nodes.add(LineUtils.create(reader, line));
                    nodeCounter++;
                    
                    // set node to hide by default
                    effects.add(new Effect("Hide", nodes.get(nodeCounter), 0));
                    
                    // check for and add effects
                    while (!line.equals("")) {

                        if (line.equals("effect")) { effects.add(ShapeUtils.determineEffect(reader, line, nodes.get(nodeCounter))); }
                        line = reader.readLine();

                    }

                }

                line = reader.readLine();

            }
            
        // exception handling
        } catch (IOException e) {

        }

    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        
        // load node and effect data
        loadAnimationFromFile("animation.txt");
        
        // fill root group with nodes
        Group root = new Group();
        for (Node node : nodes) { root.getChildren().add(node); }
        
        // create text for when animation is finished
        Text text = new Text(265, 290, "ANIMATION COMPLETE");
        root.getChildren().add(text);
        
        // hide text at start, show text at end
        effects.add(new Effect("Hide", text, 0));
        effects.add(new Effect("Show", text, frames));
        
        // create and start animation timer
        AnimationTimer timer = new Timer(frames, speed, effects);
        timer.start();
        
        // set scene properties and add to stage
        Scene scene = new Scene(root, 400, 300, Color.WHITESMOKE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animation Player");
        
        // show stage
        primaryStage.show();

    }

    public static void main(String args[]) {
        
        // run javaFX program
        launch(args);

    }

}

//  NOTE: ANIMATION FILE MUST HAVE TWO EMPTY LINES AT THE BOTTOM

/*  NOTE: ANIMATION FILE MUST BE MODIFIED ACCORDINGLY

    --- GOOD -------
    
    Circle
    r: 10
    x: 50
    y: 50
    color: 255, 0, 0
                        <--- space between parameters and effects
    effect
    Show
    start: 10
    effect
    Hide
    start: 120
    
    ----------------

    --- BAD --------

    Circle
    r: 10
    x: 50
    y: 50
    color: 255, 0, 0
    effect
    Show
    start: 10
    effect
    Hide
    start: 120

    ----------------

*/
