package commandnode;

import model.SLogoCharacterState;

/**
 * Returns the index of the turtle's current shape
 */

public class GetShapeNode extends NullaryNode {

    public double evaluate(SLogoCharacterState state) {
        return state.getShapeIndex();
    }
}
