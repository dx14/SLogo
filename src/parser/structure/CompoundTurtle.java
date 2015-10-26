package parser.structure;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import gui.GUITurtle;
import parser.ParserException;
import parser.command.Evaluable;
import util.Coordinate;
import util.LineStyle;
import util.SlogoPath;

public class CompoundTurtle implements Turtle, GUITurtle{

	private Map<Integer, Turtle> myTurtles;
	private int currentTurtleID;
	private TurtleContainer myContainer;
	
	public CompoundTurtle(TurtleContainer container, List<Turtle> turtles){
		myContainer = container;
		myTurtles = turtles.stream()
				.collect(Collectors.toMap((t) -> t.getID(), Function.identity()));
		currentTurtleID = myTurtles.values().stream().reduce( (t1, t2) -> t2 ).get().getID();
		System.out.println("CURRENT TURTLE ID: " + currentTurtleID);
	}
	
	private double recursiveSet(TurtleFunction<Turtle, Double> operation) throws ParserException{
		double result = 0;
		for(Turtle t : myTurtles.values()){
			currentTurtleID = t.getID();
			result = operation.apply(t);
			System.out.println("Applying operation to turtle " + currentTurtleID);
			update();
		}
		return result;
	}
	
	private double recursiveSet(TurtleFunction<Turtle, Double> operation, boolean exceptionsOn){
		double result = 0;
		try{
			result = recursiveSet(operation);
		}
		catch(ParserException e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int getID() {
		return currentTurtleID;
	}

	@Override
	public List<SlogoPath> getPaths() {
		return myTurtles.get(currentTurtleID).getPaths();
	}

	@Override
	public List<SlogoPath> getHistory() {
		return myTurtles.get(currentTurtleID).getHistory();

	}

	@Override
	public Pen getPen() {
		return myTurtles.get(currentTurtleID).getPen();

	}

	@Override
	public boolean isShowing() {
		return myTurtles.get(currentTurtleID).isShowing();
	}

	@Override
	public boolean isClear() {
		return myTurtles.get(currentTurtleID).isClear();
	}

	@Override
	public void completeUpdate() {
		myTurtles.get(currentTurtleID).completeUpdate();
	}

	@Override
	public double move(Evaluable distance, UnaryOperator<Double> operator) throws ParserException {
		return recursiveSet( (t) -> t.move(distance, operator) );
	}

	@Override
	public boolean visible() {
		return myTurtles.get(currentTurtleID).visible();
	}

	@Override
	public boolean visible(boolean visible) {
		recursiveSet( (t) -> { t.visible(visible); return 0.0; }, false);
		return visible;
	}

	@Override
	public void penDown() {
		recursiveSet( (t) -> { t.penDown(); return 0.0; } , false);
	}

	@Override
	public void penUp() {
		recursiveSet( (t) -> { t.penUp(); return 0.0; }, false);
	}

	@Override
	public boolean isPenDown() {
		return myTurtles.get(currentTurtleID).isPenDown();
	}

	@Override
	public double getHeading() {
		return myTurtles.get(currentTurtleID).getHeading();
	}

	@Override
	public double setPosition(Evaluable x, Evaluable y) throws ParserException {
		return recursiveSet( (t) -> t.setPosition(x, y) );
	}

	@Override
	public double setHeading(Evaluable angle) throws ParserException {
		return recursiveSet( (t) -> t.setHeading(angle) );
	}

	@Override
	public double setTowards(Evaluable x, Evaluable y) throws ParserException {
		return recursiveSet((t) -> t.setTowards(x, y) );
	}

	@Override
	public void clear() {
		recursiveSet( (t) -> {t.clear(); return 0.0;}, false );
	}

	@Override
	public double goHome() {
		return recursiveSet((t) -> t.goHome(), false);
	}

	@Override
	public double turn(Evaluable angle, UnaryOperator<Double> operator) throws ParserException {
		return recursiveSet((t)->t.turn(angle, operator));
	}

	@Override
	public Coordinate getCoordinate() {
		return myTurtles.get(currentTurtleID).getCoordinate();
	}
	
	private void update(){
		myContainer.update();
	}
	
	@Override
	public void setPenColor(int color) {
		myTurtles.get(currentTurtleID).setPenColor(color);
	}

	@Override
	public boolean isStamped() {
		return myTurtles.get(currentTurtleID).isStamped();
	}

	@Override
	public double setPenSize(Evaluable size) throws ParserException {
		return recursiveSet((t) -> t.setPenSize(size));
	}

	@Override
	public double setPenColor(Evaluable color) throws ParserException {
		return recursiveSet((t) -> t.setPenColor(color));
	}

	@Override
	public double setShape(Evaluable shape) throws ParserException {
		return recursiveSet((t) -> t.setShape(shape));
	}

	@Override
	public double getShape() {
		return myTurtles.get(currentTurtleID).getShape();
	}

	@Override
	public double stamp() {
		return recursiveSet((t) -> t.stamp(), false);
	}

	@Override
	public void setPenStyle(LineStyle style) {
		recursiveSet((t) -> { t.setPenStyle(style); return 0.0;}, false);
	}

	@Override
	public LineStyle getPenStyle() {
		return myTurtles.get(currentTurtleID).getPenStyle();
	}

	@Override
	public int getDisplayIndex() {
		return myTurtles.get(currentTurtleID).getDisplayIndex();
	}

	@Override
	public void setDisplayIndex(int display) {
		recursiveSet((t) -> {t.setDisplayIndex(display); return 0.0;}, false);
	}

}
