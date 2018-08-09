
package paintproject.model;
import javafx.scene.paint.Color;
//import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import static javafx.scene.paint.Color.WHITE;
import paintproject.controller.Json;

public class Triangle extends AbstractShape {

 public Point p1 = new Point();
  public Point p2 = new Point();

    public Map<String, Double> propt;
    public Color c;
    public Color fc;
    int gr,b,a,opacity;
    int gr2, b2, a2, opacity2;
    public Triangle(Point p, Color c, Color fillc) {
        super(p, c, fillc,"Triangle");
        propt = new HashMap<>();
        propt.put("w", 0.0);
        propt.put("x2", 0.0);
        propt.put("y2", 0.0);
    }

  

     

    @Override
    public Object clone() throws CloneNotSupportedException {
        //return super.clone(); //To change body of generated methods, choose Tools | Templates.
        
            AbstractShape r = new Triangle(p, c, fillc);
        r.setColor(c);
        r.setFillColor(fc);
        r.setPosition(p);
        Map newprop = new HashMap<>();
        for (Map.Entry s: propt.entrySet())
            newprop.put(s.getKey(), s.getValue());
        r.setProperties(newprop);
        return r;
    }

       public void draw( Canvas canvas )
    {
       double[] xCOORD={ (int)p1.getX()+Math.abs((int)propt.get("w").intValue())/2 ,(int)p1.getX() ,(int)p2.getX() };
       double[] yCOORD = { (int)p1.getY(),(int) p2.getY(),(int) p2.getY()};
      GraphicsContext g = canvas.getGraphicsContext2D();
      javafx.scene.paint.Color  clr = getColor();

        javafx.scene.paint.Color  fclr = getFillColor();
         g.setStroke(getColor());
           g.setFill(getFillColor());
      if (p1!=null && p2!=null){
          g.setFill(fillc);
          g.setStroke(c);
          if(fillc==WHITE)
          g.strokePolygon(xCOORD, yCOORD, 3);
          else
              g.fillPolygon(xCOORD, yCOORD,3);
      }
      // g.setStroke(getColor());
      //  g.setFill(getFillColor());

        

 
  canvas.setOnMousePressed(e -> {
                
                g.beginPath();
               propt.put("w", 0.0);
               p1.x= (int) e.getX();
                p1.y = (int) e.getY();
            });
      canvas.setOnMouseDragged(e -> {
                
                      propt.put("w", e.getX() - (int)p1.getX());


           
            });
       canvas.setOnMouseReleased(e -> {
       p2.x= (int) e.getX();
                p2.y = (int) e.getY();
         double[] Xcoord = { (int)p1.getX()+Math.abs((int)propt.get("w").intValue())/2 ,(int)p1.getX() ,(int)p2.getX() };
        double[] Ycoord = { (int)p1.getY(),(int) p2.getY(),(int) p2.getY()};
        propt.put("x2",p2.getX());
         propt.put("y2",p2.getY());
          g.setStroke(g.getStroke()); //sets the color
          fc=getFillColor();
          if (fc==WHITE)
          g.strokePolygon(Xcoord, Ycoord, 3); //draws a regular triangle   
          else
              g.fillPolygon(Xcoord,Ycoord,3);
        g.closePath();
        
        
        double mapw=(int) propt.get("w").intValue();
            
            double ponex=p1.getX();
            double poney=p1.getY();
            double ptwox=p2.getX();
            double ptwoy=p2.getY();
            Point T=new Point();
            
            Triangle triangleto=new Triangle(T,c,fc);
            triangleto.p1.x=(int) ponex;
            triangleto.p1.y=(int) poney;
            triangleto.p2.x=(int)ptwox;
            triangleto.p2.y=(int)ptwoy;
            
            triangleto.c=getColor();
            triangleto.fc=getFillColor();
           triangleto.propt.put("w",mapw);
           triangleto.propt.put("x2",ptwox);
           triangleto.propt.put("y2",ptwoy);
           triangleto.setPosition(p1);
           triangleto.setProperties(propt);
           
            
                AbstractShape.originator.set(triangleto);
           
            
            AbstractShape.caretaker.addMemento(AbstractShape.originator.storeInMemento());
            
            AbstractShape.saveFiles++;
            AbstractShape.currentArticle++;

            triangleto.setColor(clr);
            triangleto.setFillColor(fclr);
               Json.AS.add(triangleto);
        
        
        });
    }
       
       public Point getPoint1(){
           return p1;
       }
       
       public Point getPoint2(){
           
           return p2;
       }
               
   
  
}