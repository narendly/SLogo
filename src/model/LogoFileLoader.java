/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reads pre-defined .logo files
 * Currently only able to read in files with one "to" custom commands
 * 
 * @author Hunter
 *
 */
public class LogoFileLoader extends FileLoader {
    
    private String myLogoFile;
    private List<String> myCustomCommands;
    
    /**
     * Default constructor that sets the path & filename
     * 
     * @param filename
     */
    public LogoFileLoader (String filename) {
        myLogoFile = filename;
        myCustomCommands = new ArrayList<>();
    }
    
    /**
     * Converts the contents of the file to a big String object
     * so that it could be passed to the parser
     * 
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String createString () throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(myLogoFile))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                stringAdder(sb, line);
                line = br.readLine();
            }
            return sb.toString();
        }
    }
    
    /**
     * Used in conjunction with createString
     * Adds each word to the entire string
     * 
     * @param sb
     * @param line
     */
    private void stringAdder (StringBuilder sb, String line) {
        List<String> words = new ArrayList<>(Arrays.asList(line.split("\\s+")));
        words.forEach(word -> sb.append(word + " "));        
    }
    
    /**
     * Detect custom commands that start with "to" and run them prior to
     * execution of the entire command list to avoid error
     * 
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<String> registerCustomCommands () throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(myLogoFile))) {
            StringBuilder sb = new StringBuilder();
            boolean sawTo = false;
            List<String> toCommands = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                if (!line.startsWith("#")) {
                    List<String> words = new ArrayList<>(Arrays.asList(line.split("\\s+")));
                    for (String word : words) {
                        if (sawTo) {
                            sb.append(word + " ");
                            toCommands.add(word);
                            sawTo = false;
                        } else if (toCommands.contains(word)) {
                            continue;
                        } else if (word.equals("to")) {
                            sb.append(word + " ");
                            sawTo = true;
                        } else {
                            sb.append(word + " ");
                        }
                    }
                }
                line = br.readLine();
            }
            myCustomCommands.add(sb.toString());
            myCustomCommands.addAll(toCommands);
            return myCustomCommands;
    }}

    /**
     * @return the myLogoFile
     */
    public String getLogoFile () {
        return myLogoFile;
    }

    /**
     * @return the myCustomCommands
     */
    public List<String> getCustomCommands () {
        return myCustomCommands;
    }
    
    /**
     * Returns a list of commands consisting of custom commands and
     * the entire String appended at the end
     * 
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public List<String> getAllCommands () throws IOException {
        myCustomCommands = registerCustomCommands();
        return getCustomCommands();
    }

    /**
     * For unit testing only
     * 
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        LogoFileLoader l = new LogoFileLoader("examples/simple/forward.logo");
        for (String s : l.registerCustomCommands()) {
            System.out.println(s);
        }
    }
}
