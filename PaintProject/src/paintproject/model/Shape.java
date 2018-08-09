
package paintproject.model;

import javafx.scene.canvas.Canvas;


public interface Shape    {
    public void setPosition(java.awt.Point position);
  public java.awt.Point getPosition();
/* update shape specific properties (e.g., radius) */
public void setProperties(java.util.Map<String, Double> properties);
public java.util.Map<String, Double> getProperties();
public void setColor(javafx.scene.paint.Color color);
public javafx.scene.paint.Color getColor();
public void setFillColor(javafx.scene.paint.Color color);
public javafx.scene.paint.Color getFillColor();
/* redraw the shape on the canvas,
 for swing, you will cast canvas to java.awt.Graphics */
//public void draw(Object canvas);

public void draw(Canvas canvas);
public Object clone() throws CloneNotSupportedException; 
}
