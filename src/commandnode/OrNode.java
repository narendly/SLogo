package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Or command, a Math logic command
 */
public class OrNode extends BinaryNode {

    /**
     * @param state
     * @return 1 if children are non-zero, otherwise 0
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return evaluateChild(0, state) != 0 || evaluateChild(1, state) != 0 ? 1 : 0;
    }
}