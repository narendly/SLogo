package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Power command, a Math command
 */
public class PowerNode extends BinaryNode{

    /**
     * Raises base (child 0) to exponent (child 1)
     * @return base^child value
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        double base = evaluateChild(0, state);
        double exponent = evaluateChild(1, state);
        return Math.pow(base, exponent);
    }
}