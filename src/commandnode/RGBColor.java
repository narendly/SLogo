package commandnode;

import java.util.Optional;

import exception.SLogoException;
import parser.InstructionLoader;

/**
 * Node representation of RGB Value with bounds error checking, used for SetPalette creation
 */
public class RGBColor {

    private static final int NUM_ARGUMENTS = 3;
    private static final int MAX_VAL = 255;
    private static final int MIN_VAL = 0;
    private static final int RED_INDEX = 0;
    private static final int GREEN_INDEX = 1;
    private static final int BLUE_INDEX = 2;
    private int red;
    private int green;
    private int blue;
    private int colorIndex;
    
    /**
     * @param red
     * @param green
     * @param blue
     * @param colorIndex in color map
     * @throws SLogoException for correcting values
     */
    public RGBColor(int red, int green, int blue, int colorIndex) throws SLogoException{
        boundsCheck(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.colorIndex = colorIndex;
    }

    public int getR(){
        return red;
    }

    public int getG(){
        return green;
    }

    public int getB(){
        return blue;
    }

    public int getIndex(){
        return colorIndex;
    }

    /**
     * @param red
     * @param green
     * @param blue
     * @return true when input RGB values fit range 0-255
     * @throws SLogoException for correcting values by user
     * Uses myNewInput, user input grabbed from SLogoException typed by user in text popup
     */
    public boolean boundsCheck(int red, int green, int blue) throws SLogoException {
        InstructionLoader instructLoader = new InstructionLoader();
        if ((red < MIN_VAL || green < MIN_VAL || blue < MIN_VAL || red > MAX_VAL 
                || green > MAX_VAL || blue > MAX_VAL)) {
            SLogoException e = new SLogoException(instructLoader
                                             .getString("RGBColor"), NUM_ARGUMENTS);
            Optional<String> myNewInput = e.getNewInput();
            checkInput(myNewInput);
        }
        return true;
    }

    /**
     * @param Unparsed user input (in form "R G B")
     * @return integer array {R, G, B}
     */
    public int[] grabRGB(String input) {
        String[] rgbStr = input.split(" ");
        return new int[] { Integer.parseInt(rgbStr[RED_INDEX]), 
                           Integer.parseInt(rgbStr[GREEN_INDEX]), 
                           Integer.parseInt(rgbStr[BLUE_INDEX])};
    }

    /**
     * @param myNewInput, user input entered in SLogoException text popup
     * Calls boundsCheck to ensure proper user input
     * @throws SLogoException
     */
    public void checkInput(Optional<String> myNewInput) throws SLogoException {
        int[] rgb = grabRGB(myNewInput.get());
        boundsCheck(rgb[RED_INDEX], rgb[GREEN_INDEX], rgb[BLUE_INDEX]);
    }

}