package view;

import exception.SLogoException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ResourceLoader;
import parser.CommandNameLoader;

/**
 * CustomizerBuilder that builds a prompt screen when
 * Customize button is clicked
 * 
 */
public class SLogoCustomizerBuilder {
    private static final String CSS_PATH = "view/splashstyle.css";

    private static final int XPROMPTSIZE = 550;
    private static final int YPROMPTSIZE = 350; 
    private static final int PADDING = 74;
    private static final int SPLASHSIZE = 400;
    private static final int COLORLABELSIZE = 202;
    private static final int PREFSIZE = 50;
    private static final int RECT_WIDTH = 100;
    private static final int RECT_HEIGHT = 20;
    private static final int SLIDER = 10;
    private static final float TICK_UNIT = 0.25f;
    private static final float BLOCK_INCREMENT = 0.1f;
    private static final double DOTTED_LINE = 2.0;
    private static final double DASHED_LINE = 10.0;
    private static final double DASHED_SPACE = 5.0;
    private static final double SOLID_LINE = 1.0;
    private static final String SOLID = "SOLID";
    private static final String DASH = "DASHED";
    private static final String DOT = "DOTTED";
    private static final String PEN_DEFAULT = "PenDefault";
    
    private Stage myCustomizerStage;
    private Scene myCustomizerScene;
    private VBox vbox;
    private Label colorLabel;
    private Color myPaneColor;
    private HBox colorHb;
    private Label fontColorLabel;
    private HBox fontColorHb;
    private Button myOkayButton;
    private HBox buttonHb;
    private ComboBox comboBox;
    private Label penStyleLabel;
    private HBox penStyleHb;
    private Slider thicknessSlider;
    private Label thicknessSliderLabel;
    private HBox thicknessSliderHb;
    private SLogoPropertiesData myPropertiesData;
    private SLogoCustomizerToggleSwitch switchButton;
    private HBox switchHb;
    private Label switchLabel;

    private Color myPenColor;
    private double myPenWidth;
    private String myStrokeStyle;
    private boolean isDown;

    private SLogoGUIController myGUI;
    private CommandNameLoader myCommandNameLoader;
    private ResourceLoader myResourceLoader;
    private ResourceLoader myErrorLoader;
    private int myPenStyle;

    private ObservableList<Color> data = FXCollections.observableArrayList(
                                            Color.WHITE, Color.BLACK, Color.RED, 
                                            Color.ORANGE, Color.YELLOW, Color.GREEN, 
                                            Color.BLUE, Color.PURPLE);
 
    /**
     * Default constructor that initializes ResourceLoader
     * Stage and Scene set
     * 
     * @param myGUI
     * @throws SLogoException
     */
    public SLogoCustomizerBuilder(SLogoGUIController myGUI) throws SLogoException {
        this.myGUI = myGUI;
        myResourceLoader = new ResourceLoader("default.properties");
        myErrorLoader = new ResourceLoader("error.properties");
        setup();
        myCustomizerStage = new Stage();
        myCustomizerScene = new Scene(setVBox(), XPROMPTSIZE, YPROMPTSIZE);
        myCustomizerStage.setScene(myCustomizerScene);
        myCustomizerStage.setTitle(myResourceLoader.getString("CustomizerTitle"));
        myCommandNameLoader = new CommandNameLoader();
    }

    /**
     * Initialize the Customizer button
     * @throws SLogoException 
     * 
     */
    private void setup() throws SLogoException {
        myPaneColor = Color.WHITE;
        myPenColor = Color.BLACK;
        myStrokeStyle = SOLID;
        myPenWidth = 1;
        setColorPicker();
        setFontColor();
        setLine();
        setLineThickness();
        setPenDown();
        setButton();
    }

    /**
     * Create a VBox design to line elements up vertically
     * 
     * @return
     */
    private VBox setVBox() {
        vbox = new VBox();
        vbox.setPrefSize(SPLASHSIZE, SPLASHSIZE);
        vbox.setPadding(new Insets(PADDING));
        vbox.getChildren().addAll(colorHb, fontColorHb, 
                                  thicknessSliderHb, penStyleHb, switchHb, buttonHb);
        vbox.getStylesheets().add(CSS_PATH);
        return vbox;
    }

