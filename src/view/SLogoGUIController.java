package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import exception.SLogoException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.LogoFileLoader;
import model.Model;
import model.ResourceLoader;
import model.SLogoCustomCommand;
import model.SLogoDisplayData;
import model.SLogoVariable;

/**
 * A controller class used in conjunction with SceneBuilder's
 * UI.fxml file
 *
 * @author Hunter, Michelle
 */
public class SLogoGUIController implements Initializable, Observer {

    private static final String HELP_URL = "http://htmlpreview.github.io/?https://" +
            "github.com/narendly/narendly.github.io/blob/master/slogo_help.html";
    private static final String CSS_PATH = "view/splashstyle.css";
    private static final int POPUP_WIDTH = 1024;
    private static final int POPUP_HEIGHT = 768;
    private static final int PANE_WIDTH = 200;
    private static final int PANE_HEIGHT = 170;
    private static final int RGB_CONST = 255;
    private static final int ERROR_INDEX = 7;
    private static final String ERROR = "ERROR";
    private static final int WORKSPACE_ONE = 0;
    private static final int WORKSPACE_TWO = 1;
    private static final int WORKSPACE_THREE = 2;
    private static final int WORKSPACE_FOUR = 3;
    private static final int WORKSPACE_FIVE = 4;

    private ResourceLoader myResourceLoader;
    private ResourceLoader myErrorLoader;
    private WebView	myBrowser;
    private WebEngine myWebEngine;
    private ListView<String> myHistoryPaneView;
    private ListView<String> myPropertiesPaneView = new ListView<>();
    private ListView<String> myVariableView;
    private ListView<String> myCustomCommandView;
    private ObservableList<String> myProperties;
    private Stage myCurrentStage;
    private Color myCanvasColor;
    private ColorPicker myColorPicker;
    private HBox myColorHBox;
    private SLogoFileChooserBuilder myFileChooser;
    private SLogoCustomizerBuilder myCustomizer;
    private SLogoPropertiesData myPropertiesData;
    private Model myModel;
    private String myCommand;
    private List<String> myHistory;

    /**
     * The following are FXML-JavaFX component links
     * 
     */
    //Help button
    @FXML
    private Button myHelpButton;
    
    //Read file prompt button
    @FXML
    private Button myReadButton;

    //Textfield
    @FXML
    private TextField myTextField;

    //Run button
    @FXML
    private Button myRunButton;

    //Main Pane
    @FXML
    private Pane myCanvas;

    //Drop-down menuButton - Choose Project
    @FXML
    private MenuButton myMenuButton;

    //MenuButton's MenuItem list
    @FXML
    private MenuItem myProject1;
    @FXML
    private MenuItem myProject2;
    @FXML
    private MenuItem myProject3;
    @FXML
    private MenuItem myProject4;
    @FXML
    private MenuItem myProject5;

    //Command History Pane where ObservableList<CommandNode> will go
    @FXML
    private ScrollPane myCommandHistoryPane;

    //myVariablePane where ObservableList<Variable> will go
    @FXML
    private ScrollPane myVariablePane;
    
    //CustomCommands Pane where ObservableList<SLogoCustomCommand> will go
    @FXML
    private ScrollPane myCustomPane;

    //Displays the properties of a turtle
    @FXML
    private Pane myPropertyPane;

    //addWorkspace button
    @FXML
    private Button myAddWorkspaceButton;

    //Customize button
    @FXML
    private Button myCustomizeButton;


    /**
     * All GUI elements are initialized in this method
     * and FXML settings are read
     * 
     */
    @Override 
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        myFileChooser = new SLogoFileChooserBuilder();
        myResourceLoader = new ResourceLoader("default.properties");
        myErrorLoader = new ResourceLoader("error.properties");
        myHistory = new ArrayList<>();
        myVariableView = new ListView<>();
        myCustomCommandView = new ListView<>();
        myPropertyPane.getChildren().add(myPropertiesPaneView);
        try {
            myCustomizer = new SLogoCustomizerBuilder(this);
        } catch (SLogoException e) {
            e.showErrorDialog(getErrorLoader().getString("CustomizerError"));
        }
        myCustomizer.hide();

