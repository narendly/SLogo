package model;

import java.io.IOException;

import exception.SLogoException;
import javafx.collections.ObservableList;

/**
 * Model interface - A contract for View Model in MVC Design Pattern: View can
 * only call methods defined in this interface
 * 
 * @author Hunter
 *
 */
public interface Model {

    void readCommand(String command) throws SLogoException;

    void addWorkspace() throws SLogoException, IOException;

    SLogoWorkspace getCurrentWorkspace();

    ObservableList<SLogoDisplayData> getObservableDataList();

    void switchWorkspace(int index) throws SLogoException;

}