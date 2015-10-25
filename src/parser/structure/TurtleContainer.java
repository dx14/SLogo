package parser.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import parser.ParserException;
import parser.command.Evaluable;

public class TurtleContainer extends Observable implements GUITurtleContainer {

	private List<Turtle> myTurtles;

	private List<Turtle> myTurtleExecList;
	
	public TurtleContainer(){
		myTurtles = new ArrayList<Turtle>();
		myTurtleExecList = new ArrayList<Turtle>();
		initializeTurtle(1);
		myTurtleExecList.add(getTurtle(1));
	}
	
	@Override
	public Turtle getCurrentTurtle(){
		return myTurtleExecList.get(myTurtleExecList.size()-1);
	}
	
	public void debug(){
		myTurtles.stream().forEach(t -> System.out.println(t));
	}
	
	public void setCurrent(List<Integer> turtleIDs){
		System.out.println(turtleIDs);
		if(!turtleIDs.isEmpty()){
		turtleIDs.stream().forEach((i) -> initializeTurtle(i));
		myTurtleExecList.set(0, new CompoundTurtle(this, turtleIDs.stream().map(this::getTurtle).collect(Collectors.toList())));
		}
	}
	
	public void addToExecList(List<Integer> turtleIDs){
		turtleIDs.stream().forEach((i) -> initializeTurtle(i));
		myTurtleExecList.add(new CompoundTurtle(this, turtleIDs.stream().map(this::getTurtle).collect(Collectors.toList())));
	}
	
	public void addToExecList(Evaluable condition) throws ParserException{
		List<Integer> turtleIDs = myTurtles.stream()
				.filter( (i) -> test(i, condition) )
				.map((t) -> t.getID())
				.collect(Collectors.toList());
		addToExecList(turtleIDs);
	}
	
	public void removeFromExecList(){
		myTurtleExecList.remove(myTurtleExecList.size()-1);
	}
	
	private void initializeTurtle(int id){
		while(myTurtles.size() < id){
			myTurtles.add(new SimpleTurtle(this));
			System.out.println("INITIALIZING TURTLE " + myTurtles.size());
			update();
		}
	}
	
	private boolean test(Turtle t, Evaluable condition) {
		addToExecList(Arrays.asList(t.getID()));
		boolean result = false;
		try {
			result = (condition.evaluate()!=0);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		removeFromExecList();
		System.out.println("TURTLE " + t.getID() + ((result)?" PASSES ":" FAILS ") + condition.toString());
		return result;
	}
	
	private Turtle getTurtle(int id){
		return myTurtles.get(id - 1);
	}
	
	public void update(){
		setChanged();
		notifyObservers();
		//System.out.println("QQQQQQQQ");
	}

	public double getNumTurtles() {
		return (double)myTurtles.size();
	}
	
}
