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
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import paintproject.controller.Json;

/**
 *
 * @author amrak
 */
public class Square extends AbstractShape {

    public Point p = new Point();
    public Map<String, Double> propsq;
    public Color c;
    public Color fc;
    int gr, b, a, opacity;
    int gr2, b2, a2, opacity2;

    public Square(Point p, Color c, Color fillc) {
        super(p, c, fillc,"Square");
        propsq = new HashMap<>();
        propsq.put("s", 0.0);

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // return super.clone(); //To change body of generated methods, choose Tools | Templates.

        AbstractShape r = new Square(p, c, fillc);
        r.setColor(c);
        r.setFillColor(fc);
        r.setPosition(p);
        Map newprop = new HashMap<>();
        for (Map.Entry s : propsq.entrySet()) {
            newprop.put(s.getKey(), s.getValue());
        }
        r.setProperties(newprop);
        return r;
    }

    @Override
    public void draw(Canvas canvas) {
        // super.draw(canvas);
        GraphicsContext g = canvas.getGraphicsContext2D();
        javafx.scene.paint.Color  clr = getColor();

        javafx.scene.paint.Color  fclr = getFillColor();
        g.setStroke(getColor());
           g.setFill(getFillColor());
        

        
        if (p!=null){
            g.setFill(fc);
         g.setStroke(c);
         if(fc==WHITE)
            g.strokeRect((int) p.getX(), (int) p.getY(), (int) propsq.get("s").intValue(), (int) propsq.get("s").intValue());
        else
             g.fillRect((int) p.getX(), (int) p.getY(), (int) propsq.get("s").intValue(), (int) propsq.get("s").intValue());
        }

        canvas.setOnMousePressed(e -> {

            g.beginPath();
             propsq.put("s", 0.0);

            p.x = (int) e.getX();
            p.y = (int) e.getY();
        });

        canvas.setOnMouseDragged(e -> {

            //propsq.put("s", e.getX() - (int)p.getX());
            propsq.put("s", e.getY() - (int) p.getY());

            //g.clearRect((int) p.getX(), (int) p.getY(), (int) propsq.get("s").intValue(), (int) propsq.get("s").intValue());
            //g.strokeRect((int) p.getX(), (int) p.getY(), (int) propsq.get("s").intValue(), (int) propsq.get("s").intValue());

        });
        canvas.setOnMouseReleased(e -> {
             if (getFillColor()==WHITE) 
            g.strokeRect((int) p.getX(), (int) p.getY(), (int) propsq.get("s").intValue(), (int) propsq.get("s").intValue());
             else
                 g.fillRect((int) p.getX(), (int) p.getY(), (int) propsq.get("s").intValue(), (int) propsq.get("s").intValue());
            
            double xxy3=(int) propsq.get("s").intValue();
            double xxzz3=p.getX();
            double xxxzzz3=p.getY();
            Point T6=new Point();
            T6=p;
            Square squareto=new Square(T6,clr,fclr);
            squareto.p.x=(int) xxzz3;
            squareto.p.y=(int)xxxzzz3;
            squareto.c=getColor();
            squareto.fc=getFillColor();
            
            
            
          squareto.propsq.put("s",xxy3);
          squareto.setProperties(propsq);
           
            
                AbstractShape.originator.set(squareto);
           
            
            AbstractShape.caretaker.addMemento(AbstractShape.originator.storeInMemento());
            
            AbstractShape.saveFiles++;
            AbstractShape.currentArticle++;

         
              squareto.setColor(clr);
            squareto.setFillColor(fclr);
               Json.AS.add(squareto);

            
        });

    }
    public String toString()
    {
       return  p + " " + propsq + " " + c + " " + fillc;
    }
    public double getSide() {
        return (int) propsq.get("s").intValue();
    }

}
