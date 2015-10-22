package gui;

import parser.ParserException;
import util.LineStyle;

public interface GUIPen {

	public boolean isDown();
	
	public double getWidth();
	public void setWidth(double width) throws ParserException;
	
	public String getColor();
	
	public void setColor(String color);
	
	public LineStyle getStyle();
	public void setStyle(LineStyle style);
}
