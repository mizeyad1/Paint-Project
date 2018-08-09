package paintproject.controller;

import paintproject.model.Caretaker;
import paintproject.model.Originator;
import com.sun.java.accessibility.util.AWTEventMonitor;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;
import java.awt.AWTEventMulticaster;
import javafx.scene.paint.Color;
import static java.awt.Color.red;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;

import static javafx.scene.paint.Color.color;
import paintproject.model.Circle;
import paintproject.model.Line;
import paintproject.model.Rectangle;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONException;
import org.xml.sax.SAXException;
import paintproject.model.AbstractShape;
import static paintproject.model.AbstractShape.caretaker;
import static paintproject.model.AbstractShape.counterr;
import static paintproject.model.AbstractShape.currentArticle;
import static paintproject.model.AbstractShape.originator;
import static paintproject.model.AbstractShape.saveFiles;
import paintproject.model.Ellipse;
import paintproject.model.Select;
import paintproject.model.Square;
import paintproject.model.Triangle;
import paintproject.view.DrawingEngine;
//import jfxtras.labs.util.event.MouseControlUtil;

public class PaintController implements DrawingEngine {

    @FXML
    private Button undobtn;
    @FXML
    private Button redobtn;
    double x, y, Tx, Ty;
    @FXML
    private Canvas canvas;

    @FXML
    public ColorPicker colorPicker;
    @FXML
    public ColorPicker fillcolorPicker;

    @FXML
    private TextField brushSize;

    @FXML
    private CheckBox eraser;
    @FXML
    private Button Rec;

    @FXML
    private ComboBox comboBox;

    @FXML
    private ComboBox comboColors;

    private Stage stage;

    @FXML
    public static ComboBox comboColors1 = new ComboBox();

    public void initialize() {

    }

