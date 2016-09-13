package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class ClearStampsNode extends TurtleCommand{

    /**
     * Sets number of children to zero
     * 
     */
	public ClearStampsNode() {
		setNumChildren(0);
	}

    /**
     * Clears a stamp using clearStampTurtles method in TurtleCommand, called from StampingNode
     * 
     */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		super.clearStampTurtles();
		return 0;
	}

}
