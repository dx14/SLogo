package parser;

import java.util.ResourceBundle;

import parser.command.CommandElement;
import parser.command.CommandList;
import parser.command.tree.CommandTreeNode;

public class Validator {

	private static ResourceBundle errors = ResourceBundle.getBundle("resources.config.Errors");
	
	public static void assertNumArguments(CommandTreeNode tree, CommandElement command, int count, boolean subtractone) throws ParserException{
		int adjustment = (subtractone)?1:0;
		if(tree.getNumBranches() != count){
			throw new ParserException(String.format(errors.getString("NumArguments"), command.getRawText(), count - adjustment, tree.getNumBranches() - adjustment));
		}
	}
	
	public static void assertType(CommandTreeNode tree, CommandElement command, Class<?> test) throws ParserException{
		if(!( test.isInstance(tree.getCommand()))){
			throw new ParserException(String.format(errors.getString("ArgumentType"), command.getRawText(), test.getSimpleName(), tree.getCommandElement().getRawText()));
		}
	}
	
	public static void assertParent(CommandTreeNode tree, CommandElement command, Class<?> test) throws ParserException {
		if(!( test.isInstance(tree.getParent().getCommand()))){
			throw new ParserException(String.format(errors.getString("ParentType"), command.getRawText()));
		}
	}
	
	public static void assertNonEmpty(CommandList commands) throws ParserException{
		if(commands.isEmpty()){
			throw new ParserException(errors.getString("NotEnoughArgs"));
		}
	}
	
	public static void assertCommandExists(boolean notFound, CommandElement command) throws ParserException{
		if(notFound){
			throw new ParserException(String.format(errors.getString("CommandNotFound"), command.getRawText()));
		}
	}
}
