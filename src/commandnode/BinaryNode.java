package commandnode;

/**
 * @author Adam Tache
 * Node representation of command with two children
 */
public abstract class BinaryNode extends CommandNode{

    private static final int NUM_CHILDREN = 2;

    public BinaryNode(){
        setNumChildren(NUM_CHILDREN);
    }

}