package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;


/**
 * Node representation of IfElse command
 */
public class IfElseNode extends TernaryNode {

    private static final int TRUE_COMMANDS = 1;
    private static final int FALSE_COMMANDS = 2;
    
    /**
     * @param state
     * Checks evaluation of 0th child (NumericNode) for 0
     * If not 0, runs true commands, represented as child 1 as a ListCommand
     * Otherwise runs false commands, represented as child 2 as a ListCommand
     * @return value of final command executed or 0 if none executed
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
    	childListCheck(TRUE_COMMANDS);
    	childListCheck(FALSE_COMMANDS);
    	return evaluateChild(0, state) != 0 ? evaluateChild(TRUE_COMMANDS, state) : 
                                                    evaluateChild(FALSE_COMMANDS, state);
    }

}