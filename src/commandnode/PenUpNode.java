package commandnode;

import model.SLogoCharacterState;

/**
 * Node representation of PenUp command, Turtle Command
 */
public class PenUpNode extends NullaryNode{

    /**
     * Sets Turtle pen up, leaving no trails
     * @return 0 representing pen off
     */
    public double evaluate(SLogoCharacterState state) {
        state.getPen().setPenDown(false);
        return 0;
    }
}