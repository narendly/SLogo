package view;

import java.util.Observable;

import javafx.scene.paint.Color;

public class SLogoPenData extends Observable{

    private Color myPenColor;
    private double myPenWidth;
    private boolean isDown;
    private String myStrokeStyle;

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
}
