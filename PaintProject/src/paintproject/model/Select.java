package paintproject.model;

//import java.awt.Color;
import javafx.scene.paint.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Select extends AbstractShape {

protected Point psel = new Point();
protected Map<String, Double> proprecs;
protected Color c;
protected Color fillc;
int gr, b, a, opacity;
int gr2, b2, a2, opacity2;

public Select(Point p, Color c, Color fillc) {
super(p, c, fillc,"Select");
proprecs = new HashMap<>();
proprecs.put("w", 0.0);
proprecs.put("h", 0.0);
}

public void draw(Canvas canvas) {
GraphicsContext g = canvas.getGraphicsContext2D();

//java.awt.Color awtColor = getColor();
//int rr = awtColor.getRed();
//gr = awtColor.getGreen();
//b = awtColor.getBlue();
//a = awtColor.getAlpha();
//opacity = (int) (a / 255.0);
//javafx.scene.paint.Color zz = javafx.scene.paint.Color.rgb(rr, gr, b, opacity);
//
//java.awt.Color fillColor = getFillColor();
//int rr2 = fillColor.getRed();
//gr2 = fillColor.getGreen();
//b2 = fillColor.getBlue();
//a2 = fillColor.getAlpha();
//opacity2 = (int) (a2 / 255.0);
//javafx.scene.paint.Color z = javafx.scene.paint.Color.rgb(rr2, gr2, b2, opacity2);
g.setStroke(getColor());
g.setFill(getFillColor());

canvas.setOnMousePressed(e -> {
g.beginPath();

proprecs.put("w", 0.0);
proprecs.put("h", 0.0);
psel.x = Math.abs((int) e.getX());
psel.y = Math.abs((int) e.getY());
});

 

canvas.setOnMouseDragged(e -> {

proprecs.put("w", Math.abs(e.getX() - (int) psel.getX()));
proprecs.put("h", Math.abs(e.getY() - (int) psel.getY()));

});
canvas.setOnMouseReleased(e -> {

g.strokeRect(Math.abs((int) psel.getX()), Math.abs((int) psel.getY()), Math.abs((int) proprecs.get("w").intValue()), Math.abs((int) proprecs.get("h").intValue()));
   

g.closePath();

//System.out.println("x is" + p.x + "y is " + p.y);
});

}

 public double getX() {
        return (int) psel.x;
    }
      public double getY() {
        return (int) psel.y;
    }

}
