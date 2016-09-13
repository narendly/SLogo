package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Turtle ID command
 */
public class IDNode extends NullaryNode{

    /**
     * @param state
     * @return ID of active turtle
     */

    public IDNode() {
    }

    @Override
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return state.getID();
    }

}
