package view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.ResourceLoader;

/**
 * ToggleSwitch GUI module used in GUI's Customizer button
 *
 */
public class SLogoCustomizerToggleSwitch extends HBox {

    private static final int WIDTH = 80;
    private static final int BIND_FACTOR = 2;
    private final Label label = new Label();
    private final Button button = new Button();
    private ResourceLoader myResourceLoader;

    private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(true);
    public SimpleBooleanProperty switchOnProperty() { 
        return switchedOn; 
    }

    /**
     * Default constructor that initializes ResourceLoader
     * 
     */
    public SLogoCustomizerToggleSwitch() {
        myResourceLoader = new ResourceLoader("default.properties");
        initialize();
        switchedOn.addListener((a,b,c) -> {
            if (c) {
                label.setText(getResourceLoader().getString("Up"));
                label.toFront();
            }
            else {
                label.setText(getResourceLoader().getString("Down"));
                button.toFront();
            }
        });
    }
    
    /**
     * Initialize that assigns actions to buttons
     * 
     */
    private void initialize() {
        label.setText(getResourceLoader().getString("Down"));

        getChildren().addAll(label, button);	
        button.setOnAction((e) -> {
            switchedOn.set(!switchedOn.get());
        });
        label.setOnMouseClicked((e) -> {
            switchedOn.set(!switchedOn.get());
        });
        setStyle();
        bindProperties();
    }

    /**
     * Defines a style to use for the customizer screen
     * 
     */
    private void setStyle() {
        setWidth(WIDTH);
        label.setAlignment(Pos.CENTER);
        setStyle(getResourceLoader().getString("Style"));
    }

    /**
     * Bind properties
     * 
     */
    private void bindProperties() {
        label.prefWidthProperty().bind(widthProperty().divide(BIND_FACTOR));
        label.prefHeightProperty().bind(heightProperty());
        button.prefWidthProperty().bind(widthProperty().divide(BIND_FACTOR));
        button.prefHeightProperty().bind(heightProperty());
    }

    /**
     * Returns whether the penDown is true/false
     * 
     * @return
     */
    public boolean isDown(){
        return label.getText().equals(getResourceLoader().getString("Down"));
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