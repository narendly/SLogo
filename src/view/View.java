package view;

import java.io.IOException;
import java.util.Observer;
import exception.SLogoException;
import javafx.collections.ObservableList;

/**
 * View interface - A contract for Model
 * View in MVC Design Pattern: Model can only call methods
 * defined in this interface
 * 
 * @author Hunter
 * @param <T>
 */
public interface View<T> {

    SLogoVisualizer getCurrentVisualizer();

    void addVisualizer() throws SLogoException, IOException;

    void switchVisualizer(int index);

    Observer getObserver();
    
    String getLanguage();

    void updateCustoms (ObservableList<T> customs);
    
    void updateVariables (ObservableList<T> variables);

    void updateDisplayData ();
    
}