    /**
     * Hide method for closing customization popup
     */
    public void hide() {
        myCustomizerStage.hide();
    }

    /**
     * Show method for opening customization popup
     */
    public void show() {
        myCustomizerStage.show();
    }

    /**
     * For observable/observer to interact with propertiesData
     * @param propertiesData
     */
    public void setPropertiesData(SLogoPropertiesData propertiesData) {
        myPropertiesData = propertiesData;
    }

    /**
     * Front end color chooser drop down colored rectangles
     * 
     * @author Michelle
     *
     */
    public class ColorRectCell extends ListCell<Color> {
        @Override
        public void updateItem(Color color, boolean empty) {
            super.updateItem(color, empty);
            Rectangle rect = new Rectangle(RECT_WIDTH, RECT_HEIGHT);
            if (color != null) {
                rect.setFill(color);
                setGraphic(rect);
            }
        }
    }

    /**
     * To select color for background
     * 
     * @return
     */
    private ComboBox setColorDropdown() {
        ComboBox<Color> cb = new ComboBox<>();
        cb.setItems(data);
        Callback<ListView<Color>, ListCell<Color>> factory = 
                                  new Callback<ListView<Color>, ListCell<Color>>() {
            @Override
            public ListCell<Color> call(ListView<Color> list) {
                return new ColorRectCell();
            }
        };
        cb.setCellFactory(factory);
        cb.setButtonCell(factory.call(null));
        return cb;
    }
    
