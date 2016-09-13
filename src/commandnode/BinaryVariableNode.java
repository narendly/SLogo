package commandnode;

/**
 * Abstract class that serves as a superclass for nodes
 * requiring two children
 *
 */
public abstract class BinaryVariableNode extends VariableCommand{

    private static final int NUM_CHILDREN = 2;

    public BinaryVariableNode(){
        setNumChildren(NUM_CHILDREN);
    }

}