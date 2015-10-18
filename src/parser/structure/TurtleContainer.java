package parser.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class TurtleContainer extends Observable implements GUITurtleContainer {

	private List<Turtle> myTurtles;

	private Turtle myCurrentTurtle;
	
	public TurtleContainer(){
		myTurtles = new ArrayList<Turtle>();
		myCurrentTurtle = new Turtle(this);
		myTurtles.add(myCurrentTurtle);
	}
	
	@Override
	public Turtle getCurrentTurtle(){
		return myCurrentTurtle;
	}
	
	public void debug(){
		myTurtles.stream().forEach(t -> System.out.println(t));
	}
	
	protected void update(){
		setChanged();
		notifyObservers();
	}
	
}
