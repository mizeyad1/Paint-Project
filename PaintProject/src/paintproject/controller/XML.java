package paintproject.controller;

//import java.awt.Color;
import javafx.scene.paint.Color;
import java.awt.Point;

import java.io.File;
import java.io.IOException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.paint.Color.color;
import javafx.scene.paint.Paint;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XML implements Strategy {

    public void Write(File fileName) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element rootElement = doc.createElement("Shapeslist");
            //append root element to document
            doc.appendChild(rootElement);

            for (int i = 0; i < Json.AS.size(); i++) {

                if (Json.AS.get(i).type.equalsIgnoreCase("Rectangle")) {

                    rootElement.appendChild(getRecshape(doc, Json.AS.get(i).type, Json.AS.get(i).getPosition().getX(), Json.AS.get(i).getPosition().getY(), Json.AS.get(i).getColor(), Json.AS.get(i).getFillColor(), Json.AS.get(i).getProperties().get("w").intValue(), Json.AS.get(i).getProperties().get("h").intValue()));

                } else if (Json.AS.get(i).type.equalsIgnoreCase("Square")) {
                    rootElement.appendChild(getsqushape(doc, Json.AS.get(i).type, Json.AS.get(i).getPosition().getX(), Json.AS.get(i).getPosition().getY(), Json.AS.get(i).getColor(), Json.AS.get(i).getFillColor(), Json.AS.get(i).getProperties().get("s")));

                } else if (Json.AS.get(i).type.equalsIgnoreCase("Circle")) {
                    rootElement.appendChild(getcircshape(doc, Json.AS.get(i).type, Json.AS.get(i).getPosition().getX(), Json.AS.get(i).getPosition().getY(), Json.AS.get(i).getColor(), Json.AS.get(i).getFillColor(), Json.AS.get(i).getProperties().get("r")));

                } else if (Json.AS.get(i).type.equalsIgnoreCase("Ellipse")) {
                    rootElement.appendChild(getellshape(doc, Json.AS.get(i).type, Json.AS.get(i).getPosition().getX(), Json.AS.get(i).getPosition().getY(), Json.AS.get(i).getColor(), Json.AS.get(i).getFillColor(), Json.AS.get(i).getProperties().get("w"), Json.AS.get(i).getProperties().get("h")));

                } else if (Json.AS.get(i).type.equalsIgnoreCase("Line")) {

                    rootElement.appendChild(getlineshape(doc, Json.AS.get(i).type, Json.AS.get(i).getPosition().getX(), Json.AS.get(i).getPosition().getY(), Json.AS.get(i).getColor(), Json.AS.get(i).getFillColor(), Json.AS.get(i).getProperties().get("x"), Json.AS.get(i).getProperties().get("y")));

                } else if (Json.AS.get(i).type.equalsIgnoreCase("Triangle")) {
                    rootElement.appendChild(getTrishape(doc, Json.AS.get(i).type, Json.AS.get(i).getPosition().getX(), Json.AS.get(i).getPosition().getY(), Json.AS.get(i).getColor(), Json.AS.get(i).getFillColor(), Json.AS.get(i).getProperties().get("w").intValue(), Json.AS.get(i).getProperties().get("x2").intValue(), Json.AS.get(i).getProperties().get("y2").intValue()));

                }

            }
            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            // StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(fileName);

            //write data
            //transformer.transform(source, console);
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Node getRecshape(Document doc, String type, double px, double py, Color color, Color fillcolor, double w, double h) {
        Element shape = doc.createElement("shape");

        //set id attribute
        shape.setAttribute("type", type);
        shape.appendChild(getshapeElements(doc, shape, "px", String.valueOf(px)));
        shape.appendChild(getshapeElements(doc, shape, "py", String.valueOf(py)));
        shape.appendChild(getshapeElements(doc, shape, "color", String.valueOf(color)));
        shape.appendChild(getshapeElements(doc, shape, "fillcolor", String.valueOf(fillcolor)));
        shape.appendChild(getshapeElements(doc, shape, "w", String.valueOf(w)));
        shape.appendChild(getshapeElements(doc, shape, "h", String.valueOf(h)));
        return shape;
    }

    private static Node getsqushape(Document doc, String type, double px, double py, Color color, Color fillcolor, double s) {
        Element shape = doc.createElement("shape");

        //set id attribute
        shape.setAttribute("type", type);
        shape.appendChild(getshapeElements(doc, shape, "px", String.valueOf(px)));
        shape.appendChild(getshapeElements(doc, shape, "py", String.valueOf(py)));
        shape.appendChild(getshapeElements(doc, shape, "color", String.valueOf(color)));
        shape.appendChild(getshapeElements(doc, shape, "fillcolor", String.valueOf(fillcolor)));
        shape.appendChild(getshapeElements(doc, shape, "s", String.valueOf(s)));

        return shape;
    }

    private static Node getlineshape(Document doc, String type, double px, double py, Color color, Color fillcolor, double x, double y) {
        Element shape = doc.createElement("shape");

        //set id attribute
        shape.setAttribute("type", type);
        shape.appendChild(getshapeElements(doc, shape, "px", String.valueOf(px)));
        shape.appendChild(getshapeElements(doc, shape, "py", String.valueOf(py)));
        shape.appendChild(getshapeElements(doc, shape, "color", String.valueOf(color)));
        shape.appendChild(getshapeElements(doc, shape, "fillcolor", String.valueOf(fillcolor)));
        shape.appendChild(getshapeElements(doc, shape, "x", String.valueOf(x)));
        shape.appendChild(getshapeElements(doc, shape, "y", String.valueOf(y)));
        return shape;
    }

    private static Node getcircshape(Document doc, String type, double px, double py, Color color, Color fillcolor, double r) {
        Element shape = doc.createElement("shape");

        //set id attribute
        shape.setAttribute("type", type);
        shape.appendChild(getshapeElements(doc, shape, "px", String.valueOf(px)));
        shape.appendChild(getshapeElements(doc, shape, "py", String.valueOf(py)));
        shape.appendChild(getshapeElements(doc, shape, "color", String.valueOf(color)));
        shape.appendChild(getshapeElements(doc, shape, "fillcolor", String.valueOf(fillcolor)));
        shape.appendChild(getshapeElements(doc, shape, "r", String.valueOf(r)));
        return shape;
    }

    private static Node getellshape(Document doc, String type, double px, double py, Color color, Color fillcolor, double r1, double r2) {
        Element shape = doc.createElement("shape");

        //set id attribute
        shape.setAttribute("type", type);
        shape.appendChild(getshapeElements(doc, shape, "px", String.valueOf(px)));
        shape.appendChild(getshapeElements(doc, shape, "py", String.valueOf(py)));
        shape.appendChild(getshapeElements(doc, shape, "color", String.valueOf(color)));
        shape.appendChild(getshapeElements(doc, shape, "fillcolor", String.valueOf(fillcolor)));
        shape.appendChild(getshapeElements(doc, shape, "w", String.valueOf(r1)));
        shape.appendChild(getshapeElements(doc, shape, "h", String.valueOf(r2)));
        return shape;
    }

    private static Node getTrishape(Document doc, String type, double px, double py, Color color, Color fillcolor, double w, double x2, double y2) {
        Element shape = doc.createElement("shape");

        //set id attribute
        shape.setAttribute("type", type);
        shape.appendChild(getshapeElements(doc, shape, "px", String.valueOf(px)));
        shape.appendChild(getshapeElements(doc, shape, "py", String.valueOf(py)));
        shape.appendChild(getshapeElements(doc, shape, "color", String.valueOf(color)));
        shape.appendChild(getshapeElements(doc, shape, "fillcolor", String.valueOf(fillcolor)));
        shape.appendChild(getshapeElements(doc, shape, "w", String.valueOf(w)));
        shape.appendChild(getshapeElements(doc, shape, "x2", String.valueOf(x2)));
        shape.appendChild(getshapeElements(doc, shape, "y2", String.valueOf(y2)));

        return shape;
    }

    private static Node getshapeElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public void Read(File FileName, Canvas canvas) throws JSONException {
        GraphicsContext g = canvas.getGraphicsContext2D();
        try {
            // File inputFile = new File(FileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(FileName);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("shape");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (eElement.getAttribute("type").equalsIgnoreCase("Rectangle")) {
                        System.out.println(eElement.getAttribute("type"));
                        String px = eElement.getElementsByTagName("px").item(0).getTextContent();
                        String py = eElement.getElementsByTagName("py").item(0).getTextContent();

                        String color = eElement.getElementsByTagName("color").item(0).getTextContent();
                        String fillcolor = eElement.getElementsByTagName("fillcolor").item(0).getTextContent();
                        String w = eElement.getElementsByTagName("w").item(0).getTextContent();
                        String h = eElement.getElementsByTagName("h").item(0).getTextContent();
                        System.out.println(fillcolor);
                        if (fillcolor.equals("0xffffffff")) {
                            g.setStroke(Paint.valueOf(color));
                            g.strokeRect(Double.valueOf(px), Double.valueOf(py), Double.valueOf(w), Double.valueOf(h));
                        } else {

                            g.setFill(Paint.valueOf(fillcolor));
                            g.fillRect(Double.valueOf(px), Double.valueOf(py), Double.valueOf(w), Double.valueOf(h));
                        }

                    } else if (eElement.getAttribute("type").equalsIgnoreCase("square")) {
                        String px = eElement.getElementsByTagName("px").item(0).getTextContent();
                        String py = eElement.getElementsByTagName("py").item(0).getTextContent();
                        String color = eElement.getElementsByTagName("color").item(0).getTextContent();
                        String fillcolor = eElement.getElementsByTagName("fillcolor").item(0).getTextContent();
                        String s = eElement.getElementsByTagName("s").item(0).getTextContent();
                        if (fillcolor.equals("0xffffffff")) {
                            g.setStroke(Paint.valueOf(color));
                            g.strokeRect(Double.valueOf(px), Double.valueOf(py), Double.valueOf(s), Double.valueOf(s));
                        } else {

                            g.setFill(Paint.valueOf(fillcolor));
                              g.fillRect(Double.valueOf(px), Double.valueOf(py), Double.valueOf(s), Double.valueOf(s));
                        }
                    } else if (eElement.getAttribute("type").equalsIgnoreCase("Line")) {
                        String px = eElement.getElementsByTagName("px").item(0).getTextContent();
                        String py = eElement.getElementsByTagName("py").item(0).getTextContent();
                        String color = eElement.getElementsByTagName("color").item(0).getTextContent();
                        String fillcolor = eElement.getElementsByTagName("fillcolor").item(0).getTextContent();
                        String x = eElement.getElementsByTagName("x").item(0).getTextContent();
                        String y = eElement.getElementsByTagName("y").item(0).getTextContent();
                         g.setStroke(Paint.valueOf(color));
                        g.strokeLine(Double.valueOf(px), Double.valueOf(py), Double.valueOf(x), Double.valueOf(y));
                      
                    } else if (eElement.getAttribute("type").equalsIgnoreCase("Circle")) {
                        String px = eElement.getElementsByTagName("px").item(0).getTextContent();
                        String py = eElement.getElementsByTagName("py").item(0).getTextContent();
                        String color = eElement.getElementsByTagName("color").item(0).getTextContent();
                        String fillcolor = eElement.getElementsByTagName("fillcolor").item(0).getTextContent();
                        String r = eElement.getElementsByTagName("r").item(0).getTextContent();
 g.setStroke(Paint.valueOf(color));
                       
                        
                          if (fillcolor.equals("0xffffffff")) {
                           g.strokeOval(Double.valueOf(px), Double.valueOf(py), Double.valueOf(r), Double.valueOf(r));
                        } else {

                            g.setFill(Paint.valueOf(fillcolor));
                                g.fillOval(Double.valueOf(px), Double.valueOf(py), Double.valueOf(r), Double.valueOf(r));
                        }


                    } else if (eElement.getAttribute("type").equalsIgnoreCase("Ellipse")) {
                        String px = eElement.getElementsByTagName("px").item(0).getTextContent();
                        String py = eElement.getElementsByTagName("py").item(0).getTextContent();
                        String color = eElement.getElementsByTagName("color").item(0).getTextContent();
                        String fillcolor = eElement.getElementsByTagName("fillcolor").item(0).getTextContent();
                        String r1 = eElement.getElementsByTagName("w").item(0).getTextContent();
                        String r2 = eElement.getElementsByTagName("h").item(0).getTextContent();
                         g.setStroke(Paint.valueOf(color));
                     
                        
                         if (fillcolor.equals("0xffffffff")) {
                            g.strokeOval(Double.valueOf(px), Double.valueOf(py), Double.valueOf(r1), Double.valueOf(r2));
                        } else {

                            g.setFill(Paint.valueOf(fillcolor));
                               g.fillOval(Double.valueOf(px), Double.valueOf(py), Double.valueOf(r1), Double.valueOf(r2));
                        }

                    } else if (eElement.getAttribute("type").equalsIgnoreCase("Triangle")) {

                        String px = eElement.getElementsByTagName("px").item(0).getTextContent();

                        String py = eElement.getElementsByTagName("py").item(0).getTextContent();
                        String color = eElement.getElementsByTagName("color").item(0).getTextContent();
                        String fillcolor = eElement.getElementsByTagName("fillcolor").item(0).getTextContent();
                        String w = eElement.getElementsByTagName("w").item(0).getTextContent();
                        String x2 = eElement.getElementsByTagName("x2").item(0).getTextContent();
                        String y2 = eElement.getElementsByTagName("y2").item(0).getTextContent();
                          g.setStroke(Paint.valueOf(color));
                        double[] Xcoord = {Double.valueOf(px), Double.valueOf(w) / 2.0, Double.valueOf(x2), Double.valueOf(x2)};
                        double[] Ycoord = {Double.valueOf(py), Double.valueOf(y2), Double.valueOf(y2)};
                        g.strokePolygon(Xcoord, Ycoord, 3); //draws a regular triangle       
                        
                         if (fillcolor.equals("0xffffffff")) {
                          g.strokePolygon(Xcoord, Ycoord, 3);
                        } else {

                            g.setFill(Paint.valueOf(fillcolor));
                                g.fillPolygon(Xcoord, Ycoord, 3);
                        }

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
