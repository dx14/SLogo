package parser.structure;

import util.Coordinate;
import util.LineStyle;
import util.SlogoPath;
import util.StraightPath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

import gui.GUITurtle;
import parser.ParserException;
import parser.command.Evaluable;


public class SimpleTurtle implements Turtle, GUITurtle{

	private static int id = 1;
	int myID;
	
	Coordinate myCoord;
	double myHeading;
	
	Pen myPen;
	boolean visible;
	
	int myShape;
	
	List<SlogoPath> myHistory;
	List<SlogoPath> myCurrentPaths;
	boolean clear;
	boolean stamp;
	
	TurtleContainer myContainer;
	
	public SimpleTurtle(TurtleContainer container){
		myContainer = container;
		
		myID = id++;
		
		myCoord = new Coordinate(0,0);
		myHeading = 0;
		
		myPen = new Pen();
		myCurrentPaths = new ArrayList<SlogoPath>();
		myHistory = new ArrayList<SlogoPath>();
		
		myShape = 0;
		
		visible = true;
		clear = false;
		stamp = false;
		update();
	}
	
	public double move(Evaluable distance, UnaryOperator<Double> operator) throws ParserException{
		//System.out.print("Moving turtle from " + myCoord);
		double distanceMoved = distance.evaluate();
		myCurrentPaths.add(new StraightPath(myCoord.clone(), myCoord.update(operator.apply(distanceMoved), myHeading).clone(), myPen.clone()));
		//myCurrentPaths.stream().forEach(s -> System.out.println(s));
		//System.out.println(" to " + myCoord);
		update();
		return distanceMoved;
	}

	@Override
	public boolean visible(){
		return visible;
	}
	
	@Override
	public boolean visible(boolean visible){
		this.visible = visible;
		return visible;
	}

	public double getHeading() {
		return myHeading;
	}

	@Override
	public double setPosition(Evaluable x, Evaluable y) throws ParserException{
		double distance = myCoord.set(x.evaluate(), y.evaluate());
	        update();
	        return distance;
	}

	@Override
	public double goHome() {
		double distance = myCoord.set(0, 0);
		update();
		return distance;
	}
	
	@Override
	public void clear() {
		clear = true;
		myCurrentPaths.clear();
		myHistory.clear();
		myCoord.set(0, 0);
		update();
	}

	@Override
	public double turn(Evaluable angle, UnaryOperator<Double> operator) throws ParserException{
		double degreesTurned = angle.evaluate();
		myHeading = myHeading - operator.apply(degreesTurned);
		update();
		return degreesTurned;
	}

	@Override
	public double setHeading(Evaluable angle) throws ParserException {
		double newAngle = angle.evaluate();
		return setHeading(newAngle);
	}

	private double setHeading (double angle){
		double diff = angle - myHeading;
		myHeading = angle;
		update();
		return diff;
	}
	
	@Override
	public Coordinate getCoordinate() {
		return myCoord;
	}

	public double setTowards(Evaluable x, Evaluable y) throws ParserException{
		double adjacent = x.evaluate() - myCoord.getX();
		double opposite = y.evaluate() - myCoord.getY();
		double theta = Math.atan2(opposite, adjacent);
		
		return setHeading(Math.toDegrees(theta));
	}

	@Override
	public List<SlogoPath> getPaths() {
		return Collections.unmodifiableList(myCurrentPaths);
	}
	
	@Override
	public List<SlogoPath> getHistory() {
		return Collections.unmodifiableList(myHistory);
	}

	@Override
	public Pen getPen() {
		return myPen;
	}
	
	@Override
	public int getID(){
		return myID;
	}

	@Override
	public boolean isClear() {
		return clear;
	}

	@Override
	public void completeUpdate() {
		myHistory.addAll(myCurrentPaths);
		myCurrentPaths.clear();
		clear = false;
		stamp = false;
	}

	@Override
	public double setPenColor(Evaluable color) throws ParserException{
		double newColor = color.evaluate();
		myPen.setColor(((Double)newColor).intValue());
		update();
		return newColor;
	}
	
	@Override
	public void setPenColor(int color) {
		myPen.setColor(color);
	}

	@Override
	public int getDisplayIndex() {
		return myShape;
	}

	@Override
	public void setDisplayIndex(int display) {
		myShape = display;
		update();
	}
	
	protected void update(){
		myContainer.update();
	}

	@Override
	public boolean isShowing() {
		System.out.println("UPDATING FRONT END " + visible);
		return visible;
	}

	@Override
	public void penDown() {
		myPen.setDown(true);
		update();
	}

	@Override
	public void penUp() {
		myPen.setDown(false);
		update();
	}

	@Override
	public boolean isPenDown() {
		return myPen.isDown();
	}

	@Override
	public double setPenSize(Evaluable size) throws ParserException {
		double newSize = size.evaluate();
		myPen.setWidth(newSize);
		return newSize;
	}

	@Override
	public double setShape(Evaluable shape) throws ParserException {
		double newShape = shape.evaluate();
		myShape = ((Double)newShape).intValue();
		update();
		return newShape;
	}

	@Override
	public double getShape() {
		return myShape;
	}

	@Override
	public double stamp() {
		stamp = true;
		update();
		return 0;
	}
	
	@Override
	public boolean isStamped() {
		return stamp;
	}

	@Override
	public void setPenStyle(LineStyle style) {
		myPen.setStyle(style);
	}

	@Override
	public LineStyle getPenStyle() {
		return myPen.getStyle();
	}
}