        // Assigns actions to buttons and other components
        assignMenuAction();
        assignHelpAction();
        assignReadAction();
        assignRunAction();
        assignAddWorkspaceAction();
        customize();
    }

    /**
     * Assigns an action to Run button
     * 
     */
    private void assignRunAction () {
        myRunButton.setOnAction(e -> {
            myCommand = myTextField.getText();
            myTextField.clear();
            run(myCommand);
        });  
    }

    /**
     * Assigns an action to addWorkspace Button
     * Dependency is the model interface
     * 
     */
    private void assignAddWorkspaceAction () {
        myAddWorkspaceButton.setOnAction(e -> {
            myCurrentStage = (Stage) myAddWorkspaceButton.getScene().getWindow();
            myCurrentStage.hide();
            try {
                getModel().addWorkspace();
            } catch (Exception e1) {}
        });
    }

    /**
     * Assigns an action to the HELP Button
     * 
     */
    private void assignHelpAction () {
        myHelpButton.setOnAction(e -> {
            popup(HELP_URL);
        });
    }

    /**
     * Assigns an action to the READ FILE button and provides the user
     * a function getSelectedFile to enable reading in the name of the file
     * 
     */
    private void assignReadAction () {
        myReadButton.setOnAction(e -> {
            getFileChooser().promptUser();
            LogoFileLoader loader = 
                    new LogoFileLoader(getFileChooser().getSelectedFile());
            try {
                loader.getAllCommands().forEach(c -> run(c));
            }
            catch (Exception e1) {}
        });
    }
    
    /**
     * Manually assigns an action to each MenuItem
     * 
     * This is not the optimal design, but it was done this way
     * because FXML (created by SceneBuilder) does not support
     * dynamic addition of MenuItems to MenuButton
     * 
     */
    private void assignMenuAction () {
        myProject1.setOnAction(e -> {
            try {
                getModel().switchWorkspace(WORKSPACE_ONE);
            } catch (Exception e1) {}
        });
        myProject2.setOnAction(e -> {
            try {
                getModel().switchWorkspace(WORKSPACE_TWO);
            } catch (Exception e1) {}
        });
        myProject3.setOnAction(e -> {
            try {
                getModel().switchWorkspace(WORKSPACE_THREE);
            } catch (Exception e1) {}
        });
        myProject4.setOnAction(e -> {
            try {
                getModel().switchWorkspace(WORKSPACE_FOUR);
            } catch (Exception e1) {}
        });
        myProject5.setOnAction(e -> {
            try {
                getModel().switchWorkspace(WORKSPACE_FIVE);
            } catch (Exception e1) {}
        });
    }

    /**
     * Defines and assigns an action to Run button
     * Notifies Model that there is a command to be processed
     * 
     * @param command
     */
    public void run(String command){
        setCommand(myCommand);
        try {
            getModel().readCommand(command);
        } catch (SLogoException e1) {
            command = "ERROR: " + command;
        }
        myHistory.add(command);
        displayHistory();
        displayProperties();
        myPropertiesData.notifyObservers();
    }

    /**
     * Takes the user to the customized help page
     * 
     * @param link
     */
    private void popup(String link){
        myBrowser = new WebView();
        myWebEngine = myBrowser.getEngine();
        myWebEngine.load(link);
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();        
        vbox.getChildren().addAll(myBrowser);
        VBox.setVgrow(myBrowser, Priority.ALWAYS);
        stage.setTitle(getResourceLoader().getString("PopupTitle"));
        stage.setWidth(POPUP_WIDTH);
        stage.setHeight(POPUP_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Lists commands in the command history pane
     * 
     */
    private void displayHistory(){
        myHistoryPaneView = new ListView<>();
        ObservableList<String> items =FXCollections.observableArrayList(myHistory);
        myHistoryPaneView.setItems(items);
        myCommandHistoryPane.setContent(myHistoryPaneView);

        myHistoryPaneView.getSelectionModel().selectedItemProperty().addListener
        (new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                                String oldValue, String newValue) {
                if(newValue.contains(ERROR)) {
                    myCommand = newValue.substring(ERROR_INDEX);
                } else {
                    myCommand = newValue;
                }
                run(myCommand);
            }
        });
    }   

    /**
     * Displays user-created variables in the scroll pane
     * 
     * @param variables
     */
    public void displayVariables (ObservableList<SLogoVariable> variables) {
        ObservableList<String> list = FXCollections
                .observableArrayList(new ArrayList<String>());
        variables.stream().forEach(n -> list.add(n.getName() + "  :  " + n.getValue()));
        getVariableView().setItems((ObservableList<String>) list);
        getVariablePane().setContent(getVariableView());
    }

    /**
     * Displays user-created commands in the scroll pane
     * 
     * @param customs
     */
    public void displayCustomCommands (ObservableList<SLogoCustomCommand> customs) {
        ObservableList<String> list = FXCollections
                .observableArrayList(new ArrayList<String>());
        customs.stream().forEach(n -> list.add(n.getName()));
        getCustomCommandView().setItems((ObservableList<String>) list);
        getCustomPane().setContent(getCustomCommandView());
    }

    /**
     * Returns ListView of custom commands
     * 
     * @return
     */
    private ListView<String> getCustomCommandView() {
        return myCustomCommandView;
    }

    /**
     * Displays properties
     * 
     */
    public void displayProperties(){
        myPropertiesPaneView.setItems(myProperties);
        myPropertiesPaneView.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
    }

    /**
     * Updates property info on the bottom left side of the screen
     * 
     * @param displayData
     */
    public void updateProperties(SLogoDisplayData displayData){
        myProperties = FXCollections.observableArrayList (
                        ("Direction: " + Double.toString(displayData.getDirection())),
                        ("X position: " + displayData.getX()),
                        ("Y position: " + displayData.getY()),
                        ("Pen Down: " + displayData.getPen().getDown()),
                        ("Pen Color: " + displayData.getPen().getColorIndex()),
                        ("Pen Size: " + displayData.getPen().getSize()),
                        ("Pen Style: " + myCustomizer.getMyStrokeStyle())
        		);
        myPropertiesPaneView.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        myPropertiesPaneView.setItems(myProperties);
    }

    /**
     * Assigns an action to the customize button
     * 
     */
    private void customize(){
        myCustomizeButton.setOnMouseClicked(e -> {
            myCustomizer.setPropertiesData(myPropertiesData);
            myCustomizer.show();    		
        });
    }

    /**
     * Converts Color object to its hex string
     * 
     * @param color
     * @return
     */
    public String toRGBCode (Color color) {
        return String.format( "#%02X%02X%02X",
                              (int) (color.getRed() * RGB_CONST),
                              (int) (color.getGreen() * RGB_CONST),
                              (int) (color.getBlue() * RGB_CONST));
    }

    /**
     * Getter for Customizer Button
     * 
     * @return
     */
    public SLogoCustomizerBuilder getCustomizer() {
        return myCustomizer;
    }

    /**
     * Color picker method for Customize button
     * 
     * @return
     */
    private HBox chooseColor(){		
        Label colorLabel = new Label(getResourceLoader().getString("ColorLabel"));

        myColorPicker = new ColorPicker();
        myColorPicker.setOnAction(e -> {
            myCanvasColor = myColorPicker.getValue();
        });

        myColorHBox = new HBox();
        myColorHBox.getStylesheets().add(CSS_PATH);
        myColorHBox.getChildren().addAll(colorLabel, myColorPicker);

        return myColorHBox;
    }

    /**
     * Returns current pane color
     * 
     * @return
     */
    public Color getPaneColor(){
        return myCanvasColor;
    }

    /**
     * Sets new pane color
     * 
     * @throws SLogoException
     */
    public void setPaneColor() throws SLogoException{
        this.myCanvasColor = new SLogoCustomizerBuilder(this).getMyPaneColor();
    }

    /**
     * Sets myCommand
     * 
     * @param myCommand
     */
    public void setCommand(String myCommand) {
        this.myCommand = myCommand;
    }

    /**
     * Returns current canvas
     * 
     * @return
     */
    public Pane getCanvas() {
        return myCanvas;
    }

    /**
     * Adds a node to canvas
     * 
     * @param list
     */
    public void addToCanvas(Node list) {
        getCanvas().getChildren().add(list);
    }

    /**
     * Adds a list of nodes to canvas
     * 
     * @param nodelist
     */
    public void addToCanvas(List<Line> nodelist) {
        getCanvas().getChildren().addAll(nodelist);
    }

    /**
     * Sets a new PropertiesData
     * 
     * @param propertiesData
     */
    public void setPropertiesData(SLogoPropertiesData propertiesData) {
        this.myPropertiesData = propertiesData;
        myPropertiesData.addObserver(this);
    }

    /**
     * @return the myModel
     */
    public Model getModel() {
        return myModel;
    }

    /**
     * @param myModel the myModel to set
     */
    public void setModel(Model myModel) {
        this.myModel = myModel;
    }

    /**
     * @return the myMenuButton
     */
    public MenuButton getMenuButton() {
        return myMenuButton;
    }

    /**
     * @param myMenuButton the myMenuButton to set
     */
    public void setMenuButton(MenuButton myMenuButton) {
        this.myMenuButton = myMenuButton;
    }

    /**
     * updates canvas color
     */
    @Override
    public void update(Observable o, Object arg) {
        getCanvas().setStyle("-fx-background-color: " 
                + toRGBCode(myPropertiesData.getPaneColor()));
    }

    /**
     * @return the myCommand
     */
    public String getCommand() {
        return myCommand;
    }

    /**
     * @return the myVariableView
     */
    public ListView<String> getVariableView() {
        return myVariableView;
    }

    /**
     * @param myVariableView the myVariableView to set
     */
    public void setVariableView(ListView<String> myVariableView) {
        this.myVariableView = myVariableView;
    }

    /**
     * @return the myResourceLoader
     */
    public ResourceLoader getResourceLoader () {
        return myResourceLoader;
    }

    /**
     * @param myResourceLoader the myResourceLoader to set
     */
    public void setResourceLoader (ResourceLoader myResourceLoader) {
        this.myResourceLoader = myResourceLoader;
    }

    /**
     * @return the myErrorLoader
     */
    public ResourceLoader getErrorLoader () {
        return myErrorLoader;
    }

    /**
     * @param myErrorLoader the myErrorLoader to set
     */
    public void setErrorLoader (ResourceLoader myErrorLoader) {
        this.myErrorLoader = myErrorLoader;
    }

    /**
     * @return the myVariablePane
     */
    public ScrollPane getVariablePane () {
        return myVariablePane;
    }

    /**
     * @return the myCustomPane
     */
    public ScrollPane getCustomPane () {
        return myCustomPane;
    }

    /**
     * @return the myFileChooser
     */
    public SLogoFileChooserBuilder getFileChooser () {
        return myFileChooser;
    }
}