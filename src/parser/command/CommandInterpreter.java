package parser.command;

public class CommandInterpreter {

	private CommandString myCommandString;
	private CommandTree myCommandTree;
	
	public CommandInterpreter(String command){
		
		
		// create a Native Translator object
		myCommandTree = new CommandTree(command);
		
	}
	
	public static Evaluable reflect(String raw, CommandTree tree){
		return null;
	}
	
}
