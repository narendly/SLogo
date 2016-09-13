package view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import exception.SLogoException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.Model;
import model.ResourceLoader;
import model.SLogoCustomCommand;
import model.SLogoPosition;
import model.SLogoVariable;
import model.SLogoDisplayData;

/**
 * Visualizer class that contains methods that renders turtles.
 * on the screen to show the user
 * Also exists to support multiple workspaces
 * 
 * @author Hunter
 */
public class SLogoVisualizer implements Observer {

	private static final String IMAGE_PATH = "file:resources/turtle_images/";
	private static final int PANE_SIZE = 440;
	private static final int RGB_CONST = 255;
	private static final double COORDINATE_SHIFT = (double) PANE_SIZE / 2;
	private static final int DIRECTION_FLIP = -1;
	private static final int TURTLE_SIZE = 40;
	private static final int TURTLE_CLICKED_SIZE = 55;
	private static final int PADDING = TURTLE_SIZE / 2;
	private static final int HALF_FACTOR = 2;
	private static final float DEFAULT_STROKE = 1.0f;

	private ObservableList<SLogoDisplayData> myObservableDataList;

	private FXMLLoader myLoader;
	private SLogoGUIController myGUIController;
	private SLogoPromptBuilder myPromptBuilder;
	private Parent root;

	private Scene myScene;
	private Stage myStage;
	private Model myModel;
	private String myCanvasColor;
	private SLogoPropertiesData myProperties = new SLogoPropertiesData();

	/**
	 * Default constructor
	 * 
	 * @param model
	 */
	public SLogoVisualizer(Model model) {
		myModel = model;
	}

	/**
	 * Initialize method that creates a Loader objects and loads FXML file
	 * created by SceneBuilder
	 * 
	 * @throws SLogoException
	 */
	public void initialize() throws SLogoException {
		myPromptBuilder = new SLogoPromptBuilder(myProperties);
		myPromptBuilder.promptScreen();
		setCanvasColor(toRGBCode(myPromptBuilder.sendMyColor()));
		myLoader = new FXMLLoader(getClass().getResource("UI.fxml"));
		try {
			root = (Parent) myLoader.load();
		}
		catch (IOException e) {
		}
		myGUIController = (SLogoGUIController) myLoader.getController();
		myGUIController.setModel(myModel);
		myGUIController.setPropertiesData(myProperties);
		myScene = new Scene(root);
		myStage = new Stage();
		myStage.setScene(myScene);
		myStage.setTitle(new ResourceLoader().getString("Title"));
		show();
	}

	/**
	 * Shows the current stage
	 * 
	 */
	public void show() {
		getStage().show();
	}

	/**
	 * Hides the current stage
	 * 
	 */
	public void hide () {
		getStage().hide();

	}

	/**
	 * Implements Observable interface's update
	 * 
	 */
	@Override
	public void update(Observable observable, Object arg1) {
		updateDisplayData();
	}

	/**
	 * Creates a Line object with default color black
	 * 
	 * @param position
	 * @return Line
	 */
	public Line createLine(SLogoPosition position) {
		Line newLine = new Line();
		newLine.setStartX(position.getPrevX() + COORDINATE_SHIFT);
		newLine.setStartY(DIRECTION_FLIP * position.getPrevY() + COORDINATE_SHIFT);
		newLine.setEndX(position.getX() + COORDINATE_SHIFT);
		newLine.setEndY(DIRECTION_FLIP * position.getY() + COORDINATE_SHIFT);
		newLine.setStrokeWidth(DEFAULT_STROKE);
		newLine.setStroke(myGUIController.getCustomizer().getMyPenColor());
		myGUIController.getCustomizer().changeStroke(newLine);
		return newLine;
	}

	/**
	 * Creates a Line object with specified color
	 * 
	 * @param position
	 * @param color
	 * @return Line
	 */
	public Line createLine(SLogoDisplayData turtledata) {
		Line newLine = createLine(turtledata.getPosition());
		newLine.setFill(turtledata.getPen().getColor());
		newLine.setStrokeWidth(turtledata.getPen().getSize());
		if(!turtledata.getPen().getDown() || turtledata.areLinesCleared()){
			newLine.setStrokeWidth(0);
			if(turtledata.areLinesCleared()) {
				turtledata.queueLineClearing(false);
			}
		}
		return newLine;
	}

