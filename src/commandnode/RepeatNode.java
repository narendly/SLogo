package commandnode;
import exception.SLogoException;
import model.ResourceLoader;
import model.SLogoCharacterState;
import model.SLogoVariable;

/**
 * Node representation of Repeat command, a Control Structure command using variable
 */
public class RepeatNode extends BinaryVariableNode {

	private final static int COMMAND_LIST_INDEX= 1;
	
	/**
	 * Repeats commands in list expr number of times where expr is evaluation of child 0
	 * Child one is a ListNode, a list of commands in [ ]
	 * @return value of final command in list executed
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		childListCheck(COMMAND_LIST_INDEX);
		int repcount = (int) evaluateChild(0, state);
		SLogoVariable repcountVar = getWorkspace().createVariable(new ResourceLoader().getString("Repcount"), 1);
		ListNode listNode = ((ListNode) (getChildren().get(COMMAND_LIST_INDEX)));
		double evaluation = 0;
		for(int x=1; x<=repcount; x++){
			evaluation = listNode.evaluate(state);
			repcountVar.setValue(x);
		}
		return evaluation;
	}

}