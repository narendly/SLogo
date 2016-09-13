package commandnode;

/**
 * Abstract class that serves as a superclass for variable nodes
 * requiring three children
 *
 */
public abstract class TernaryVariableNode extends VariableCommand{

    private static final int NUM_CHILDREN = 3;

    public TernaryVariableNode(){
        setNumChildren(NUM_CHILDREN);
    }

}