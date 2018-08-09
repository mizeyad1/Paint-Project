
package paintproject.view;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONException;
import org.xml.sax.SAXException;
import paintproject.model.AbstractShape;
import paintproject.model.Shape;


public interface DrawingEngine {
    /* redraw all shapes on the canvas */
public void refresh(Object canvas);
public void addShape(AbstractShape shape);
public void removeShapes();
public void updateShape(Shape oldShape, Shape newShape);
/* return the created shapes objects */
public Shape[] getShapes();
/* limited to 20 steps. You consider these actions in
* undo & redo: addShape, removeShape, updateShape */
public void undo();
public void redo();
/* use the file extension to determine the type,
* or throw runtime exception when unexpected extension */
public void save()throws JSONException, ParserConfigurationException, SAXException, IOException;
public void Load() throws JSONException, ParserConfigurationException, SAXException, IOException;

   
}
