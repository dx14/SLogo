package parser;

import java.util.ResourceBundle;

import parser.command.CommandElement;
import parser.command.tree.CommandTreeNode;

public class Validator {

	private static ResourceBundle errors = ResourceBundle.getBundle("resources.config.errors");
	
	public static void assertNumArguments(CommandTreeNode tree, CommandElement command, int count, boolean subtractone) throws ParserException{
		int adjustment = (subtractone)?1:0;
		if(tree.getNumBranches() != count){
			throw new ParserException(String.format(errors.getString("NumArguments"), command.getRawText(), count - adjustment, tree.getNumBranches() - adjustment));
		}
	}
	
	public static void assertType(CommandTreeNode tree, CommandElement command, Class<?> test) throws ParserException{
		if(!( test.isInstance(tree.getCommand()))){
			throw new ParserException(String.format(errors.getString("ArgumentType"), command.getRawText(), test.getCanonicalName(), tree.getCommandElement().getRawText()));
		}
	}
	
	public static void assertParent(CommandTreeNode tree, CommandElement command, Class<?> test) throws ParserException {
		if(!( test.isInstance(tree.getParent().getCommand()))){
			throw new ParserException(String.format(errors.getString("ParentType"), command.getRawText()));
		}
	}
	
}
