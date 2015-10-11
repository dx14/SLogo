package parser.command.commandlist;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class ConstantCommand extends Command {

	public ConstantCommand(){
		System.out.println("Constant Command Initializing");
	}
	
	@Override
	public double evaluate() throws ParserException {
		System.out.println("evaluating distance");
		return Double.parseDouble(myTree.getSource().getRawCommand(0));
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.getRemainder();
	}

}
