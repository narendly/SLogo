package model;

import exception.SLogoException;
import javafx.scene.paint.Color;

/**
 * SLogo's highest State in hierarchy, to support multiple types of character
 * states
 *
 */
public abstract class SLogoCharacterState {

    private boolean isHidden;
    private boolean toClear;
    private int myID;
    private int myShapeIndex;
    private int myBGColorIndex;
    private double myDirection;
    private SLogoPosition myPosition;
    private SLogoPen myPen;
    private String myImage;
    private Color myBGColor;
    private ResourceLoader resourceLoader = new ResourceLoader();

    /**
     * Default constructor that takes in essential turtle variable fields
     * such as Pen, Coordinates, direction, and boolean predicates
     * 
     * @param myPen
     * @param xCoor
     * @param yCoor
     * @param direction
     * @param isHidden
     * @param shapeIndex
     * @throws SLogoException
     */
    public SLogoCharacterState(int ID, SLogoPen myPen, double xCoor, double yCoor, double direction,
                               boolean isHidden, int shapeIndex) throws SLogoException {
    	this.myID = ID;
        this.myPen = myPen;
        this.myPosition = new SLogoPosition(xCoor, yCoor);
        this.myDirection = direction;
        this.isHidden = isHidden;
        this.myShapeIndex = shapeIndex;
        setImage(myShapeIndex);
        setBackground(myBGColorIndex);
    }

    public SLogoPen getPen() {
        return myPen;
    }

    public void setHidden(boolean hide) {
        this.isHidden = hide;
    }

    public boolean getHidden() {
        return isHidden;
    }

    public void setXCoor(double xCoor) {
        this.myPosition.setX(xCoor);
    }

    public void setYCoor(double yCoor) {
        this.myPosition.setY(yCoor);
    }

    public double getXCoor() {
        return this.myPosition.getX();
    }

    public double getYCoor() {
        return this.myPosition.getY();
    }

    public double getDirection() {
        return myDirection;
    }

    public void setDirection(double direction) {
        this.myDirection = direction;
    }

    public String getImage() {
        return myImage;
    }

    /**
     * @param myBGColorIndex: index of new background color
     * Sets background color 
     */

    public void setBackground(int myBGColorIndex) throws SLogoException {
        ColorLoader colorLoader = new ColorLoader();
        String colorString = colorLoader.getString(myBGColorIndex + "");
        if (colorString == null) {
            throw new SLogoException(resourceLoader.getString("ColorIndex"));
        }
        else {
            this.myBGColor = colorLoader.getColor(myBGColorIndex);
        }
    }

    public Color getBGColor(){
        return myBGColor;
    }

    /**
     * @param myShapeIndex: index of new shape
     * Updates representation of turtle in GUI
     */

    public void setImage(int myShapeIndex) throws SLogoException {
        TurtleImageLoader imageLoader = new TurtleImageLoader();
        String indexImage = imageLoader.getString(myShapeIndex + "");
        if (indexImage == null) {
            throw new SLogoException(resourceLoader.getString("ShapeIndex"));
        }
        else {
            this.myImage = indexImage;
        }
    }

    public int getID(){
        return myID;
    }

    public void setID(int myID){
        this.myID = myID;
    }

    public int getShapeIndex(){
        return myShapeIndex;
    }

    public void setShapeIndex(int myShapeIndex) throws SLogoException{
        this.myShapeIndex = myShapeIndex;
        setImage(myShapeIndex);
    }

    public boolean getCleared(){
        return toClear;
    }

    public String getPenStyle() {
        return myPen.getPenStrokeStyle();
    }

    public void queueLineClearing(boolean toClear) {
        this.toClear = toClear;
    }

}