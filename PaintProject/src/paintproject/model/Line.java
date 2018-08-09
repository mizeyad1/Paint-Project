/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.*;
import paintproject.controller.Json;
/**
 *
 * @author Ahmed Bahey
 */
public class Line extends AbstractShape{
    
      public Point p1 = new Point();

    public Map<String, Double> propLine;
    public Color c;
    public Color fc;
    int gr, b, a, opacity;
   
    
     public Line(Point p, Color c, Color fillc) {
        super(p, c, fillc,"Line");
         propLine = new HashMap<>();
        propLine.put("x", 0.0);
        propLine.put("y", 0.0);
    }

    
   

    @Override
    public Object clone() throws CloneNotSupportedException {
        //return super.clone(); //To change body of generated methods, choose Tools | Templates.
        
              AbstractShape r = new Line(p, c, fillc);
        r.setColor(c);
        r.setFillColor(fc);
        r.setPosition(p);
        Map newprop = new HashMap<>();
        for (Map.Entry s: propLine.entrySet())
            newprop.put(s.getKey(), s.getValue());
        r.setProperties(newprop);
        return r;
    }

    
    public void draw(Canvas canvas) {
        //super.draw(canvas); //To change body of generated methods, choose Tools | Templates.
        
        GraphicsContext g = canvas.getGraphicsContext2D();
         javafx.scene.paint.Color  clr = getColor();

        javafx.scene.paint.Color  fclr = getFillColor();
        
        g.setStroke(getColor());
           g.setFill(getFillColor());
        if (p1!=null){
            g.setFill(fc);
         g.setStroke(c);
             g.strokeLine((int) p1.getX(), (int) p1.getY(), (int) propLine.get("x").intValue(), (int) propLine.get("y").intValue());
        }
       

       
        canvas.setOnMousePressed(e -> {

            g.beginPath();
            p1.x = (int) e.getX();
            p1.y = (int) e.getY();
           
              //g.strokeLine(5, 10, 60, 50);
        });

        canvas.setOnMouseDragged(e -> {

            
            propLine.put("x", e.getX()); 
            propLine.put("y", e.getY());
        // g.clearRect((int)p1.getX(),(int) p1.getY(), (int)propLine.get("x").intValue(), (int)propLine.get("y").intValue());

            //g.strokeLine((int) p1.getX(), (int) p1.getY(), (int) propLine.get("x").intValue(), (int) propLine.get("y").intValue());
            
 
           

        });
        canvas.setOnMouseReleased(e -> {

            g.strokeLine((int) p1.getX(), (int) p1.getY(), (int) propLine.get("x").intValue(), (int) propLine.get("y").intValue());
            g.closePath();
            double xxy1=(int) propLine.get("x").intValue();
            double xxz1=(int) propLine.get("y").intValue();
            double xxzz1=p1.getX();
            double xxxzzz1=p1.getY();
            Point T3=new Point();
            T3=p1;
            Line Lineto=new Line(T3,clr,fclr);
            Lineto.setPosition(T3);
            Lineto.p1.x=(int) xxzz1;
            Lineto.p1.y=(int)xxxzzz1;
           Lineto.c=(getColor());
            Lineto.fc=(getFillColor());
           Lineto.propLine.put("x",xxy1);
           Lineto.propLine.put("y",xxz1);
           Lineto.setProperties(propLine);
            
            
                AbstractShape.originator.set(Lineto);
           
            
            AbstractShape.caretaker.addMemento(AbstractShape.originator.storeInMemento());
            
            AbstractShape.saveFiles++;
            AbstractShape.currentArticle++;

            //System.out.println(this);
 
  Lineto.setColor(clr);
            Lineto.setFillColor(fclr);
          Json.AS.add(Lineto);
            //   System.out.println("x is"+p.x+"y is "+p.y);
        });

        
        
        
    }
      @Override
    public String toString()
    {
        return p1 + " " + propLine + " " + c + " " + fillc;
    }
    
}
