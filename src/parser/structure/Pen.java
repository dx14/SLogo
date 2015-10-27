package parser.structure;

import gui.GUIPen;
import parser.ParserException;
import util.LineStyle;

public class Pen implements GUIPen{

	private double myWidth;
	private int myColor;
	private boolean down;
	private LineStyle myStyle;
	
	public Pen(){
		this(0, 1, true, LineStyle.SOLID);
	}
	
	public Pen(int color, double width, boolean down, LineStyle style){
		myColor = color;
		myWidth = width;
		this.down = down;
	        myStyle = style;
	}
	
	public void setDown(boolean down){
		this.down = down;
	}

	@Override
	public boolean isDown() {
		return down;
	}

	@Override
	public double getWidth() {
		return myWidth;
	}

	@Override
	public void setColor(int d) {
		myColor = d;
	}
	
	public void setWidth(double width) throws ParserException {
		if(width > 0){
			myWidth = width;
		}
		else{
			throw new ParserException("Error: pen width must be greater than 0.");
		}
	}
	
	@Override
	public int getColor(){
		return myColor;
	}
	
	public Pen clone() {
		return new Pen(myColor, myWidth, down, myStyle);
	}
	
	public LineStyle getStyle() {
	    return myStyle;
	}
	public void setStyle(LineStyle style) {
	    myStyle=style;
	}
}
