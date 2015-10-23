package util;

import gui.GUIPen;
import parser.structure.Pen;

public abstract class SlogoPath {

	private GUICoordinate start;
	private GUICoordinate end;
	
	private GUIPen myPen;
	
	
	public SlogoPath(GUICoordinate start, GUICoordinate end){
		this(start, end, new Pen());
	}
	
	public SlogoPath(GUICoordinate start, GUICoordinate end, GUIPen pen){
		this.start = start;
		this.end = end;
		myPen = pen;
	}
	
	public GUICoordinate getStart(){
		return start;
	}
	
	public GUICoordinate getEnd(){
		return end;
	}
	
	public double getXLength() {
	    return end.getX()-start.getX();
	}
    
	public double getYLength() {
        return end.getY()-start.getY();
    }
	
	public GUIPen getPen(){
		return myPen;
	}

	@Override
	public String toString(){
		return "Path from: " + start + " to " + end;
	}
}
