package parser.structure;

import java.util.List;
import java.util.Observable;

public class TurtleContainer extends Observable implements GUITurtleContainer {

	private List<Turtle> myTurtles;

	private Turtle myCurrentTurtle;
	
	public TurtleContainer(){
		myCurrentTurtle = new Turtle();
		myTurtles.add(myCurrentTurtle);
	}
	
	@Override
	public Turtle getCurrentTurtle(){
		return myCurrentTurtle;
	}
	
	public void debug(){
		myTurtles.stream().forEach(t -> System.out.println(t));
	}
	
}
