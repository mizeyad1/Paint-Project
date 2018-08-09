/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject.model;

//import java.awt.Color;
import paintproject.model.AbstractShape;
import javafx.scene.paint.Color;

/**
 *
 * @author dell-pc
 */
public class Originator {

    private AbstractShape abs;

    public void set(AbstractShape abs) {

        this.abs = abs;
        abs.setColor(Color.BLACK);
        abs.setFillColor(Color.BLACK);
    }

    public Memento storeInMemento() {
        return new Memento(abs);

    }

    public AbstractShape restoreFromMemento(Memento memento) {

        abs = memento.getSavedArticle();
        return abs;

    }

}
