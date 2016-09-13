package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Abstract class to be extended by Equal and NotEqual nodes
 */
public abstract class EqualityNode extends BinaryNode{

    /**
     * @param state
     * @return true if children evaluations equal
     */
    public boolean isEqual(SLogoCharacterState state) throws SLogoException{
        return evaluateChild(0, state) == evaluateChild(1, state) ? true : false;
    }

}