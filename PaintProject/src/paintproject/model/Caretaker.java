/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject.model;

import java.util.ArrayList;
import static paintproject.model.AbstractShape.currentArticle;
import static paintproject.model.AbstractShape.saveFiles;

/**
 *
 * @author dell-pc
 */
public class Caretaker {
    public ArrayList<Memento> savedArticles = new ArrayList<>();
    public void addMemento(Memento m) { savedArticles.add(m); }
    public Memento getMemento(int index) { return savedArticles.get(index); }
    public void appendMemento(Memento m){savedArticles.set(saveFiles-1, m);}


    
    
}
