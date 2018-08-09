package paintproject.model;

//import java.awt.Color;
import javafx.scene.paint.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import static javafx.scene.paint.Color.WHITE;
import javax.swing.*;
import paintproject.controller.*;

public class Circle extends AbstractShape {
//HII

    public Point pc = new Point();
    public Map<String, Double> propCircle;
    public Color c;
    public Color fc;
    int gr, b, a, opacity, r;
    int gr2, b2, a2, opacity2;

    public Circle(Point p, Color c, Color fillc) {

        super(p, c, fillc, "Circle");
        propCircle = new HashMap<>();
        propCircle.put("r", 0.0);

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //return super.clone(); //To change body of generated methods, choose Tools | Templates.

        AbstractShape r = new Circle(p, c, fillc);
        r.setColor(c);
        r.setFillColor(fc);
        r.setPosition(p);
        Map newprop = new HashMap<>();
        for (Map.Entry s : propCircle.entrySet()) {
            newprop.put(s.getKey(), s.getValue());
        }
        r.setProperties(newprop);
        return r;

    }

    public void draw(Canvas canvas) {

        GraphicsContext g = canvas.getGraphicsContext2D();
        javafx.scene.paint.Color clr = getColor();

        javafx.scene.paint.Color fclr = getFillColor();
        System.out.println(fclr);
        g.setStroke(getColor());
        g.setFill(getFillColor());
        Point pcc = new Point();
        pcc = pc;
        if (pcc != null) {
            g.setFill(fc);
            g.setStroke(c);
            if (fc == WHITE) {
                g.strokeOval(pcc.x, pcc.y, propCircle.get("r").intValue(), propCircle.get("r").intValue());
            } else {
                g.fillOval(pcc.x, pcc.y, propCircle.get("r").intValue(), propCircle.get("r").intValue());
            }
        }

        canvas.setOnMousePressed(e -> {

            g.beginPath();
            propCircle.put("r", 0.0);
            propCircle.put("r", 0.0);
            pc.x = (int) e.getX();
            pc.y = (int) e.getY();

            //g.strokeLine(5, 10, 60, 50);
        });

        canvas.setOnMouseDragged(e -> {

            propCircle.put("r", e.getX() - (int) pc.getX());
            propCircle.put("r", e.getY() - (int) pc.getY());


            //g.clearRect((int) pc.getX(), (int) pc.getY(), (int) propCircle.get("r").intValue(), (int) propCircle.get("r").intValue());
            //g.strokeOval((int) pc.getX(), (int) pc.getY(), (int) propCircle.get("r").intValue(), (int) propCircle.get("r").intValue());
           
            //g.strokeLine((int) p1.getX(), (int) p1.getY(), (int) propLine.get("x").intValue(), (int) propLine.get("y").intValue());
        });
        canvas.setOnMouseReleased(e -> {
            if (getFillColor() == WHITE) {
                g.strokeOval((int) pc.x, (int) pc.y, propCircle.get("r").intValue(), propCircle.get("r").intValue());
            } else {
                g.fillOval((int) pc.x, (int) pc.y, propCircle.get("r").intValue(), propCircle.get("r").intValue());
            }

            g.closePath();

            double abc = (int) propCircle.get("r").intValue();
            double def = (int) propCircle.get("r").intValue();
            double ghi = pc.x;
            double jkl = pc.y;
            Point T1 = new Point();
            T1 = pc;
            Circle Circleto = new Circle(T1, c, fc);
            Circleto.pc.x = (int) ghi;
            Circleto.pc.y = (int) jkl;
            Circleto.c = getColor();
            Circleto.fc = getFillColor();

            Circleto.propCircle.put("r", abc);
            Circleto.propCircle.put("r", def);
            Circleto.setProperties(propCircle);
            // System.out.println("Coloris"+Circleto.c+" fill color is"+Circleto.fc);
            AbstractShape.originator.set(Circleto);
            AbstractShape.caretaker.addMemento(AbstractShape.originator.storeInMemento());
            AbstractShape.saveFiles++;
            AbstractShape.currentArticle++;
            // System.out.println(this);
            Circleto.setColor(clr);
            Circleto.setFillColor(fclr);
            Json.AS.add(Circleto);
            //System.out.println(arrayList.get(0).type);

        });

    }

    public String toString() {
        return " " + pc + " " + propCircle + " " + c + " " + fc;
    }
    
    
    


}
