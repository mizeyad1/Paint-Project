/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject.model;

//import java.awt.Color;

import java.awt.Point;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author dell-pc
 */
public abstract class AbstractShape implements Shape {

     ArrayList<AbstractShape>arrayList=new ArrayList<>();
    public Point p;
    protected Color c;
    public Color fillc;
    protected Map<String, Double> myMap;
    public static Caretaker caretaker = new Caretaker();
    public static Originator originator = new Originator();
    public static int saveFiles = 0, currentArticle = 0;
    public static int counterr=0;
    public String type;

    public AbstractShape(Point p, Color c, Color fillc, String type) {
        this.p = p;
        this.c = c;
        this.fillc = fillc;
        this.type = type;

    }

    @Override
    public void setPosition(Point position) {
        p = position;
    }

    @Override
    public Point getPosition() {
        return p;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        myMap = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return myMap;
    }

    @Override
    public void setColor(Color color) {
        c = color;
    }

    @Override
    public Color getColor() {
        return c;
    }

    @Override
    public void setFillColor(Color color) {
        fillc = color;
    }

    @Override
    public Color getFillColor() {
        return fillc;
    }

    @Override
    public void draw(Canvas canvas) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public void clearScreen(Canvas canvas) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

}
