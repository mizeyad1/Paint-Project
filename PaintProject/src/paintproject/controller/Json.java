package paintproject.controller;

import com.sun.webkit.ColorChooser;
//import java.awt.Color;
import javafx.scene.paint.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.valueOf;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import paintproject.model.AbstractShape;

public class Json implements Strategy{

    public static ArrayList<AbstractShape> AS = new ArrayList<>();

    //Writing to json file
    @Override
    public void Write(File fileName)  throws ParserConfigurationException, SAXException, IOException {
       
        JSONObject ALLshape = new JSONObject();
     
        JSONObject shapeobj ;
        JSONArray arrayshapes = new JSONArray();
        for (int i = 0; i < AS.size(); i++) {
            shapeobj = new JSONObject();
            try {
                
                
 
            if (AS.get(i).type.equalsIgnoreCase("Rectangle")) {
                 

                shapeobj.put("Type", AS.get(i).type);
                shapeobj.put("px", AS.get(i).getPosition().getX());
                shapeobj.put("py", AS.get(i).getPosition().getY());
                shapeobj.put("color", AS.get(i).getColor());
                shapeobj.put("Fillcolor", AS.get(i).getFillColor());
                shapeobj.put("h", AS.get(i).getProperties().get("h"));
                shapeobj.put("w", AS.get(i).getProperties().get("w"));
                arrayshapes.put(shapeobj);
              
                
            }

            else if (AS.get(i).type.equalsIgnoreCase("Square")) {
                shapeobj.put("Type", AS.get(i).type);
                shapeobj.put("px", AS.get(i).getPosition().getX());
                shapeobj.put("py", AS.get(i).getPosition().getY());
                shapeobj.put("color", AS.get(i).getColor());
                shapeobj.put("Fillcolor", AS.get(i).getFillColor());
                shapeobj.put("s", AS.get(i).getProperties().get("s"));
                arrayshapes.put(shapeobj);
               

            }

            else if (AS.get(i).type.equalsIgnoreCase("Circle")) {
               
                shapeobj.put("Type", AS.get(i).type);
                shapeobj.put("px", AS.get(i).getPosition().getX());
                shapeobj.put("py", AS.get(i).getPosition().getY());
                shapeobj.put("color", AS.get(i).getColor());
                shapeobj.put("Fillcolor", AS.get(i).getFillColor());
                shapeobj.put("r", AS.get(i).getProperties().get("r"));
                arrayshapes.put(shapeobj);
               

            }

            else if (AS.get(i).type.equalsIgnoreCase("Ellipse")) {
                shapeobj.put("Type", AS.get(i).type);
                shapeobj.put("px", AS.get(i).getPosition().getX());
                shapeobj.put("py", AS.get(i).getPosition().getY());
                shapeobj.put("color", AS.get(i).getColor());
                shapeobj.put("Fillcolor", AS.get(i).getFillColor());
                shapeobj.put("r1", AS.get(i).getProperties().get("w"));
                shapeobj.put("r2", AS.get(i).getProperties().get("h"));
                arrayshapes.put(shapeobj);

            }

            else if (AS.get(i).type.equalsIgnoreCase("Line")) {
                
                shapeobj.put("Type", AS.get(i).type);
                
                shapeobj.put("px", AS.get(i).getPosition().getX());
                shapeobj.put("py", AS.get(i).getPosition().getY());
                shapeobj.put("color", AS.get(i).getColor());
                shapeobj.put("Fillcolor", AS.get(i).getFillColor());
                shapeobj.put("x", AS.get(i).getProperties().get("x"));
                shapeobj.put("y", AS.get(i).getProperties().get("y"));
               
                arrayshapes.put(shapeobj);

            }

            else if (AS.get(i).type.equalsIgnoreCase("Triangle")) {
                shapeobj.put("Type", AS.get(i).type);
                shapeobj.put("px", AS.get(i).getPosition().getX());
                shapeobj.put("py", AS.get(i).getPosition().getY());
                shapeobj.put("color", AS.get(i).getColor());
                shapeobj.put("Fillcolor", AS.get(i).getFillColor());
                shapeobj.put("w", AS.get(i).getProperties().get("w"));
                shapeobj.put("x2", AS.get(i).getProperties().get("x2"));
                shapeobj.put("y2", AS.get(i).getProperties().get("y2"));
                arrayshapes.put(shapeobj);

            }
              } catch (Exception e) {
            }
            
            
            

        }
        
        try {
            ALLshape.put("shapes", arrayshapes);
        } catch (JSONException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       

        try {

            // Writing to a file
            FileWriter file = new FileWriter(fileName);
            file.write(ALLshape.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Reading...  
    public void Read(File fileName, Canvas canvas) throws JSONException {

        try {
         
            GraphicsContext g = canvas.getGraphicsContext2D();
            Scanner scanner = new Scanner(fileName);
            String jsonString = scanner.useDelimiter("\\A").next();
            scanner.close();

            JSONObject shapeobj = new JSONObject();
            JSONObject jSONObject = new JSONObject(jsonString);
            JSONArray jSONArray = jSONObject.getJSONArray("shapes");
            for (int i = 0; i < jSONArray.length(); i++) {
                shapeobj = jSONArray.getJSONObject(i);
                int px = shapeobj.getInt("px");
                int py = shapeobj.getInt("py");             
               String type = (String) shapeobj.get("Type");      
               String Fillc=(String) shapeobj.get("Fillcolor");
               String c=(String) shapeobj.get("color");
                if (type.equalsIgnoreCase("Rectangle")) {
                    int w = shapeobj.getInt("w");
                    int h = shapeobj.getInt("h");
                     g.setStroke(Paint.valueOf(c));
                    if (Fillc.equals("0xffffffff")) {
                        
                        g.strokeRect(px, py, w, h);
                    } else {
                           g.setFill(Paint.valueOf(Fillc));
                        g.fillRect(px, py, w, h);
                    }

                }
                if (type.equalsIgnoreCase("square")) {

                    int s = shapeobj.getInt("s");           
                      g.setStroke(Paint.valueOf(c));
                    if (Fillc.equals("0xffffffff")) {
                        g.strokeRect(px,py, s, s);
                    } else {
                        g.setFill(Paint.valueOf(Fillc));
                        g.fillRect(px, py, s, s);
                    }

                }
                if (type.equalsIgnoreCase("line")) {
                    int x=shapeobj.getInt("x");
                    int y=shapeobj.getInt("y");
                    g.setStroke(Paint.valueOf(c));
                    g.strokeLine(px, py, x,y);

                }
                if (type.equalsIgnoreCase("circle")) {

                    int r = shapeobj.getInt("r");
                 g.setStroke(Paint.valueOf(c));
                    if (Fillc.equals("0xffffffff")) {
                        g.strokeOval(px, py, r,r);
                    } else {
                       g.setFill(Paint.valueOf(Fillc));
                        g.fillOval(px, py, r,r);
                    }

                }
                if (type.equalsIgnoreCase("ellipse")) {

                    int r1 = shapeobj.getInt("r1");
                    int r2 = shapeobj.getInt("r2");

                   
                    if (Fillc.equals("0xffffffff")) {
                        g.setStroke(Paint.valueOf(c));
                        g.strokeOval(px, py, r1,r2);
                    } else {
                        g.setFill(Paint.valueOf(Fillc));
                        g.fillOval(px, py, r1,r2);
                    }

                }
                if (type.equalsIgnoreCase("triangle")) {

                    int w=shapeobj.getInt("w");
                    int x2=shapeobj.getInt("x2");
                    int y2=shapeobj.getInt("y2");
                     g.setStroke(Paint.valueOf(c));
                    if (Fillc.equals("0xffffffff")) {
                        double[] Xcoord = {(int) px + Math.abs(w) / 2, (int) px, (int) x2};
                        double[] Ycoord = {(int) py, (int) y2, (int) y2};
                        g.strokePolygon(Xcoord, Ycoord, 3); //draws a regular triangle       
                    } else {
                       g.setFill(Paint.valueOf(Fillc));
                        double[] Xcoord = {(int) px + Math.abs(w) / 2, (int) px, (int) x2};
                        double[] Ycoord = {(int) py, (int) y2, (int) y2};
                        g.fillPolygon(Xcoord, Ycoord, 3); //draws a regular triangle       
                    }

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
