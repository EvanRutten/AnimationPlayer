package animationplayer;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;

public class Timer extends AnimationTimer {

    int currentFrame = 0;

    int frames;
    int speed;
    ArrayList<Effect> effects;
    
    // constructor (imports main data from file)
    public Timer(int frames, int speed, ArrayList<Effect> effects) {

        this.frames = frames;
        this.speed = speed;
        this.effects = effects;

    }

    @Override
    public void handle(long now) {
        
        // check all added effects
        for (Effect effect : effects) {
            
            // if a start frame is reached
            if (currentFrame == effect.start) {
                
                // check effect type and perform animation
                switch (effect.effectType) {

                    case "Hide":

                        effect.node.visibleProperty().set(false);
                        break;

                    case "Show":

                        effect.node.visibleProperty().set(true);
                        break;

                    case "Jump":

                        effect.node.translateXProperty().set(effect.x);
                        effect.node.translateYProperty().set(effect.y);
                        break;

                    case "ChangeColor":
                        
                        // hide original node
                        effect.node.visibleProperty().set(false);
                        
                        // create new shape from original node
                        Shape shape = (Shape) effect.node;
                        shape.setFill(effect.color);
                        shape.setStroke(effect.borderColor);
                        
                        // set original node to new shape
                        effect.node = shape;
                        effect.node.visibleProperty().set(true);
                        break;

                }

            }

        }
        
        // when animation is finished
        if (currentFrame > frames) {
            
            // stop timer and print result
            stop();
            System.out.println("ANIMATION COMPLETE\n");

        }

        try {
            
            // sleep one frame at a time
            Thread.sleep(1000 / speed);
            currentFrame++;
        
        // exception handling
        } catch (InterruptedException ex) {

            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
