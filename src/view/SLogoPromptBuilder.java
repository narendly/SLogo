package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.ResourceLoader;

/**
 * Subclass of SLogoBuilder that generates a prompt screen
 * that greets the user and takes in necessary information 
 * such as language and pane color
 * 
 * @author Hunter, Michelle
 * 
 */
public class SLogoPromptBuilder extends SLogoBuilder {
    private static final int XPROMPTSIZE = 500;
    private static final int YPROMPTSIZE = 275; 
    private static final int PADDING = 55;
    private static final int SPLASHSIZE = 400;
    private static final int LABEL_FONTSIZE = 32;
    private static final int PREFSIZE = 75;
    private static final String FONT = "Georgia";
    private static final String CSS_PATH = "view/splashstyle.css";

    private ResourceLoader myResourceLoader;
    private String myLanguage;
    private Stage prompt;
    private Scene promptScene;
    private VBox vbox;
    private Label label;
    private ComboBox<Object> comboBox;
    private HBox labelHb;
    private HBox langHb;
    private HBox buttonHb;
    private Label lang;
    private Button myOkayButton;
    private Color myColor;
    private SLogoPropertiesData myPropertiesData;

    /**
     * Constructor that takes in a propertiesData object
     * 
     * @param propertiesData
     */
    public SLogoPromptBuilder(SLogoPropertiesData propertiesData) {
        myResourceLoader = new ResourceLoader("default.properties");
        myColor = Color.WHITE;
        myPropertiesData = propertiesData;
    }

    /**
     * Prompts a screen to the user asking the user to select language
     * 
     */
    public void promptScreen () {
        prompt = new Stage();
        setup();
        setScene(promptScene);		
    }

    /**
     * Basic initialization primitives
     * 
     */
    private void setup(){
        setWelcome();
        setLanguage();
        setButton();
    }

    /**
     * Sets the scene to the stage
     * 
     * @param promptScene
     */
    private void setScene(Scene promptScene) {
        vbox = setVBox();
        promptScene = new Scene(vbox, XPROMPTSIZE, YPROMPTSIZE);
        prompt.setScene(promptScene);
        prompt.showAndWait();
    }

    /**
     * Uses a VBox pattern to align elements in a vertical order
     * 
     * @return
     */
    private VBox setVBox(){
        vbox = new VBox();
        vbox.setPrefSize(SPLASHSIZE, SPLASHSIZE);
        vbox.setPadding(new Insets(PADDING));
        vbox.getChildren().addAll(labelHb, langHb, buttonHb);
        vbox.getStylesheets().add(CSS_PATH);
        return vbox;
    }

    /**
     * Creates a welcome message for the user at startup
     * 
     */
    private void setWelcome(){ 
        label = new Label(getResourceLoader().getString("WelcomeLabel"));
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font(FONT, FontWeight.BOLD, LABEL_FONTSIZE));
        labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);
        labelHb.setPrefSize(PREFSIZE, PREFSIZE);
    }

    /**
     * Sets the language for SLogo
     * 
     */
    private void setLanguage(){ 
        lang = new Label(getResourceLoader().getString("LangLabel"));
        ObservableList<String> options = 
                FXCollections.observableArrayList(
                                                  "English",
                                                  "French",
                                                  "Chinese",
                                                  "German",
                                                  "Italian",
                                                  "Portuguese",
                                                  "Russian",
                                                  "Spanish"
                        );
        comboBox = new ComboBox(options);
        comboBox.setValue(getResourceLoader().getString("DefaultLang"));
        langHb = new HBox();
        langHb.setAlignment(Pos.CENTER);
        langHb.getChildren().addAll(lang, comboBox);
        langHb.setPrefSize(PREFSIZE, PREFSIZE);

    }

    /**
     * Creates the OkayButton that the user can click to 
     * move on to the next screen
     * 
     */
    private void setButton(){ 
        buttonHb = new HBox();
        myOkayButton = new Button(getResourceLoader().getString("OkayButton"));
        buttonHb.setAlignment(Pos.CENTER);
        buttonHb.getChildren().add(myOkayButton);
        buttonHb.setPrefSize(PREFSIZE, PREFSIZE);
        myOkayButton.setOnMouseClicked(e -> {
            myLanguage = comboBox.getSelectionModel().getSelectedItem().toString();
            getPrompt().hide();
        });
    }

    public String getWorkspaceLanguage(){
        return myLanguage;
    }

    public Color sendMyColor(){ 
        return myColor;
    }

    /**
     * @return the prompt
     */
    public Stage getPrompt() {
        return prompt;
    }

    /**
     * @param prompt the prompt to set
     */
    public void setPrompt(Stage prompt) {
        this.prompt = prompt;
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

}