	/**
	 * This method updates turtles' attributes and position
	 * Caller is Workspace (MyCurrentWorkspace in MainModel)
	 */
	public void updateDisplayData () {
		getGUIController().getCanvas().getChildren().clear();
		for (SLogoDisplayData turtledata : getModel().getObservableDataList()) {
			placeTurtle(turtledata);
			if(turtledata.getID() != Integer.parseInt(new ResourceLoader().getString("StampID"))){
				turtledata.addLine(createLine(turtledata));
				getGUIController().addToCanvas(turtledata.getLines());
			}
			getGUIController().updateProperties(turtledata);
			myProperties.setPaneColor(turtledata.getBGColor());
		}
	}

	/**
	 * Places a turtle on canvas
	 * 
	 * @param displaydata
	 */
	public void placeTurtle(SLogoDisplayData displaydata) {
		ImageView turtle = new ImageView();
		turtle.setImage(new Image(IMAGE_PATH + displaydata.getImage()));
		turtle.setVisible(!displaydata.getTurtleHidden());
		assignTurtleAction(turtle);
		turtleResize(turtle);
		turtle.setLayoutX(displaydata.getX() + COORDINATE_SHIFT - PADDING);
		turtle.setLayoutY(DIRECTION_FLIP * displaydata.getY() 
				+ COORDINATE_SHIFT - PADDING);
		turtle.setRotate(DIRECTION_FLIP * displaydata.getPrevDirection());
		turtle.setRotate(displaydata.getDirection());
		getGUIController().addToCanvas(turtle);
	}

	/**
	 * Assigns an click action to the turtle ImageView
	 * 
	 * @param turtle
	 */
	private void assignTurtleAction(ImageView turtle) {
		turtle.setOnMouseClicked(e -> {
			turtle.setFitWidth(TURTLE_CLICKED_SIZE);
			turtle.setLayoutX(turtle.getLayoutX() - 
					(TURTLE_CLICKED_SIZE - TURTLE_SIZE) / HALF_FACTOR);
			turtle.setLayoutY(turtle.getLayoutY() - 
					(TURTLE_CLICKED_SIZE - TURTLE_SIZE) / HALF_FACTOR);
			// Show Properties
			getGUIController().displayProperties();
		});
	}

	/**
	 * Takes an ImageView and resizes it
	 * 
	 * @param turtle
	 */
	private void turtleResize(ImageView turtle) {
		turtle.setFitWidth(TURTLE_SIZE);
		turtle.setPreserveRatio(true);
		turtle.setSmooth(true);
		turtle.setCache(true);
	}

	/**
	 * Updates the list of custom commands shown in GUI
	 * 
	 * @param customs
	 */
	public void updateCustomCommands (ObservableList<SLogoCustomCommand> customs) {
		getGUIController().displayCustomCommands(customs);
	}

	/**
	 * Updates the list of variables shown in GUI
	 * 
	 * @param variables
	 */
	public void updateVariables (ObservableList<SLogoVariable> variables) {
		getGUIController().displayVariables(variables);
	}

	/**
	 * Converts Color object into its hex String representation
	 * 
	 * @param color
	 * @return String
	 */
	public String toRGBCode (Color color) {
		return String.format( "#%02X%02X%02X",
				(int) (color.getRed() * RGB_CONST),
				(int) (color.getGreen() * RGB_CONST),
				(int) (color.getBlue() * RGB_CONST));
	}

	/**
	 * @return the myGUIController
	 */
	public SLogoGUIController getGUIController() {
		return myGUIController;
	}

	/**
	 * @return the myModel
	 */
	public Model getModel() {
		return myModel;
	}

	/**
	 * @return the myStage
	 */
	public Stage getStage() {
		return myStage;
	}

	/**
	 * @return the myObservableDataList
	 */
	public ObservableList<SLogoDisplayData> getObservableDataList() {
		return myObservableDataList;
	}

	/**
	 * @param myObservableDataList the myObservableDataList to set
	 */
	public void setObservableDataList(ObservableList<SLogoDisplayData> 
	myObservableDataList) {
		this.myObservableDataList = myObservableDataList;
	}

	/**
	 * @return the myCanvasColor
	 */
	public String getCanvasColor() {
		return myCanvasColor;
	}

	/**
	 * @param myCanvasColor the myCanvasColor to set
	 */
	public void setCanvasColor(String myCanvasColor) {
		this.myCanvasColor = myCanvasColor;
	}

	public SLogoPropertiesData getPropertiesData() {
		return myProperties;
	}

	public String getLanguage() {
		return myPromptBuilder.getWorkspaceLanguage();
	}

}