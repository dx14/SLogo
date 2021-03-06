package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.Validator;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.Evaluable;

public class UserDefinedCommand extends Command {

	private Evaluable usercommand;
	
	private boolean notFound = false;
	
	@Override
	public double evaluate() throws ParserException {
		Validator.assertCommandExists(notFound, myCommand);
		return usercommand.evaluate();
	}
	
	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.getRemainder();
		try{
			usercommand = myParser.getCommandContainer().getCommandInstance(myCommand.getRawText());
			usercommand.setParameters(myTree, myParser, myCommand);
			remainder = usercommand.build();
		}
		// if the command hasn't been created yet, it's not NECESSARILY an error -> could be the first time
		// in this case we set a flag and make sure we never try to evaluate this command
		catch(ParserException e){
			notFound = true;
		}
		return 	remainder;
	}
}
