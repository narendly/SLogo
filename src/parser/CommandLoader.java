package parser;
import model.FileLoader;

/**
 * CommandLoader initiates file loading from commands resources folder
 * 
 * @author Adam Tache
 *
 */
public class CommandLoader extends FileLoader {

    private static final String COMMAND_PATH = "resources/commands";

    public String getPath(){
        return COMMAND_PATH;
    }

}