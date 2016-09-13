package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Adds a new color (r, g, b) to list of colors
 */

public class SetPaletteNode extends QuaternionNode{

    private static final int RED = 1;
    private static final int GREEN = 2;
    private static final int BLUE = 3;
    
    private RGBColor myColorNode;
    
    /**
     * @param state
     * @return index of new color
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        int index = (int) evaluateChild(0, state);
        int red = (int) evaluateChild(RED, state);
        int green = (int) evaluateChild(GREEN, state);
        int blue = (int) evaluateChild(BLUE, state);
        myColorNode = new RGBColor(red, green, blue, index);
        return index;
    }

    /**
     * @return the colorNode
     */
    public RGBColor getColorNode () {
        return myColorNode;
    }

}