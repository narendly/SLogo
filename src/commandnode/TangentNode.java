package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Tan, Tangent commands
 */
public class TangentNode extends UnaryNode{

    /**
     * @return tangent evaluation of radians converted from degrees
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return Math.tan(Math.toRadians(evaluateChild(0, state)));
    }

}