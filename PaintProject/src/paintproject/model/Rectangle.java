/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject.model;


import java.awt.MouseInfo;
import java.awt.Point;
import javafx.scene.paint.Color;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;

import static javafx.scene.paint.Color.color;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.omg.CORBA.Object;
import paintproject.controller.Json;
import paintproject.model.Circle;
import paintproject.model.Line;
import paintproject.model.Rectangle;

/**
 *
 * @author Ahmed Bahey
 */
public class Rectangle extends AbstractShape {

    public Point p = new Point();
    public Map<String, Double> proprec;
    public Color c;
    public Color fillc;
    int gr, b, a, opacity;
    int gr2, b2, a2, opacity2;
    
    //
    List<String> options = new ArrayList<String>();
    

    public Rectangle(Point p, Color c, Color fillc) {
        super(p, c, fillc, "Rectangle");
        proprec = new HashMap<>();
        proprec.put("w", 0.0);
        proprec.put("h", 0.0);

    }

    
    @Override
    public AbstractShape clone() throws CloneNotSupportedException {
        // return super.clone(); //To change body of generated methods, choose Tools | Templates.

        AbstractShape cl = new Rectangle(p, c, fillc);
        cl.setColor(getColor());
        cl.setFillColor(fillc);
        cl.setPosition(p);
        Map newprop = new HashMap<>();
        for (Map.Entry s : proprec.entrySet()) {
            newprop.put(s.getKey(), s.getValue());
        }
        cl.setProperties(newprop);
        return cl;
    }

    @Override
    public void draw(Canvas canvas) {

        
        GraphicsContext g = canvas.getGraphicsContext2D();
         javafx.scene.paint.Color  clr = getColor();

        javafx.scene.paint.Color  fclr = getFillColor();
        
        g.setStroke(getColor());
           g.setFill(getFillColor());
        
        if (p != null) {
         g.setFill(fillc);
         g.setStroke(c);
         if(fillc==WHITE)
           g.strokeRect(p.getX(), p.getY(), (double) proprec.get("w").intValue(), (double) proprec.get("h").intValue());
         else
             g.fillRect(p.getX(), p.getY(), (double) proprec.get("w").intValue(), (double) proprec.get("h").intValue());
        
        }

       
  

        canvas.setOnMousePressed(e -> {
            g.beginPath();

            proprec.put("w", 0.0);
            proprec.put("h", 0.0);
            p.x = Math.abs((int) e.getX());
            p.y = Math.abs((int) e.getY());
        });

        canvas.setOnMouseDragged(e -> {

            proprec.put("w", Math.abs(e.getX() - (int) p.getX()));
            proprec.put("h", Math.abs(e.getY() - (int) p.getY()));

        });
        canvas.setOnMouseReleased(e -> {
            if(getFillColor()==WHITE)
            g.strokeRect(Math.abs((int) p.getX()), Math.abs((int) p.getY()), Math.abs((int) proprec.get("w").intValue()), Math.abs((int) proprec.get("h").intValue()));
            else
                g.fillRect(Math.abs((int) p.getX()), Math.abs((int) p.getY()), Math.abs((int) proprec.get("w").intValue()), Math.abs((int) proprec.get("h").intValue()));
            double xxy = (int) proprec.get("w").intValue();
            double xxz = (int) proprec.get("h").intValue();
            double xxzz = p.getX();
            double xxxzzz = p.getY();
            Point T = new Point();
            T = p;
            Rectangle recto = new Rectangle(T, clr, fclr);
            recto.p.x = (int) xxzz;
            recto.p.y = (int) xxxzzz;
            recto.c=getColor();
            recto.fillc=getFillColor();

            
            recto.proprec.put("w", xxy);
            recto.proprec.put("h", xxz);
            recto.setProperties(proprec);
            
            
           
           
            
            AbstractShape.originator.set(recto);

            AbstractShape.caretaker.addMemento(AbstractShape.originator.storeInMemento());

            AbstractShape.saveFiles++;
            AbstractShape.currentArticle++;

            System.out.println();
            recto.setColor(clr);
            recto.setFillColor(fclr);
            Json.AS.add(recto);
            paintproject.controller.PaintController.comboColors1.getItems().addAll(Json.AS.get(0));  
            
        });
           
    }
    public void initializeee()
    {
        //options.add("Rectangle");
             
    }
    
    
    
    public double getWidth() {
        return (int) proprec.get("w").intValue();
    }

    public void setWidth(double width) {
        proprec.put("w", width);
    }

    public double getHeight() {
        return (int) proprec.get("h").intValue();
    }

    public void setHeight(double height) {
        proprec.put("h", height);
    }

    @Override
    public String toString() {//overriding the toString() method  
        return p + " " + proprec + " " + c + " " + fillc;
    }
    
  

}
