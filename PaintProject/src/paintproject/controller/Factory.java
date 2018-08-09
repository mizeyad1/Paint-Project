/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject.controller;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.TOMATO;
import paintproject.model.AbstractShape;
import paintproject.model.Circle;
import paintproject.model.Ellipse;
import paintproject.model.Line;
import paintproject.model.Rectangle;
import paintproject.model.Square;
import paintproject.model.Triangle;
//import java.awt.Color;
import paintproject.model.Select;

/**
 *
 * @author amrak
 */
public class Factory {
    
    public AbstractShape chooseshape(String Shape){

        AbstractShape A=null;
        if(Shape.equalsIgnoreCase("RECTANGLE")){
          A= new Rectangle(null,null,null); 
        }
        
        if(Shape.equalsIgnoreCase("SQUARE")){
         A=new Square(null,null,null);
        }if(Shape.equalsIgnoreCase("LINE")){
         A= new Line(null,null,null);
        }
        
        if(Shape.equalsIgnoreCase("ELLIPSE")){
      A=new Ellipse(null,null,null);
        }
        
        if(Shape.equalsIgnoreCase("TRIANGLE")){
    A=new Triangle(null,null,null);
        }
        
        if(Shape.equalsIgnoreCase("CIRCLE")){
      A= new Circle(null,null,null);
        }
         if(Shape.equalsIgnoreCase("SELECT")){
      A= new Select(null,null,null);
       }
  
        return A;
        
    }
    
    
}
