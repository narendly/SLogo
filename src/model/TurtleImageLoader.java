package model;

import java.io.File;
import java.util.HashMap;

import exception.SLogoException;

/**
 * Class that allows access to Turtle images for commands
 * 
 * @author Adam Tache
 *
 */
public class TurtleImageLoader extends FileLoader{

    private static final String TURTLE_PATH = "resources/resources";
    private static final String TURTLE_EXTENSION = "TurtleImages.resources";
    private static final String IMAGE_PATH = "resources/turtle_images/";
    private HashMap<Integer, String> myTurtleMap;

    public TurtleImageLoader() throws SLogoException{
        myTurtleMap = new HashMap<>();
        load();
        loadDefaultTurtles();
    }

    public void load() throws SLogoException {
        super.load(TURTLE_PATH, TURTLE_EXTENSION);
    }

    private void loadDefaultTurtles() throws SLogoException{
        int numDefaultTurtles = countDefaultImages();
        for(int x=0; x<numDefaultTurtles; x++){
            String turtleFile = getString(x+"");
            myTurtleMap.put(x, turtleFile);
        }
    }

    private int countDefaultImages(){
        File turtle_images = new File(IMAGE_PATH);
        int numDefaultTurtles = 0;
        for (File file: turtle_images.listFiles()) {
            if(file.isFile()) {
                numDefaultTurtles++;
            }
        }
        return numDefaultTurtles;
    }

}