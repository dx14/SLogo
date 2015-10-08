package parser.command;

public class CommandInterpreter {

	private CommandString myCommandString;
	private CommandTree myCommandTree;
	
	public CommandInterpreter(String command){
		
		/*
		 * 
		 * 1. Build Command String
		 * 2. Pass Command String to Native Translator -- does step 1 too
		 * 3. Build Command Tree
		 * 
		 */
		
		// create a Native Translator object
		//myCommandTree = new CommandTree(command);
		
	}
	
	public static Evaluable reflect(String raw, CommandTree tree){
		return null;
	}
	
}
