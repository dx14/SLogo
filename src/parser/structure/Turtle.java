package parser.structure;

import gui.GUITurtle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import util.Coordinate;

// TODO: modify GUI turtle -> change JavaFX specific commands to Strings

public class Turtle implements Observable{
	
	Coordinate myCoord;
	double myHeading;
	boolean penDown;
	boolean visible;
	
	boolean useImage;
	String myImageOrShape;
	
	//TODO: make pen object
	String penColor;
	
	public Turtle(){
		myCoord = new Coordinate(0,0);
		myHeading = 0;
		penDown = true;
		visible = true;
	}
	
	public void move(double distance){
		System.out.println("Moving turtle from " + myCoord);
		myCoord.update(distance, myHeading);
		System.out.println("to " + myCoord);
	}

	public void show() {
		visible = true;
	}
	
	public void hide() {
		visible = false;
	}
	
	public void penUp() {
		penDown = false;
	}
	
	public void penDown() {
		penDown = true;
	}
	
	public boolean isPenDown() {
		return penDown;
	}

	public double getHeading() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return false;
	}

	public Coordinate getCoordinate() {
		// TODO Auto-generated method stub
		return null;
	}

	public double setTowards(double evaluate, double evaluate2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

}
