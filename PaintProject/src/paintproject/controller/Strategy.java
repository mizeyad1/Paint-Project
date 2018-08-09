/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject.controller;

import java.io.File;
import java.io.IOException;
import javafx.scene.canvas.Canvas;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONException;
import org.xml.sax.SAXException;

/**
 *
 * @author Ahmed Bahey
 */
public interface Strategy {
    
   public void Write(File fileName)  throws ParserConfigurationException, SAXException, IOException ;
   public void Read(File fileName, Canvas canvas) throws JSONException;
    
}
