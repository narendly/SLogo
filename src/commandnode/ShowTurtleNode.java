package commandnode;

import model.SLogoCharacterState;

/**
 * Node representation of ShowTurtle, St commands
 */
public class ShowTurtleNode extends NullaryNode {

    /**
     * @param state
     * Shows turtle
     * @return 1 representing turtle is shown
     */
    public double evaluate(SLogoCharacterState state) {
        state.setHidden(false);
        return 1;
    }

}