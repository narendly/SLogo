package parser;

import exception.SLogoException;

/**
 * InstructionLoader allows access to command instructions
 * 
 * @author Adam Tache
 *
 */
public class InstructionLoader extends CommandLoader{

    private static final String COMMAND_EXTENSION = "instructions.resources";

    public String getString(String key) throws SLogoException{
        super.load(super.getPath(), COMMAND_EXTENSION);
        return super.getString(key);
    }

}