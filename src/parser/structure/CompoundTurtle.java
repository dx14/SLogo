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
import util.SlogoPath;

public class CompoundTurtle implements FullTurtle, GUITurtle{

	private Map<Integer, FullTurtle> myTurtles;
	private int currentTurtleID;
	private TurtleContainer myContainer;
	
	public CompoundTurtle(TurtleContainer container, List<FullTurtle> turtles){
		myContainer = container;
		myTurtles = turtles.stream()
				.collect(Collectors.toMap((t) -> t.getID(), Function.identity()));
		currentTurtleID = myTurtles.get(myTurtles.values().size()).getID();
	}
	
	private double recursiveSet(TurtleFunction<FullTurtle, Double> operation) throws ParserException{
		double result = 0;
		for(FullTurtle t : myTurtles.values()){
			currentTurtleID = t.getID();
			result = operation.apply(t);
			update();
		}
		return result;
	}
	
	private double recursiveSet(TurtleFunction<FullTurtle, Double> operation, boolean exceptionsOn){
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
	public void setPenColor(Evaluable color) throws ParserException{
		recursiveSet( (t) -> {t.setPenColor(color); return 0.0;});
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
	public boolean usingImage() {
		return myTurtles.get(currentTurtleID).usingImage();
	}

	// NOTE: may want to do recursive-set on this one...?
	@Override
	public void setUsingImage(boolean useImage) {
		myTurtles.get(currentTurtleID).setUsingImage(useImage);
	}

	@Override
	public String getDisplayString() {
		return myTurtles.get(currentTurtleID).getDisplayString();
	}

	@Override
	public void setDisplayString(String display) {
		myTurtles.get(currentTurtleID).setDisplayString(display);
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
	public void setPenColor(double color) {
		myTurtles.get(currentTurtleID).setPenColor(color);
	}

}
