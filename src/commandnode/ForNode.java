package commandnode;

import exception.SLogoException;
import model.ResourceLoader;
import model.SLogoCharacterState;
import model.SLogoVariable;

/**
 * Node representation of For command, a Control Structure command using variable
 */
public class ForNode extends BinaryVariableNode {

	private final static int CONTROL_LIST_INDEX = 0;
	private final static int COMMAND_LIST_INDEX= 1;
	private final static int VARIABLE_INDEX = 0;
	private final static int START_INDEX = 1;
	private final static int END_INDEX = 2;
	private final static int INCREMENT_INDEX = 3;

	/**
	 * Children are two ListNode children
	 * Child one is a ListNode with four values, variable, start, end, and increment
	 * Child two is a ListNode with commands run for each value specified in range by a for-loop
	 * @return value of final command in list executed
	 * Loop runs from start to end inclusive using increment between each run
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		childListCheck(CONTROL_LIST_INDEX);
		childListCheck(COMMAND_LIST_INDEX);
		ListNode controlList = ((ListNode) (getChildren().get(CONTROL_LIST_INDEX)));
		Node startNode = controlList.getInnerRoots().get(START_INDEX);
		Node endNode = controlList.getInnerRoots().get(END_INDEX);
		Node incrementNode = controlList.getInnerRoots().get(INCREMENT_INDEX);
		if(!(startNode instanceof NumericNode) || !(endNode instanceof NumericNode) || !(incrementNode instanceof NumericNode)){
			throw new SLogoException(new ResourceLoader().getString("NumericError"));
		}
		int start = (int) Math.floor(startNode.evaluate(state));
		int end = (int) Math.floor(endNode.evaluate(state));
		int increment = (int) Math.floor(incrementNode.evaluate(state));
		SLogoVariable var = getWorkspace().createVariable(controlList.getInnerCommands().get(VARIABLE_INDEX), start);
		ListNode commandList = ((ListNode) (getChildren().get(COMMAND_LIST_INDEX)));
		double evaluation = 0;
		for(int x = start; x <= end; x += increment){
			evaluation = commandList.evaluate(state);
			var.setValue(x);
		}
		return evaluation;
	}

}