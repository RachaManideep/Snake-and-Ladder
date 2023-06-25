
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Main;



public class Tile extends Rectangle {
    public Tile(int x, int y){
        setWidth(Main.Size);
        setHeight(Main.Size);
        setFill(Color.RED);
        setStroke(Color.BLACK);
    }

}
