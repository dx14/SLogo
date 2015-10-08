package parser.command.commandlist;

import java.util.List;

import parser.ParserException;
import parser.SlogoParser;
import parser.command.Command;
import parser.command.CommandTree;
import parser.command.Evaluable;

public class ForwardCommand extends Command {
	
	private Evaluable myDistance;
	
	public ForwardCommand(){
		System.out.println("Forward Command Initializing");
	}

	@Override
	public double evaluate() throws ParserException {
		double distance = myDistance.evaluate();
		myParser.moveCurrentTurtle(distance);
		return distance;
	}

	@Override
	public List<String> build() throws ParserException {
		
		myDistance = myTree.buildNext();
		return myTree.getRemainder();
	}

}
