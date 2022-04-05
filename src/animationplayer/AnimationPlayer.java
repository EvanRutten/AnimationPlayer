package animationplayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class AnimationPlayer extends Application {

    int frames;
    int speed;
    int elements;
    Circle circle = new Circle();

    void loadAnimationFromFile(String fileName) {

        BufferedReader reader;

        try {

            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {

                if (line.contains("frames")) {

                    frames = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                    line = reader.readLine();
                    speed = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                    line = reader.readLine();
                    elements = Integer.parseInt(line);

                }
                
                if (line.equals("Circle")) { circle = CircleUtils.create(reader, line); }
                // check for "Rect"
                // check for "Line"
                
                line = reader.readLine();

            }

            reader.close();

        } catch (IOException e) {

        }
        
    }

    @Override
    public void start(Stage primaryStage) {

        loadAnimationFromFile("animation.txt");

        // Circle circle = CircleUtils.create(100, 200, 200, 5, Color.RED, Color.DARKRED);
        Rectangle rectangle = RectangleUtils.create(300, 100, 600, 200, 5, Color.BLUE, Color.DARKBLUE);
        Line line = LineUtils.create(200, 400, 800, 800, 5, Color.GREEN);

        Pane pane = new Pane();
        pane.getChildren().add(circle);
        pane.getChildren().add(rectangle);
        pane.getChildren().add(line);

        Scene scene = new Scene(pane, 1600, 900, Color.WHITESMOKE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animation Player");

        primaryStage.show();

    }

    public static void main(String args[]) {

        launch(args);

    }

}
