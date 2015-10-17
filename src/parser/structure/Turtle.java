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


	public double setPosition(double d, double e) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public double setPosition(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void turn(double angle) {
		// TODO Auto-generated method stub
		
	}

	public double setHeading(double evaluate) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isShowing() {
		return visible;
	}

	public Coordinate getCoordinate() {
		return myCoord;
	}

	public double setTowards(double evaluate, double evaluate2) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void completeUpdate() {
		myCurrentPaths.clear();
		clear = false;
	}
}
