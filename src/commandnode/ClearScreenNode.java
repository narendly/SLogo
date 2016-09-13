package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * @author Adam Tache
 * Node representation of ClearScreen command
 */
public class ClearScreenNode extends NullaryNode{

    /**
     * @return distance to home
     * Sets state to be cleared on next display update
     * Creates and evaluates HomeNode to send turtle to (0,0)
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        state.queueLineClearing(true);
        return new HomeNode().evaluate(state);
    }

}