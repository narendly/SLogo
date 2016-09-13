package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.SLogoModel;
import view.SLogoView;

import java.io.IOException;

import exception.SLogoException;

/**
 * Controls the initial setup of our GUI and back end
 * 
 * @author SLogo Team 18
 */

public class Main extends Application {

    private SLogoModel myModel;
    private SLogoView myView;
    
 
    /**
     * Main that launches Application with args
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage myStage) throws SLogoException, IOException {
        initialize();
    }

    /**
     * Passes a reference of View and Model Interfaces, not references to the
     * actual View and Model objects
     * Initializes Model and View
     * 
     * @throws SLogoException
     * @throws IOException
     */
    public void initialize() throws SLogoException, IOException {
        myModel = new SLogoModel();
        myView = new SLogoView(myModel);
        myModel.setView(myView);
        myModel.initialize();
        myView.initialize();
        myModel.addListeners();
    }
}