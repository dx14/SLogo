package parser.command;

import java.lang.reflect.Constructor;
import java.util.List;

import parser.ParserException;
import parser.SlogoParser;
import parser.resource.NativeTranslator;

public class CommandInterpreter {

	private CommandList myCommandList;
	private CommandTreeNode myRoot;
	private SlogoParser myParser;
	
	public CommandInterpreter(String command, SlogoParser parser) throws ParserException{
		
		myParser = parser;
		
		System.out.println(command);
		
		NativeTranslator translator = new NativeTranslator();
		myCommandList = translator.buildCommandList(command);
		
		myRoot = new CommandTreeNode(myCommandList.copy(), myParser);
		myRoot.build();
		
		myRoot.evaluate();
		
		
		
	}
	
	public static Evaluable reflect(String raw, CommandTreeNode tree, SlogoParser parser) throws ParserException{
		String className = "parser.command.commandlist." + raw + "Command";
		Evaluable command = instantiateClass(className);
	
		
		
		command.setParameters(tree, parser);
		System.out.println("Parameters set.");

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
	
	private static Evaluable instantiateClass(String className) throws ParserException {
		Class c = loadClass(className);
		Class[] types = { CommandTreeNode.class, SlogoParser.class };
		Constructor constructor;
		try{
		constructor = Class.forName(className).getDeclaredConstructor(types);
		}
		catch(Exception e)
		{
			
		}
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
	
}
