package animationplayer;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Effect {

    String effectType;
    Node node;

    int start;
    int x, y;
    Color color, borderColor;
    
    // normal constructor (used when reading from file)
    public Effect(Node node) {

        this.node = node;

    }
    
    // custom constructor (used when not reading from file)
    public Effect(String effectType, Node node, int start) {

        this.effectType = effectType;
        this.node = node;
        this.start = start;

    }

}