    /**
     * Creates an instance of colorpicker to use
     * 
     */
    private void setColorPicker() {
        ComboBox myCB = setColorDropdown();
        myCB.getSelectionModel().select(0);
        StackPane root = new StackPane();
        root.getChildren().add(myCB);

        myCB.getSelectionModel().selectedItemProperty().addListener( 
                                          new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observable, 
                                Color oldValue, Color newValue) {
                myPaneColor = newValue;
           }
        });	    
        colorLabel = new Label(getResourceLoader().getString("ColorPickerLabel"));
        colorLabel.setPrefWidth(COLORLABELSIZE);
        colorHb = new HBox();
        colorHb.getChildren().addAll(colorLabel, root);
        colorHb.setPrefSize(PREFSIZE, PREFSIZE);
    }

    /**
     * Sets a new font color
     * 
     */
    private void setFontColor() {
        ComboBox myCB2 = setColorDropdown();
        // Default value
        myCB2.getSelectionModel().select(1);
        StackPane root = new StackPane();
        root.getChildren().add(myCB2);
        myCB2.getSelectionModel().selectedItemProperty().addListener(
                                         new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observable, 
                                Color oldValue, Color newValue) {
                myPenColor = newValue;
            }
        });	    
        fontColorLabel = new Label(getResourceLoader().getString("FontLabel"));
        fontColorLabel.setPrefWidth(COLORLABELSIZE);
        fontColorHb = new HBox();
        fontColorHb.getChildren().addAll(fontColorLabel, root);
        fontColorHb.setPrefSize(PREFSIZE, PREFSIZE);
    }

    /**
     * Sets line style
     * 
     */
    private void setLine() {
        penStyleLabel = new Label(getResourceLoader().getString("PenLabel"));
        penStyleLabel.setPrefWidth(COLORLABELSIZE);

        ObservableList<String> options = 
                FXCollections.observableArrayList(
                                                  "SOLID",
                                                  "DASHED",
                                                  "DOTTED"						
                        );
        comboBox = new ComboBox(options);
        comboBox.setValue(getResourceLoader().getString(PEN_DEFAULT));
        penStyleHb = new HBox();
        penStyleHb.getChildren().addAll(penStyleLabel, comboBox);
        penStyleHb.setPrefSize(PREFSIZE, PREFSIZE);
        myPenStyle = options.indexOf(getResourceLoader().getString(PEN_DEFAULT));
    }

    /**
     * Sets new line thickness
     * 
     */
    private void setLineThickness() {
        thicknessSliderLabel = new Label(getResourceLoader().getString("SliderLabel"));
        thicknessSliderLabel.setPrefWidth(COLORLABELSIZE);
        thicknessSlider = new Slider(0, SLIDER, 1);
        thicknessSlider.setShowTickLabels(true);
        thicknessSlider.setMajorTickUnit(TICK_UNIT);
        thicknessSlider.setBlockIncrement(BLOCK_INCREMENT);
        thicknessSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                myPenWidth = (double) new_val;
            }
        });
        thicknessSliderHb = new HBox();
        thicknessSliderHb.getChildren().addAll(thicknessSliderLabel, thicknessSlider);
        thicknessSliderHb.setPrefSize(PREFSIZE, PREFSIZE);
    }

    /**
     * Updates penDown boolean value
     * 
     */
    private void setPenDown() {
        switchHb = new HBox();
        switchLabel = new Label(getResourceLoader().getString("SwitchLabel"));
        switchButton = new SLogoCustomizerToggleSwitch();
        switchHb.setAlignment(Pos.CENTER);
        switchHb.getChildren().addAll(switchLabel, switchButton);
        switchHb.setPrefSize(PREFSIZE, PREFSIZE);
    }

    /**
     * Set button that applies changes to the current working environment
     * 
     */
    private void setButton() throws SLogoException {
        buttonHb = new HBox();
        myOkayButton = new Button(getResourceLoader().getString("OkayButton"));
        buttonHb.setAlignment(Pos.CENTER);
        buttonHb.getChildren().add(myOkayButton);
        buttonHb.setPrefSize(PREFSIZE, PREFSIZE);
        myOkayButton.setOnMouseClicked(e -> {
            myStrokeStyle = comboBox.getSelectionModel().getSelectedItem().toString();
            try {
                myGUI.run(new CommandNameLoader().getString("setbg") + " " 
                        + data.indexOf(myPaneColor));
                myGUI.run(myCommandNameLoader.getString("setpc") + " " 
                        + data.indexOf(myPenColor));
                myGUI.run(myCommandNameLoader.getString("setpensize") + " " 
                        + myPenWidth);
                if (switchButton.isDown()) {
                	myGUI.run(myCommandNameLoader.getString("pd"));
                } else {
                	myGUI.run(myCommandNameLoader.getString("pu"));
                }                
        }
            catch (SLogoException e1) {
                e1.showErrorDialog(getErrorLoader().getString("ButtonError"));
            }
            myCustomizerStage.hide();
        });
    }

    /**
     * Changes stroke type when given an Line object
     * 
     * @param myLine
     */
    public void changeStroke(Line myLine) {
    	if (comboBox.getValue().toString().equals(DOT)) {
            myLine.getStrokeDashArray().addAll(DOTTED_LINE);
    	} else if (comboBox.getValue().toString().equals(DASH)) {
    		myLine.getStrokeDashArray().addAll(DASHED_LINE, DASHED_SPACE);
    	} else {
    		myLine.getStrokeDashArray().addAll(SOLID_LINE);
    	}
    }

    /**
     * @return the myColor
     */
    public Color getMyPaneColor() {
        return myPaneColor;
    }

    /**
     * @param myColor the myColor to set
     */
    public void setMyPaneColor(Color myColor) {
        this.myPaneColor = myColor;
    }
    /**
     * @return the myPenColor
     */
    public Color getMyPenColor() {
        return myPenColor;
    }

    /**
     * @param myPenColor the myPenColor to set
     */
    public void setMyPenColor(Color myPenColor) {
        this.myPenColor = myPenColor;
    }

    /**
     * @return the myPenWidth
     */
    public double getMyPenWidth() {
        return myPenWidth;
    }

    /**
     * @param myPenWidth the myPenWidth to set
     */
    public void setMyPenWidth(double myPenWidth) {
        this.myPenWidth = myPenWidth;
    }

    /**
     * @return the myStrokeStyle
     */
    public String getMyStrokeStyle() {
        return myStrokeStyle;
    }

    /**
     * @param myStrokeStyle the myStrokeStyle to set
     */
    public void setMyStrokeStyle(String myStrokeStyle) {
        this.myStrokeStyle = myStrokeStyle;
    }

    /**
     * @return the isDown
     */
    public boolean isDown() {
        return isDown;
    }

    /**
     * @param isDown the isDown to set
     */
    public void setDown(boolean isDown) {
        this.isDown = isDown;
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
}