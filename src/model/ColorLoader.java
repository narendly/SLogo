package model;

import java.util.HashMap;
import commandnode.RGBColor;
import exception.SLogoException;
import javafx.scene.paint.Color;

/**
 * Class for loading default colors from resources file for commands
 * 
 * @author Adam Tache
 */
public class ColorLoader extends FileLoader{

    private static final String COLOR_PATH = "resources/resources";
    private static final String COLOR_EXTENSION = "colors.resources";
    private static final double DEFAULT_OPACITY = 1.0;
    private static final double RGB_CONST = 255.0;
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;

    private HashMap<Integer, Color> myRGBMap;

    public ColorLoader() throws SLogoException {
        myRGBMap = new HashMap<>();
        load();
        loadDefaultColors();
    }

    /**
     * loads color resource file
     */
    public void load() throws SLogoException {
        super.load(COLOR_PATH, COLOR_EXTENSION);
    }

    /**
     * Add default colors to color map with index
     */
    private void loadDefaultColors() throws SLogoException {
        int numDefaultColors = countLines(getFileName());
        for (int x = 0; x < numDefaultColors; x++) {
            String rgbString = getString(x+"");
            String[] rgbStrs = rgbString.split(",");
            double[] rgb = {convertRGB(Integer.parseInt(rgbStrs[INDEX_ZERO])), 
                            convertRGB(Integer.parseInt(rgbStrs[INDEX_ONE])), 
                            convertRGB(Integer.parseInt(rgbStrs[INDEX_TWO]))};
            myRGBMap.put(x, new Color(rgb[INDEX_ZERO], rgb[INDEX_ONE], 
                                      rgb[INDEX_TWO], DEFAULT_OPACITY));
        }
    }

    public Color getColor(int index) throws SLogoException {
        return myRGBMap.get(index);
    }

    public Color createColor(int r, int g, int b) {
        return new Color(convertRGB(r), convertRGB(g), convertRGB(b), DEFAULT_OPACITY);
    }

    public void addRGB(RGBColor color) throws SLogoException {
        myRGBMap.put(color.getIndex(), createColor(color.getR(), color.getG(), color.getB()));
    }

    /**
     * @param value: RGB value (0:255)
     * Node representation of Repeat command, a Control Structure command using variable
     * @return Normalized RGB value (0:1)
     */

    private double convertRGB(int value) {
        return value / RGB_CONST;
    }

    public HashMap<Integer, Color> getMap() {
        return myRGBMap;
    }

}