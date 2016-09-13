package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of XCor command
 */
public class XCoordinateNode extends NullaryNode{

    /**
     * @param state
     * @return turtle current X coordinate from center (0,0)
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException{
        return state.getXCoor();
    }

}