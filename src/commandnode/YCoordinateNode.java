package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of YCor command
 */
public class YCoordinateNode extends NullaryNode{

    /**
     * @param state
     * @return turtle current Y coordinate from center (0,0)
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException{
        return state.getYCoor();
    }

}