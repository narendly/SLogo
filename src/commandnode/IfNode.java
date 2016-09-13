package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of If command
 */
public class IfNode extends BinaryNode {

	private final static int COMMAND_LIST_INDEX= 1;
	
    /**
     * @param state
     * Checks evaluation of 0th child (NumericNode) for 0
     * If not 0, runs true commands, represented as child 1 as a ListCommand
     * @return value of final command executed or 0 if none executed
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
    	childListCheck(COMMAND_LIST_INDEX);
    	return evaluateChild(0, state) != 0 ? evaluateChild(COMMAND_LIST_INDEX, state) : 0;
    }

}