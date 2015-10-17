package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.command.CommandList;
import parser.command.Evaluable;

public class RootCommand extends parser.command.Command {

	@Override
	public double evaluate() throws ParserException {
		double result = 0;
		int index = 0;
		while(true){
			try{
				Evaluable current = myTree.get(index++);
				result = current.evaluate();
			}
			catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return result;
	}

	@Override
	public CommandList build() throws ParserException {
		CommandList remainder;
		do{
			remainder = myTree.buildNext().getRemainder();
		}while(!remainder.isEmpty());
		return remainder;
	}

}
