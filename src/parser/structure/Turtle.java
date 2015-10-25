package parser.structure;

import java.util.function.UnaryOperator;

import gui.GUITurtle;
import parser.ParserException;
import parser.command.Evaluable;
import util.Coordinate;

public interface Turtle extends GUITurtle {
	
	public int getID();
	
	public double move(Evaluable distance, UnaryOperator<Double> operator) throws ParserException;

	public boolean visible();
	public boolean visible(boolean visible);
	
	public void penDown();
	public void penUp();
	public boolean isPenDown();
	
	public Pen getPen();
	public double setPenSize(Evaluable size) throws ParserException;
	public double setPenColor(Evaluable color) throws ParserException;
	
	public double setShape(Evaluable shape) throws ParserException;
	public double getShape();
	
	public double getHeading();
	public double setPosition(Evaluable x, Evaluable y) throws ParserException;
	public double setHeading(Evaluable angle) throws ParserException;
	public double setTowards(Evaluable x, Evaluable y) throws ParserException;
	
	public void clear();
	public double goHome();

	public double turn(Evaluable angle, UnaryOperator<Double> operator) throws ParserException;

	public Coordinate getCoordinate();
	
	public double stamp();

}
