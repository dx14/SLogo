package parser.command.commandlist;

import java.util.List;

import parser.ParserException;
import parser.command.Command;

public class ConstantCommand extends Command {

	public ConstantCommand(){
		System.out.println("Constant Command Initializing");
	}
	
	@Override
	public double evaluate() throws ParserException {
		return Double.parseDouble(myTree.getSource().get(0));
	}

	@Override
	public List<String> build() throws ParserException {
		return myTree.getRemainder();
	}

}
