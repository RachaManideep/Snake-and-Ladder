package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile  extends Rectangle {
    public Tile(int x, int y){
        setWidth( Main.Size);
        setHeight(Main.Size);

        setFill(Color.PINK);
        setStroke(Color.BLACK);
    }


}
