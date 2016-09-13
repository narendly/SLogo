package commandnode;

/**
 * @author Adam Tache
 * Node representation of command with zero children
 */
public abstract class NullaryNode extends CommandNode{
    private int NUM_CHILDREN = 0;

    public NullaryNode() {
        setNumChildren(NUM_CHILDREN);
    }
}