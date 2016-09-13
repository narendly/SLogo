package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import commandnode.Node;
import exception.SLogoException;
import model.ResourceLoader;
import model.SLogoWorkspace;

/**
 * SLogoParser reads in command, formats into command parts, then creates corresponding tree(s)
 * Reads in command from MainModel's readCommand method
 * Roots of created trees evaluated and corresponding display information updated
 * @author Adam Tache
 *
 */
public class SLogoParser {

    private TreeFactory myTreeFactory;

    public SLogoParser(SLogoWorkspace workspace) throws SLogoException {
        myTreeFactory = new TreeFactory(workspace);
    }

    /**
     * Reads a command and feeds it into TreeFactory and gets a tree built
     * 
     */
    public List<Node> readCommand(String command) throws SLogoException {
        if (invalidInputCheck(command)) {
            throw new SLogoException(new ResourceLoader().getString("NoCommandEntered"));
        }
        List<String> commandParts = formatCommandParts(command);
        return myTreeFactory.createRoots(commandParts);
    }

    /**
     * Using RegEx, parses string
     * 
     */
    private List<String> formatCommandParts(String text) throws SLogoException {
        return Arrays.stream(text.split("\\s+")).map(String::toLowerCase)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Checks for invalid input
     * 
     */
    private boolean invalidInputCheck (String command) {
        return command.isEmpty() || command == null;
    }
}