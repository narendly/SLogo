API Critique - Team 18 (18FEB16)
===================
Aaron Newman
Michelle Chen
Hunter Lee
Mario Oliver
Adam Tache


Fundamentals of API Design
External/Internal
Access modifier (public, private, protected, and package)

----------
Use of Generic Return Types
-----------------------
When passing in/returning objects, APIs want to make them as generic as possible.

 - In a number of instances, we noticed that public methods return HashMap and ArrayList. A better design conforming to the principles discussed in class would be Collections.



Methods that could be hidden
-----------------------
There were a few methods that you want to keep hidden inside classes that are not to be used by outer classes/methods.

 - Under MainDriver, there were three methods that run Animation, and those methods could have been made private.


6 APIs analyzed
-------------------
1-3) public void playAnimation () method: This method runs the Animation inside the class so we feel that it would have been better private.

4-6) public void pauseAnimation () method: This method pauses the given Animation object and it would have been better if it was made private.






```java
package src.controller;
public class CellIterator implements Iterable<Cell> { 
    public CellIterator(Grid grid) 
    public Iterator<Cell> iterator() 
    public boolean hasNext() 
   	public Cell next() 
}
```
 
package src.controller;
 public interface EventListener {
  }
 
package src.controller;
public class Main extends Application { 
      public void start(Stage primaryStage) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, ClassNotFoundException, DOMException, IllegalArgumentException, IllegalAccessException c
}
 
package src.controller;
public class MainDriver implements EventListener { 
  	public MainDriver(Stage stage) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, ClassNotFoundException, DOMException, IllegalArgumentException, IllegalAccessException c
	public Simulation setSim(String simType, ArrayList<Double> paramsList, ArrayList<Integer> statesList)x
	public void playAnimation() x
	public void pauseAnimation() x
	public void stepAnimation() x
	public void onSliderMove(int newValue) x
	public void onFileSelection(File myFile) throws NoSuchFieldException, SecurityException, ClassNotFoundException, DOMException, IllegalArgumentException, IllegalAccessException, ParserConfigurationException, SAXException, IOException x
}
 
package src.controller.Simulation;
public class FireSimulation extends Simulation{ 
  	public FireSimulation(Grid grid) c
	public void setProbCatch(double prob) p
	public void setEmptyParameter(int emptyCell) p
	public void setTreeParameter(int treeCell) p
	public void setBurningParameter(int burningCell) p
	public String returnTitle() x
	public Cell updateCellState(Cell cell) i
}
 
package src.controller.Simulation;
public class GameOfLifeSimulation extends Simulation{  
  	public GameOfLifeSimulation(Grid grid) c
	public void setLiveStateParameter(int liveCell) i
	public void setDeadStateParameter(int deadCell) i
	public String returnTitle() x
	public Cell updateCellState(Cell cell) i
}
 
package src.controller.Simulation;
public class SegregationSimulation extends Simulation { 
  	public SegregationSimulation(Grid grid) c
	public void setSatisfiedPercentage(double percentage) i
	public double getSatisfiedPercentage() i
	public void setNoAgentParameter(int emptyCell) p
	public void setFirstAgentParameter(int firstAgentCell) p
	public void setSecondAgentParameter(int secondAgentCell) p
	public String returnTitle() x
	public Cell updateCellState(Cell cell) i
}
 
package src.controller.Simulation;
public abstract class Simulation { 
  	public Simulation(Grid grid) c
	public Grid getGrid() x 
	public void initialize(ArrayList<Integer> cellStates) i
	public void run() p
	public void pause() p
	public Grid step() p
	public abstract Cell updateCellState(Cell cell); i
	public String returnTitle() x
}
 
