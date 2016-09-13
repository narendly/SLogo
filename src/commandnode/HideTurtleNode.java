package commandnode;

import model.SLogoCharacterState;

/**
 * Node representation of HideTurtle, Ht commands
 */
public class HideTurtleNode extends NullaryNode{

    /**
     * @param state
     * Hides turtle
     * @return 0 representing turtle is hidden
     */
    public double evaluate(SLogoCharacterState state) {
        state.setHidden(true);
        return 0;
    }

}