package parser.structure;

import util.Coordinate;
import util.SlogoPath;

import java.util.List;
import java.util.Observable;

// TODO: modify GUI turtle -> change JavaFX specific commands to Strings

public class Turtle extends Observable implements GUITurtle2{
	
	Coordinate myCoord;
	double myHeading;
	
	Pen myPen;
	boolean visible;
	
	boolean useImage;
	String myImageOrShape;
	
	public Turtle(){
		myCoord = new Coordinate(0,0);
		myHeading = 0;
		
		myPen = new Pen();
		
		visible = true;
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
	public List<SlogoPath> getPaths() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pen getPen() {
		// TODO Auto-generated method stub
		return null;
	}
}
