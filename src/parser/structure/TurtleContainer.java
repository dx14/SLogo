package parser.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

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
		turtleIDs.stream().forEach((i) -> initializeTurtle(i));
		myCurrentTurtle = new CompoundTurtle(turtleIDs.stream().map(this::getTurtle).collect(Collectors.toList()));
	}
	
	private void initializeTurtle(int id){
		while(myTurtles.size() < id){
			myTurtles.add(new SimpleTurtle(this));
			update();
		}
	}
	
	private FullTurtle getTurtle(int id){
		return myTurtles.get(id - 1);
	}
	
	public void update(){
		setChanged();
		notifyObservers();
		//System.out.println("QQQQQQQQ");
	}
	
}
