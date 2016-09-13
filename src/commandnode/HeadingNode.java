package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Heading command
 */
public class HeadingNode extends NullaryNode {

    /**
     * @param state
     * @return degrees turtle is heading
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return state.getDirection();
    }
}