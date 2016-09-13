package commandnode;

public abstract class StampingNode extends TurtleCommand{

	private int NUM_CHILDREN = 0;
	
	/**
	 * Sets stamp node to have zero children
	 */
	public StampingNode(){
		setNumChildren(NUM_CHILDREN);
	}
	
}