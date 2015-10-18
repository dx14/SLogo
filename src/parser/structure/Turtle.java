package parser.structure;

import util.Coordinate;
import util.SlogoPath;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

// TODO: modify GUI turtle -> change JavaFX specific commands to Strings

public class Turtle extends Observable implements GUITurtle2{
	
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
	
	public Turtle(int id){
		myID = id;
		
		
		myCoord = new Coordinate(0,0);
		myHeading = 0;
		
		myPen = new Pen();
		
		visible = true;
		clear = false;
	}
	
	public void move(double distance){
		System.out.print("Moving turtle from " + myCoord);
		myCoord.update(distance, myHeading);
		System.out.println(" to " + myCoord);
	}

	public void show() {
		visible = true;
	}
	
	public void hide() {
		visible = false;
	}
	
	public void penUp() {
		myPen.setDown(false);
	}
	
	public void penDown() {
		myPen.setDown(true);	}
	
	public boolean isPenDown() {
		return myPen.isDown();
	}

	public double getHeading() {
		return myHeading;
	}


	public double setPosition(double x, double y) {
		return myCoord.set(x, y);
	}

	public void clear() {
		clear = true;
	}

	public void turn(double angle) {
		myHeading = myHeading + angle;
	}

	public double setHeading(double angle) {
		double diff = myHeading - angle;
		myHeading = angle;
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
}
