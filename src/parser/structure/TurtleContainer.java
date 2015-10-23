package parser.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class TurtleContainer extends Observable implements GUITurtleContainer {

	private List<FullTurtle> myTurtles;

	private FullTurtle myCurrentTurtle;
	
	public TurtleContainer(){
		myTurtles = new ArrayList<FullTurtle>();
		myCurrentTurtle = new SimpleTurtle(this);
		myTurtles.add(myCurrentTurtle);
	}
	
	@Override
	public FullTurtle getCurrentTurtle(){
		return myCurrentTurtle;
	}
	
	public void debug(){
		myTurtles.stream().forEach(t -> System.out.println(t));
	}
	
	public void setCurrent(List<Integer> turtleIDs){
		
	}
	
	public void update(){
		setChanged();
		notifyObservers();
		//System.out.println("QQQQQQQQ");
	}
	
}
