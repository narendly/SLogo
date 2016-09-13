package commandnode;

/**
 * @author Adam Tache
 * Node representation of command with three children
 */
public abstract class TernaryNode extends CommandNode{

    private static final int NUM_CHILDREN = 3;

    public TernaryNode(){
        setNumChildren(NUM_CHILDREN);
    }

}