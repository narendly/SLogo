package commandnode;

/**
 * @author Adam Tache
 * Node representation of command with one child
 */
public abstract class UnaryNode extends CommandNode{

    private int NUM_CHILDREN = 1;

    public UnaryNode(){
        setNumChildren(NUM_CHILDREN);
    }

}