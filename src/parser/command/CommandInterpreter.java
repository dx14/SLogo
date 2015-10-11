package parser.command;

import java.util.List;

import parser.ParserException;
import parser.SlogoParser;
import parser.resource.NativeTranslator;

public class CommandInterpreter {

	private CommandList myCommandList;
	private CommandTree myRoot;
	private SlogoParser myParser;
	
	public CommandInterpreter(String command, SlogoParser parser) throws ParserException{
		
		myParser = parser;
		
		System.out.println(command);
		
		NativeTranslator translator = new NativeTranslator();
		myCommandList = translator.buildCommandList(command);
		
		myRoot = new CommandTree(myCommandList.copy(), myParser);
		myRoot.build();
		myRoot.evaluate();
		
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
	
	public static Evaluable reflect(String raw, CommandTree tree, SlogoParser parser) throws ParserException{
		String className = "parser.command.commandlist." + raw + "Command";
		Evaluable command = instantiateClass(className);
	
		command.setParameters(tree, parser);
		System.out.println("I just set parameters for real");
		
		return command;
	}

	private static Evaluable instantiateClass(String className) throws ParserException {
		Class c = loadClass(className);
		Evaluable command = null;
		try {
		    command = (Evaluable)c.newInstance();
		} catch (InstantiationException e) {
		    e.printStackTrace();
		    throw new ParserException("Unable to instantiate command");
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		    throw new ParserException("Unable to instantiate command");
		}
		return command;
	}

	private static Class loadClass(String className) throws ParserException {
		Class c = null;
		try{
			c = Class.forName(className);
		}
		catch(ClassNotFoundException e){
			throw new ParserException(className + " not found!");
		}
		return c;
	}
	
}
