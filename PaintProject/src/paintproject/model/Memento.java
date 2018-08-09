/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject.model;

import java.awt.Color;
import paintproject.model.AbstractShape;

/**
 *
 * @author dell-pc
 */
public class Memento {
     private AbstractShape absShape;

    public Memento(AbstractShape absShape) {
       this.absShape=absShape;
      
    }

    public AbstractShape getSavedArticle() {
        return absShape;
    }
    
}
