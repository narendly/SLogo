package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class StampNode extends StampingNode{
	
    /**
     * Sets number of children to zero
     * 
     */
	public StampNode(){
		setNumChildren(0);
	}

    /**
     * Creates a stamp using TurtleCommand's createStampTurtle method, called from StampingNode
     * 
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
    	super.createStampTurtle(state);
        return 0;
    }

}