package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Not command, a Math logic command
 */
public class NotNode extends UnaryNode{

    /**
     * @param state
     * @return 1 if child's evaluation is 0, 0 otherwise
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return evaluateChild(0, state) == 0 ? 1 : 0;
    }
}