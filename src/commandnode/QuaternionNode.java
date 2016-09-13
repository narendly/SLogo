package commandnode;

/**
 * @author Adam Tache
 * Node representation of command with four children
 */
public abstract class QuaternionNode extends CommandNode{

    private static final int NUM_CHILDREN = 4;

    public QuaternionNode() {
        setNumChildren(NUM_CHILDREN);
    }

}