/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject.model;

//import java.awt.Color;
import javafx.scene.paint.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import static javafx.scene.paint.Color.WHITE;
import paintproject.controller.Json;

/**
 *
 * @author Ahmed Bahey
 */
public class Ellipse extends AbstractShape {

    public Point pell = new Point();
    public Map<String, Double> propell;
    public Color c;
    public Color fc;
    int gr, b, a, opacity;
    int gr2, b2, a2, opacity2;

    public Ellipse(Point p, Color c, Color fillc) {
        super(p, c, fillc, "Ellipse");
        propell = new HashMap<>();
        propell.put("w", 0.0);
        propell.put("h", 0.0);

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // return super.clone(); //To change body of generated methods, choose Tools | Templates.

        AbstractShape r = new Ellipse(p, c, fillc);
        r.setColor(c);
        r.setFillColor(fc);
        r.setPosition(p);
        Map newprop = new HashMap<>();
        for (Map.Entry s : propell.entrySet()) {
            newprop.put(s.getKey(), s.getValue());
        }
        r.setProperties(newprop);
        return r;
    }

    public void draw(Canvas canvas) {
        // super.draw(canvas);

        GraphicsContext g = canvas.getGraphicsContext2D();
        javafx.scene.paint.Color  clr = getColor();

        javafx.scene.paint.Color  fclr = getFillColor();
         g.setStroke(getColor());
           g.setFill(getFillColor());
        if (pell != null) {
            g.setFill(fc);
            g.setStroke(c);
            if (fc==WHITE)
            g.strokeOval((int) pell.getX(), (int) pell.getY(), (int) propell.get("w").intValue(), (int) propell.get("h").intValue());
            else
                g.fillOval((int) pell.getX(), (int) pell.getY(), (int) propell.get("w").intValue(), (int) propell.get("h").intValue());
        }

        g.setStroke(getColor());
        g.setFill(getFillColor());

        canvas.setOnMousePressed(e -> {
            g.beginPath();
            propell.put("w", 0.0);
            propell.put("h", 0.0);
            pell.x = (int) e.getX();
            pell.y = (int) e.getY();
        });

        canvas.setOnMouseDragged(e -> {

            propell.put("w", e.getX() - (int) pell.getX());
            propell.put("h", e.getY() - (int) pell.getY());

            g.clearRect((int)pell.getX(),(int) pell.getY(), (int)propell.get("w").intValue(), (int)propell.get("h").intValue());
            g.strokeOval((int)pell.getX(),(int) pell.getY(), (int)propell.get("w").intValue(), (int)propell.get("h").intValue());
        });
        canvas.setOnMouseReleased(e -> {
            if (getFillColor()==WHITE)
            g.strokeOval((int) pell.getX(), (int) pell.getY(), (int) propell.get("w").intValue(), (int) propell.get("h").intValue());
            else
                g.fillOval((int) pell.getX(), (int) pell.getY(), (int) propell.get("w").intValue(), (int) propell.get("h").intValue());
            g.closePath();
            //   System.out.println("x is"+p.x+"y is "+p.y);
            double abcd = (int) propell.get("w").intValue();
            double defg = (int) propell.get("h").intValue();
            double ghij = pell.getX();
            double jklm = pell.getY();
            Point T2 = new Point();
            T2 = pell;
            Ellipse Ellipseto = new Ellipse(T2, c, fc);
            Ellipseto.pell.x = (int) ghij;
            Ellipseto.pell.y = (int) jklm;
Ellipseto.c=getColor();
Ellipseto.fc=getFillColor();
            Ellipseto.propell.put("w", abcd);
            Ellipseto.propell.put("h", defg);
            Ellipseto.setProperties(propell);
            AbstractShape.originator.set(Ellipseto);
            AbstractShape.caretaker.addMemento(AbstractShape.originator.storeInMemento());
            AbstractShape.saveFiles++;
            AbstractShape.currentArticle++;
           // System.out.println(this);
            Ellipseto.setColor(clr);
            Ellipseto.setFillColor(fclr);
            Json.AS.add(Ellipseto);
        });

    }

    @Override
    public String toString() {
        return pell + " " + propell + " " + c + " " + fillc;
    }

}
