package parser.structure;

import util.Coordinate;
import util.SlogoPath;
import util.StraightPath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import gui.GUITurtle;

import gui.GUITurtle;

// TODO: modify GUI turtle -> change JavaFX specific commands to Strings

public class Turtle implements GUITurtle{
	
	private static int id = 1;
	int myID;
	
	Coordinate myCoord;
	double myHeading;
	
	Pen myPen;
	boolean visible;
	
	boolean useImage;
	String myImageOrShape;
	
	List<SlogoPath> myHistory;
	List<SlogoPath> myCurrentPaths;
	boolean clear;
	
	TurtleContainer myContainer;
	
	public Turtle(TurtleContainer container){
		myContainer = container;
		
		myID = id++;
		
		myCoord = new Coordinate(0,0);
		myHeading = 0;
		
		myPen = new Pen();
		myCurrentPaths = new ArrayList<SlogoPath>();
		
		visible = true;
		clear = false;
		update();
	}
	
	public void move(double distance){
		System.out.print("Moving turtle from " + myCoord);
		myCurrentPaths.add(new StraightPath(myCoord.clone(), myCoord.update(distance, myHeading), myPen.clone()));
		System.out.println(" to " + myCoord);
		update();
	}

	public void show() {
		visible = true;
		update();
	}
	
	public void hide() {
		visible = false;
		update();
	}
	
	public void penUp() {
		myPen.setDown(false);
		update();
	}
	
	public void penDown() {
		myPen.setDown(true);
		update();
	}
	
	public boolean isPenDown() {
		return myPen.isDown();
	}

	public double getHeading() {
		return myHeading;
	}

	public double setPosition(double x, double y) {
		update();
		return myCoord.set(x, y);
	}

	public void clear() {
		clear = true;
		update();
	}

	public void turn(double angle) {
		myHeading = myHeading + angle;
		update();
	}

	public double setHeading(double angle) {
		double diff = myHeading - angle;
		myHeading = angle;
		update();
		return diff;
	}

	public boolean isShowing() {
		return visible;
	}

	public Coordinate getCoordinate() {
		return myCoord;
	}

	public double setTowards(double x, double y) {
		
		double adjacent = x - myCoord.getX();
		double opposite = y - myCoord.getY();
		double hypotenuse = Math.sqrt( Math.pow(adjacent, 2) + Math.pow(opposite, 2) );
		double theta = Math.asin(opposite/hypotenuse);
		
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
	}

	@Override
	public void setPenColor(String color) {
		myPen.setColor(color);
		update();
	}

	@Override
	public boolean usingImage() {
		return useImage;
	}

	@Override
	public void setUsingImage(boolean useImage) {
		this.useImage = useImage;
		update();
	}

	@Override
	public String getDisplayString() {
		return myImageOrShape;
	}

	@Override
	public void setDisplayString(String display) {
		myImageOrShape = display;
		update();
	}
	
	private void update(){
		myContainer.update();
	}
}