package src.controller.Simulation;
public class WaTorSimulation extends Simulation{ 	public class Fish extends Actor{ 	public class Shark extends Actor{ 
  	public String returnTitle() x
		public Fish(int x, int y, double actorEnergy, double depletionRate) c
		public Shark(int x, int y, double actorEnergy, double depletionRate) c
	public void setParameters(ArrayList<Double> params) i
	public void setChrononParameter(double time) i 
	public void setFishReproductionTimeParameter(double time) i
	public void setSharkReproductionTimeParameter(double time) i
	public void setSharkDepletionRateParameter(double rate) i
	public void setEatFishRegenerationRateParameter(double rate) i
	public void setSharkEnergyParamter(double energy) i
	public void setEmptyCellParameter(int empty) i
	public void setFishCellParameter(int fish) i
	public void setSharkCellParameter(int shark) i
	public WaTorSimulation(Grid grid) c
	*public void populateCellToActorMap() x
	public Grid step() p
	public void initialize(ArrayList<Integer> cellStates) x
	public Cell updateCellState(Cell cell) i
	public void reproduceIfAllowed(Actor actor, boolean useFishReproductionTime) p
	public void moveAtRandom(Actor actor, ArrayList<Cell> emptyCells) p
}
 
package src.controller;
public class XMLParser { 
  	public XMLParser(File myFile) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, ClassNotFoundException, DOMException, IllegalArgumentException, IllegalAccessException c
	public void fileCheck(File file) throws IOException p 
	public void parseFile(Document doc) throws NoSuchFieldException, SecurityException,  ClassNotFoundException, DOMException, IllegalArgumentException, IllegalAccessException p
	public void createParamsList(NodeList params) p
	public void createCellsMap(NodeList cells) p
	public Grid makeCellsGrid(HashMap<Integer[], Integer> map, int xLen, int yLen) p
	public void setDimensions(NodeList dimen) p
	public HashMap<Integer[], Integer> getCellsMap() x
	public HashMap<Integer, Color> getStatesMap() x
	public ArrayList<Double> getParamsList() x
	public int getXLen() x
	public int getYLen() x
	public String getSimType() x
	public ArrayList<Integer> getStatesList() x
}
 
package src.Model;
public class Actor { 
  	public Actor(int x, int y, double actorEnergy, double depletionRate) c
	public double getTimeSinceReproduced() i
	public void resetTimeSinceReproduced() i 
	public double getDepletionRate() i 
	public void updateTimeSinceReproduced(double time) i
	public void move(int x, int y) p
	public int getX() i
	public void setX(int x) i
	public int getY() i
	public void setY(int y) i
	public void depleteEnergy() i
	public boolean isDead() i
	public void setEnergy(double newEnergy) i
	public double getEnergy() i
}
 
package src.Model;
public class Cell { 
      public Cell(int state) c 
    public Cell(int x, int y, int state) c 
    public Actor getActor() i
    public void setActor(Actor actorToSet) i
    public void removeActor() i
    public Cell(Cell toUse) c 
    public Boolean isState(int state) i
    public Boolean isDiagonalNeighborWith(Cell otherCell) i
    public Cell clone() i 
    public void setX(int x) i
    public void setY(int y) i
    public void setState(int state) i
    public int getX() i
    public int getY() i
    public int getState() i
}
 
package src.View;
public class Display { 
      public Display(Scene scene, Group root, Stage stage) c
    public void addEventListener(EventListener listener) p
    public void makeToolbar(Group root, Stage stage) p
    public void addFileOpener(Stage stage) p
    public void drawGraph() p
    public void setGraphTitle(String s) p
    public void draw(Grid grid, HashMap<Integer, Color> statesMap) p
    public File getFile() x
}
 
package src.View;
public class Grid { 
      public Grid(int width, int height) c 
	public ArrayList<Cell> getAllNeighbors(Cell cell) x 
	public ArrayList<Cell> getNonDiagonalNeighbors(Cell cell) x
	public Grid getGridClone() x 
    public int getGridWidth() x 
    public int getGridHeight() x
    public void setCell(Cell cell) i
    public Cell getCell(int x, int y) x
    public CellIterator getCellIterator() i
    public void draw(Canvas canvas, HashMap<Integer, Color> myColorMap) p
    public String toString() p
}
```

Simulation
	Internal
		The internal API for the Simulation sub-part is largely made up of getters/setters to fetch and change information about the status of each of the cells or agents on the board. We imagine that this information is required by other methods within the simulations to determine the next state of each cell. Along with determining the next state of each cell, it is important for each 		simulation to have an updateCellState() method that can be used internally to implement setting each cell's next state. Other internal methods are used to perform discrete functions on the grid, such as removing a neighbor, cloning a neighbor, or determining a specific relationship between cells.
	External
		The external API for the Simulation sub-part is limited to those methods that communicate grid states with the visualization classes, so that they can be displayed. The distinction that we drew between internal and external simulation methods were those that dealt with changing individual cells or agents and those that passed the current status of large sets of cells, respectively. Some of these external methods were populateCellToActorMap() and returnTitle().

Configuration
	Internal
		The internal API for the configuration sub-part consists only of methods responsible for parsing the information within 	the XML document into usable formats. 
	External
		The external API for the configuration sub-part consists of methods used to pass the appropriate formatted XML parameters to the simulation and visualizations sub-parts for each to access.

Visualization
	Internal
		Internally we see that the Grid class has a closed action method that probably uses some of the getters and setters  in order to draw the grid on the GUI. This method is however repeated in Display class. It seems that some of the methods should not be public that are public.
	External
		The way that the software communicates with the Visualization API's is through a bunch of getters and setters. 
 
