package commandnode;

import model.SLogoCharacterState;

/**
 * Node representation of PenDown command, Turtle Command
 */
public class PenDownNode extends NullaryNode{

    /**
     * Sets Turtle pen down, leaving trails
     * @return 1 representing pen on
     */
    public double evaluate(SLogoCharacterState state) {
        state.getPen().setPenDown(true);
        return 1;
    }
}