    public void freedraw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX();
            double y = e.getY();
            if (eraser.isSelected()) {
                g.clearRect(x, y, size, size);
            } else {
                g.setFill(colorPicker.getValue());
                System.out.println(colorPicker.getValue());
                g.fillRect(x, y, size, size);
            }
        });
    }

    public void save() throws JSONException, ParserConfigurationException, SAXException, IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Json j = new Json();
            j.Write(file);
        }
    }

    public void save2() throws JSONException, ParserConfigurationException, SAXException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XML x = new XML();
            x.Write(file);
        }

    }

    public void Load() throws JSONException, ParserConfigurationException, SAXException, IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Json j = new Json();
            j.Read(file, canvas);
        }

    }

    public void Load2() throws JSONException, ParserConfigurationException, SAXException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XML x = new XML();
            x.Read(file, canvas);
        }

    }

    public void drawR() {
        try {

            Factory f = new Factory();
            AbstractShape R;
            R = f.chooseshape("RECTANGLE");
            // Color c=(Color)colorPicker.getValue();
            R.setColor(colorPicker.getValue());
            R.setFillColor(fillcolorPicker.getValue());
            R.draw(canvas);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void drawS() {

        try {
            Factory f = new Factory();
            AbstractShape R;
            R = f.chooseshape("SQUARE");
            R.setColor(colorPicker.getValue());
            R.setFillColor(fillcolorPicker.getValue());
            R.draw(canvas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawL() {

        try {
            Factory f = new Factory();
            AbstractShape R;
            R = f.chooseshape("LINE");
            R.setColor(colorPicker.getValue());
            R.setFillColor(fillcolorPicker.getValue());
            R.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawC() {

        try {
            Factory f = new Factory();
            AbstractShape R;
            R = f.chooseshape("CIRCLE");
            R.setColor(colorPicker.getValue());
            R.setFillColor(fillcolorPicker.getValue());
            R.draw(canvas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawEll() {

        try {
            Factory f = new Factory();
            AbstractShape R;
            R = f.chooseshape("ELLIPSE");
            R.setColor(colorPicker.getValue());
            R.setFillColor(fillcolorPicker.getValue());
            R.draw(canvas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Select() {
        try {

            Factory f = new Factory();
            AbstractShape R;
            R = f.chooseshape("SELECT");
            R.setColor(colorPicker.getValue());
            R.setFillColor(fillcolorPicker.getValue());
            R.draw(canvas);
            Point p = new Point();
            p = R.getPosition();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void drawT() {
        try {

            Factory f = new Factory();
            AbstractShape R;
            R = f.chooseshape("TRIANGLE");
            R.setColor(colorPicker.getValue());
            R.setFillColor(fillcolorPicker.getValue());
            R.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Resize() {
        String str = Json.AS.get(Json.AS.size() - 1).type;
        int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
        int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();

        GraphicsContext g = canvas.getGraphicsContext2D();
        Color c = Json.AS.get(Json.AS.size() - 1).getColor();
        Color fc = Json.AS.get(Json.AS.size() - 1).getFillColor();
        if (str.equalsIgnoreCase("Rectangle")) {
            int w = Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue();
            int h = Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
                g.strokeRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
            });
            canvas.setOnMouseReleased(e -> {
                //g.clearRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
                if (fc == WHITE) {
                    g.strokeRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
                } else {
                    g.fillRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
                }
                Point rectolpoint = new Point();
                Rectangle rectol = new Rectangle(rectolpoint, originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor(), originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor());
                rectol.p.x = x;
                rectol.p.y = y;
                rectol.proprec.put("w", e.getX() - x);
                rectol.proprec.put("h", e.getY() - y);
                rectol.c = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor();
                rectol.fillc = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor();

                AbstractShape.originator.set(rectol);

                AbstractShape.caretaker.appendMemento(AbstractShape.originator.storeInMemento());

            });
        } else if (str.equalsIgnoreCase("Square")) {
            int s = Json.AS.get(Json.AS.size() - 1).getProperties().get("s").intValue();
            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
                g.strokeRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
            });
            canvas.setOnMouseReleased(e -> {
                if (fc == WHITE) {
                    g.strokeRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
                } else {
                    g.fillRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
                }
                Point squaretol1 = new Point();
                Square squaretol = new Square(squaretol1, originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor(), originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor());
                squaretol.p.x = x;
                squaretol.p.y = y;
                squaretol.propsq.put("s", e.getX() - x);

                squaretol.c = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor();
                squaretol.fc = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor();

                AbstractShape.originator.set(squaretol);

                AbstractShape.caretaker.appendMemento(AbstractShape.originator.storeInMemento());
            });
        } else if (str.equalsIgnoreCase("Line")) {

            int x1 = Json.AS.get(Json.AS.size() - 1).getProperties().get("x").intValue();
            int y1 = Json.AS.get(Json.AS.size() - 1).getProperties().get("y").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX(), e.getY());
                // g.strokeLine(x, y,x1,y1);
            });
            canvas.setOnMouseReleased(e -> {
                g.clearRect(x, y, Math.abs(e.getX() + (int) x), Math.abs(e.getY() + (int) y));
                g.strokeLine(x, y, Math.abs(e.getX()), Math.abs(e.getY()));
                Point linetol1 = new Point();
                Line linetol = new Line(linetol1, originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor(), originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor());
                linetol.p1.x = x;
                linetol.p1.y = y;
                linetol.propLine.put("x", Math.abs(e.getX()));
                linetol.propLine.put("y", e.getY());

                linetol.c = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor();
                linetol.fillc = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor();

                AbstractShape.originator.set(linetol);

                AbstractShape.caretaker.appendMemento(AbstractShape.originator.storeInMemento());

            });
        } else if (str.equalsIgnoreCase("Circle")) {

            int r = Json.AS.get(Json.AS.size() - 1).getProperties().get("r").intValue();
            Map<String, Double> proprec22;
            proprec22 = new HashMap<>();
            proprec22.put("r", 0.0);

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {

                proprec22.put("r", Math.abs(e.getX() - x));
                g.clearRect((int) x, (int) y, (int) proprec22.get("r").intValue(), (int) proprec22.get("r").intValue());
                g.strokeOval((int) x, (int) y, (int) proprec22.get("r").intValue(), (int) proprec22.get("r").intValue());

            });
            canvas.setOnMouseReleased(e -> {
                if (fc == WHITE) {
                    g.strokeOval(x, y, proprec22.get("r").intValue(), proprec22.get("r").intValue());
                } else {
                    g.fillOval(x, y, proprec22.get("r").intValue(), proprec22.get("r").intValue());
                }
                double i1 = proprec22.get("r").intValue();
                Point circletol1 = new Point();
                Circle circletol = new Circle(circletol1, originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor(), originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor());
                circletol.pc.x = x;
                circletol.pc.y = y;
                circletol.propCircle.put("r", i1);

                circletol.c = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor();
                circletol.fc = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor();

                AbstractShape.originator.set(circletol);

                AbstractShape.caretaker.appendMemento(AbstractShape.originator.storeInMemento());
            });
        } else if (str.equalsIgnoreCase("Ellipse")) {

            int r1 = Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue();
            int r2 = Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue();
            Map<String, Double> proprec22;
            proprec22 = new HashMap<>();
            proprec22.put("w", 0.0);
            proprec22.put("h", 0.0);

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {

                proprec22.put("w", Math.abs(e.getX() - x));
                proprec22.put("h", Math.abs(e.getY() - y));
                g.clearRect((int) x, (int) y, (int) proprec22.get("w").intValue(), (int) proprec22.get("h").intValue());
                g.strokeOval((int) x, (int) y, (int) proprec22.get("w").intValue(), (int) proprec22.get("h").intValue());

            });
            canvas.setOnMouseReleased(e -> {
                if (fc == WHITE) {
                    g.strokeOval(x, y, proprec22.get("w").intValue(), proprec22.get("h").intValue());
                } else {
                    g.fillOval(x, y, proprec22.get("w").intValue(), proprec22.get("h").intValue());
                }
                Point ellipsetol1 = new Point();
                Ellipse ellipsetol = new Ellipse(ellipsetol1, originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor(), originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor());
                ellipsetol.pell.x = x;
                ellipsetol.pell.y = y;
                ellipsetol.propell.put("w", (double) proprec22.get("w").intValue());
                ellipsetol.propell.put("h", (double) proprec22.get("h").intValue());

                ellipsetol.c = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor();
                ellipsetol.fc = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor();

                AbstractShape.originator.set(ellipsetol);

                AbstractShape.caretaker.appendMemento(AbstractShape.originator.storeInMemento());
            });
        } else if (str.equalsIgnoreCase("Triangle")) {

            int w = Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue();
            Map<String, Double> proprec22;
            proprec22 = new HashMap<>();
            proprec22.put("w", 0.0);
            canvas.setOnMousePressed(e -> {
                g.beginPath();
                proprec22.put("w", 0.0);
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                proprec22.put("w", x - e.getX());
            });
            canvas.setOnMouseReleased(e -> {
                proprec22.put("w", Math.abs(e.getX() - x));
                proprec22.put("h", Math.abs(e.getY() - y));
                g.clearRect((int) x, (int) y, (int) proprec22.get("w").intValue(), (int) proprec22.get("h").intValue());

                double[] Xcoord = {(int) x + Math.abs((int) proprec22.get("w").intValue()) / 2, x, (int) e.getX()};
                double[] Ycoord = {(int) y, e.getY(), e.getY()};
                if (originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor() == WHITE) {
                    g.strokePolygon(Xcoord, Ycoord, 3); //draws a regular triangle
                } else {
                    g.fillPolygon(Xcoord, Ycoord, 3);
                }
                g.closePath();
                Point triangletol1 = new Point();
                Triangle triangletol = new Triangle(triangletol1, originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor(), originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor());
                triangletol.p1.x = x;
                triangletol.p1.y = y;
                triangletol.propt.put("w", (double) proprec22.get("w").intValue());
                triangletol.p2.y = (int) e.getY();
                triangletol.p2.x = (int) e.getX();

                triangletol.c = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getColor();
                triangletol.fc = originator.restoreFromMemento(caretaker.getMemento(saveFiles - 1)).getFillColor();

                AbstractShape.originator.set(triangletol);

                AbstractShape.caretaker.appendMemento(AbstractShape.originator.storeInMemento());

            });

        }
    }

    public void Drag() {

        String str = Json.AS.get(Json.AS.size() - 1).type;
        GraphicsContext g = canvas.getGraphicsContext2D();
        Color c = Json.AS.get(Json.AS.size() - 1).getColor();
        Color fc = Json.AS.get(Json.AS.size() - 1).getFillColor();
        if (str.equalsIgnoreCase("Rectangle")) {

            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();
            int w = Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue();
            int h = Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX(), e.getY());
                g.strokeRect(x, y, Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue(), Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue());
            });
            canvas.setOnMouseReleased(e -> {
                g.clearRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
                if (fc == WHITE) {
                    g.strokeRect(e.getX(), e.getY(), Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue(), Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue());
                } else {
                    g.fillRect(e.getX(), e.getY(), Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue(), Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue());
                }

            });
        } else if (str.equalsIgnoreCase("Square")) {
            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();

            int s = Json.AS.get(Json.AS.size() - 1).getProperties().get("s").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX(), e.getY());
                g.strokeRect(x, y, s, s);
            });
            canvas.setOnMouseReleased(e -> {
                g.clearRect(x, y, Math.abs(e.getX() + (int) x), Math.abs(e.getY() + (int) y));
                if (fc == WHITE) {
                    g.strokeRect(Math.abs(e.getX()), Math.abs(e.getY()), s, s);
                } else {
                    g.fillRect(Math.abs(e.getX()), Math.abs(e.getY()), s, s);
                }

            });
        } else if (str.equalsIgnoreCase("Line")) {
            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();
            int x1 = Json.AS.get(Json.AS.size() - 1).getProperties().get("x").intValue();
            int y1 = Json.AS.get(Json.AS.size() - 1).getProperties().get("y").intValue();
            int differencex = x1 - x;
            int differencey = y1 - y;

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX(), e.getY());
                // g.strokeLine(x, y,x1,y1);
            });
            canvas.setOnMouseReleased(e -> {
                //      g.clearRect(x1, y1, Math.abs(e.getX() + (int) x1), Math.abs(e.getY() + (int) y1));
                g.strokeLine(e.getX() - differencex, e.getY() - differencey, e.getX(), e.getY());
                //g.strokeLine(x1,y1,x+x,y+y);
            });
        } else if (str.equalsIgnoreCase("Circle")) {
            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();
            //int w = Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue();
            int r = Json.AS.get(Json.AS.size() - 1).getProperties().get("r").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, r, r);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX(), e.getY());
                //   g.fillOval(Math.abs(e.getX()), Math.abs(e.getY()) ,r,r);
                g.clearRect(x, y, e.getX(), e.getY());
            });
            canvas.setOnMouseReleased(e -> {
                g.clearRect(x, y, Math.abs(e.getX() + (int) x), Math.abs(e.getY() + (int) y));
                if (Json.AS.get(Json.AS.size() - 1).getFillColor() == WHITE) {
                    g.strokeOval(Math.abs(e.getX()), Math.abs(e.getY()), r, r);
                } else {
                    g.fillOval(Math.abs(e.getX()), Math.abs(e.getY()), r, r);
                }
            });
        } else if (str.equalsIgnoreCase("Ellipse")) {

            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();
            int w = Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue();
            int h = Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX(), e.getY());
                g.fillOval(x, y, w, h);
            });
            canvas.setOnMouseReleased(e -> {
                g.clearRect(x, y, w, h);

                if (Json.AS.get(Json.AS.size() - 1).getFillColor() == WHITE) {
                    g.strokeOval(e.getX(), e.getY(), w, h);
                } else {
                    g.fillOval(e.getX(), e.getY(), w, h);
                }

            });
        } else if (str.equalsIgnoreCase("Triangle")) {

            Triangle triangle = (Triangle) Json.AS.get(Json.AS.size() - 1);
            int w = triangle.getProperties().get("w").intValue();
            int x1 = triangle.getPoint1().x;
            int y1 = triangle.getPoint1().y;
            int x2 = triangle.getPoint2().x;
            int y2 = triangle.getPoint2().y;

            Map<String, Double> proprec22;
            proprec22 = new HashMap<>();
            proprec22.put("w", 0.0);
            canvas.setOnMousePressed(e -> {
                g.beginPath();
                proprec22.put("w", 0.0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX(), e.getY());
            });
            canvas.setOnMouseReleased(e -> {
                double[] Xcoord = {Math.abs((x1 + w / 2) - x1), Math.abs(x1 - x2), (int) x, e.getX()};
                double[] Ycoord = {e.getY(), Math.abs(y2 - y1), e.getY()};
                if (Json.AS.get(Json.AS.size() - 1).getFillColor() == WHITE) {
                    g.strokePolygon(Xcoord, Ycoord, 3); //draws a regular triangle
                } else {
                    g.fillPolygon(Xcoord, Ycoord, 3);
                }
                g.closePath();
            });
        }
    }

    public void cleansheet() {
        GraphicsContext gg = canvas.getGraphicsContext2D();
        gg.clearRect(0, 0, 2000, 2000);
    }

    @Override
    public void refresh(Object canvas) {
        AbstractShape res;
        for (int i = 0; i < currentArticle; i++) {
            res = originator.restoreFromMemento(caretaker.getMemento(i));
            res.setColor(res.getColor());
            res.setFillColor(res.getFillColor());
            res.draw((Canvas) canvas);
        }
    }

    @Override
    public void addShape(paintproject.model.AbstractShape shape) {
        shape.draw(canvas);
    }

    @Override
    public void updateShape(paintproject.model.Shape oldShape, paintproject.model.Shape newShape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public paintproject.model.AbstractShape[] getShapes() {
        AbstractShape res;
        AbstractShape[] myshapes = new AbstractShape[saveFiles];
        for (int i = 0; i < saveFiles; i++) {
            res = originator.restoreFromMemento(caretaker.getMemento(i));
            myshapes[i] = res;
        }
        return myshapes;
    }

    @Override
    public void undo() {
        AbstractShape res;
        counterr++;
        if (counterr > 20) {
            JOptionPane.showMessageDialog(null, "Limited to 20 steps");
            counterr = 0;
            return;
        }

        currentArticle--;
        if (currentArticle < 0) {
            JOptionPane.showMessageDialog(null, "You should draw a shape first before undo ");
            currentArticle = 0;
        }
        res = originator.restoreFromMemento(caretaker.getMemento(0));
        res.clearScreen(canvas);
        refresh(canvas);
    }

    @Override
    public void redo() {
        AbstractShape ress;
        counterr = 0;
        ress = originator.restoreFromMemento(caretaker.getMemento(currentArticle));
        ress.setColor(ress.getColor());
        ress.setFillColor(ress.getFillColor());
        addShape(ress);
        //ress.draw(canvas);
        currentArticle++;

    }

    public void copy() {
        String str = Json.AS.get(Json.AS.size() - 1).type;
        GraphicsContext g = canvas.getGraphicsContext2D();
        Color c = Json.AS.get(Json.AS.size() - 1).getColor();
        Color fc = Json.AS.get(Json.AS.size() - 1).getFillColor();
        if (str.equalsIgnoreCase("Rectangle")) {
            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();
            int w = Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue();
            int h = Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
            });
            canvas.setOnMouseDragged(e -> {
                //  g.clearRect(x, y, e.getX(), e.getY());
                //g.strokeRect(x, y, Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue(), Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue());
            });
            canvas.setOnMouseReleased(e -> {
                g.clearRect(x, y, e.getX() - (int) x, e.getY() - (int) y);
                if (fc == WHITE) {
                    g.strokeRect(e.getX(), e.getY(), Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue(), Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue());
                } else {
                    g.fillRect(e.getX(), e.getY(), Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue(), Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue());
                }

            });
        } else if (str.equalsIgnoreCase("Square")) {
            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();

            int s = Json.AS.get(Json.AS.size() - 1).getProperties().get("s").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                //  g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                //   g.clearRect(x, y, e.getX(), e.getY());
                g.strokeRect(x, y, s, s);
            });
            canvas.setOnMouseReleased(e -> {
                g.clearRect(x, y, Math.abs(e.getX() + (int) x), Math.abs(e.getY() + (int) y));
                if (fc == WHITE) {
                    g.strokeRect(Math.abs(e.getX()), Math.abs(e.getY()), s, s);
                } else {
                    g.fillRect(Math.abs(e.getX()), Math.abs(e.getY()), s, s);
                }

            });
        } else if (str.equalsIgnoreCase("Line")) {
            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();
            int x1 = Json.AS.get(Json.AS.size() - 1).getProperties().get("x").intValue();
            int y1 = Json.AS.get(Json.AS.size() - 1).getProperties().get("y").intValue();
            int differencex = x1 - x;
            int differencey = y1 - y;

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                // g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                //   g.clearRect(x, y, e.getX(), e.getY());
                // g.strokeLine(x, y,x1,y1);
            });
            canvas.setOnMouseReleased(e -> {
                //      g.clearRect(x1, y1, Math.abs(e.getX() + (int) x1), Math.abs(e.getY() + (int) y1));
                g.strokeLine(e.getX() - differencex, e.getY() - differencey, e.getX(), e.getY());
                //g.strokeLine(x1,y1,x+x,y+y);
            });
        } else if (str.equalsIgnoreCase("Circle")) {
            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();
            //int w = Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue();
            int r = Json.AS.get(Json.AS.size() - 1).getProperties().get("r").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                //   g.clearRect(x, y, r, r);
            });
            canvas.setOnMouseDragged(e -> {
                //   g.clearRect(x, y, e.getX(), e.getY());
                //   g.fillOval(Math.abs(e.getX()), Math.abs(e.getY()) ,r,r);
                //   g.clearRect(x, y, e.getX(), e.getY());
            });
            canvas.setOnMouseReleased(e -> {
                //  g.clearRect(x, y, Math.abs(e.getX() + (int) x), Math.abs(e.getY() + (int) y));
                if (Json.AS.get(Json.AS.size() - 1).getFillColor() == WHITE) {
                    g.strokeOval(Math.abs(e.getX()), Math.abs(e.getY()), r, r);
                } else {
                    g.fillOval(Math.abs(e.getX()), Math.abs(e.getY()), r, r);
                }
            });
        } else if (str.equalsIgnoreCase("Ellipse")) {

            int x = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getX();
            int y = (int) Json.AS.get(Json.AS.size() - 1).getPosition().getY();
            int w = Json.AS.get(Json.AS.size() - 1).getProperties().get("w").intValue();
            int h = Json.AS.get(Json.AS.size() - 1).getProperties().get("h").intValue();

            canvas.setOnMousePressed(e -> {
                g.beginPath();
                g.clearRect(x, y, 0, 0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX(), e.getY());
                g.fillOval(x, y, w, h);
            });
            canvas.setOnMouseReleased(e -> {
                g.clearRect(x, y, w, h);

                if (Json.AS.get(Json.AS.size() - 1).getFillColor() == WHITE) {
                    g.strokeOval(e.getX(), e.getY(), w, h);
                } else {
                    g.fillOval(e.getX(), e.getY(), w, h);
                }

            });
        } else if (str.equalsIgnoreCase("Triangle")) {

            Triangle triangle = (Triangle) Json.AS.get(Json.AS.size() - 1);
            int w = triangle.getProperties().get("w").intValue();
            int x1 = triangle.getPoint1().x;
            int y1 = triangle.getPoint1().y;
            int x2 = triangle.getPoint2().x;
            int y2 = triangle.getPoint2().y;

            Map<String, Double> proprec22;
            proprec22 = new HashMap<>();
            proprec22.put("w", 0.0);
            canvas.setOnMousePressed(e -> {
                g.beginPath();
                proprec22.put("w", 0.0);
            });
            canvas.setOnMouseDragged(e -> {
                g.clearRect(x, y, e.getX(), e.getY());
            });
            canvas.setOnMouseReleased(e -> {
                double[] Xcoord = {Math.abs((x1 + w / 2) - x1), Math.abs(x1 - x2), (int) x, e.getX()};
                double[] Ycoord = {e.getY(), Math.abs(y2 - y1), e.getY()};
                g.strokePolygon(Xcoord, Ycoord, 3); //draws a regular triangle   
                g.fillPolygon(Xcoord, Ycoord, 3);
                g.closePath();
            });
        }
    }

    public void Repaint() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        for (int i = 0; i < Json.AS.size(); i++) {
            String str = Json.AS.get(i).type;
            int px = (int) Json.AS.get(i).getPosition().getX();
            int py = (int) Json.AS.get(i).getPosition().getY();
            if (str.equalsIgnoreCase("Rectangle")) {
                double w = Json.AS.get(i).getProperties().get("w");
                double h = Json.AS.get(i).getProperties().get("h");
                if (Json.AS.get(i).getFillColor() == WHITE) {
                    g.setStroke(Json.AS.get(i).getColor());
                    g.strokeRect(px, py, w, h);
                } else {
                    g.setFill(Json.AS.get(i).getFillColor());
                    g.fillRect(px, py, w, h);
                }

            } else if (str.equalsIgnoreCase("Square")) {

                double s = Json.AS.get(i).getProperties().get("s");

                if (Json.AS.get(i).getFillColor() == WHITE) {
                    g.setStroke(Json.AS.get(i).getColor());
                    g.strokeRect(px, py, s, s);
                } else {
                    g.setFill(Json.AS.get(i).getFillColor());
                    g.fillRect(px, py, s, s);
                }

            } else if (str.equalsIgnoreCase("Line")) {
                double x2 = Json.AS.get(i).getProperties().get("y");
                double y2 = Json.AS.get(i).getProperties().get("x");
                g.setStroke(Json.AS.get(i).getColor());
                g.strokeLine(px, py, x2, y2);

            } else if (str.equalsIgnoreCase("Circle")) {
                double r = Json.AS.get(i).getProperties().get("r");

                if (Json.AS.get(i).getFillColor() == WHITE) {
                    g.setStroke(Json.AS.get(i).getColor());
                    g.strokeOval(px, py, r, r);
                } else {
                    g.setFill(Json.AS.get(i).getFillColor());
                    g.fillOval(px, py, r, r);
                }

            } else if (str.equalsIgnoreCase("Ellipse")) {

                double r1 = Json.AS.get(i).getProperties().get("w");
                double r2 = Json.AS.get(i).getProperties().get("h");
                if (Json.AS.get(i).getFillColor() == WHITE) {
                    g.setStroke(Json.AS.get(i).getColor());
                    g.strokeOval(px, py, r1, r2);
                } else {
                    g.setFill(Json.AS.get(i).getFillColor());
                    g.fillOval(px, py, r1, r2);
                }
            } else if (str.equalsIgnoreCase("Triangle")) {
                int w = Json.AS.get(i).getProperties().get("w").intValue();
                int x2 = Json.AS.get(i).getProperties().get("x2").intValue();
                int y2 = Json.AS.get(i).getProperties().get("y2").intValue();
                double[] Xcoord = {(int) px + Math.abs(w) / 2, (int) px, (int) x2};
                double[] Ycoord = {(int) py, (int) y2, (int) y2};
                if (Json.AS.get(i).getFillColor() == WHITE) {
                    g.setStroke(Json.AS.get(i).getColor());
                    g.strokePolygon(Xcoord, Ycoord, 3);
                } else {
                    g.setFill(Json.AS.get(i).getFillColor());
                    g.fillPolygon(Xcoord, Ycoord, 3);
                }

            }
        }
    }

    @Override
    public void removeShapes() {
        if (Json.AS.size() == 1) {
            cleansheet();
        } else {
            Json.AS.remove(Json.AS.size() - 1);
            System.out.println(Json.AS.get(Json.AS.size() - 1).type);
            cleansheet();
            Repaint();
        }
    }
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    public void DargALL() {

        canvas.setOnMousePressed(canvasOnMousePressedEventHandler);
        canvas.setOnMouseDragged(canvasOnMouseDraggedEventHandler);

    }

    EventHandler<MouseEvent> canvasOnMousePressedEventHandler
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Canvas) (t.getSource())).getTranslateX();
            orgTranslateY = ((Canvas) (t.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> canvasOnMouseDraggedEventHandler
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ((Canvas) (t.getSource())).setTranslateX(newTranslateX);
            ((Canvas) (t.getSource())).setTranslateY(newTranslateY);
        }
    };

}
