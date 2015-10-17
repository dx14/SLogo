package util;

import parser.structure.GUICoordinate;

public class Coordinate implements GUICoordinate{
	
	private double myX;
	private double myY;
	
	public Coordinate(double x, double y){
		myX = x;
		myY = y;
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	
	// TODO: add wrapper
	public void update(double distance, double angle){
		myX += distance * Math.sin(Math.toRadians(angle));
		myY += distance * Math.cos(Math.toRadians(angle));
	}
	
	public void set(double x, double y){
		myX = x;
		myY = y;
	}
	
	@Override
	public String toString(){
		return "(" + myX + ", " + myY + ")";
	}

}
