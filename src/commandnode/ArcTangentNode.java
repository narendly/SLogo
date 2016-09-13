package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of ArcTan, Atan commands
 */
public class ArcTangentNode extends UnaryNode {

    /**
     * @return ArcTangent evaluation of radians converted from degrees
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return Math.atan(Math.toRadians(evaluateChild(0, state)));
    }

}