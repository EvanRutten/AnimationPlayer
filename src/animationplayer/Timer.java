package animationplayer;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;

public class Timer extends AnimationTimer {

    int currentFrame = 0;

    int frames;
    int speed;
    ArrayList<Effect> effects;

    public Timer(int frames, int speed, ArrayList<Effect> effects) {

        this.frames = frames;
        this.speed = speed;
        this.effects = effects;

    }

    @Override
    public void handle(long now) {

        for (Effect effect : effects) {

            if (currentFrame == effect.start * speed) {

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

                        break;

                }

            }

        }

        if (currentFrame == frames) {

            stop();
            System.out.println("ANIMATION COMPLETE\n");

        }

        try {

            Thread.sleep(1000 / speed);
            currentFrame++;

        } catch (InterruptedException ex) {

            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
