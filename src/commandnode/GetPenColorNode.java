package commandnode;

import model.SLogoCharacterState;

/**
 * Node representation of PenColor command, a Display command
 */
public class GetPenColorNode extends NullaryNode {

    /**
     * @return current turtle's pen color index
     */
    public double evaluate(SLogoCharacterState state) {
        return state.getPen().getColorIndex();
    }

}