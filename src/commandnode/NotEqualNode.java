package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Equal and = commands that extends EqualityNode
 */
public class NotEqualNode extends EqualityNode {

    /**
     * @param state
     * @return true if children evaluations not equal
     * isEqual(state) evaluated from EqualityNode superclass
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return isEqual(state) ? 0 : 1;
    }

}