package parser.command;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;

import parser.ParserException;
import parser.SlogoParser;
import parser.resource.ResourceParser;

public class CommandInterpreter {

	private CommandList myCommandList;
	private CommandTreeNode myRoot;
	private SlogoParser myParser;
	private ResourceParser myResourceParser;
	
	private static final ArrayList<String> commandTypes = new ArrayList<>(Arrays.asList("turtlecommand.", "turtlequery.", "math.", "booleancommand.", "control.", "display.", "turtles.", "syntax."));

	
	public CommandInterpreter(SlogoParser parser, ResourceParser resourceParser){
		
		myParser = parser;
		myResourceParser = resourceParser;
		
	}
	
	public void interpret(String command) throws ParserException{
		//System.out.println(command);
		
		myCommandList = myResourceParser.buildCommandList(command);
		
		interpret(myCommandList);
	}
	
	public void interpret(CommandList command) throws ParserException{
		//System.out.println(command);
		
		myRoot = new CommandTreeNode(myCommandList.copy(), myParser);
		myRoot.build();
		//System.out.println(myRoot.toString());
		
		myRoot.evaluate();
	}
	
	public static Evaluable reflect(CommandElement ce, CommandTreeNode tree, SlogoParser parser) throws ParserException{
		
		String baseName = "parser.command.commandlist.";
		Evaluable command = null;
		for(String ctype : commandTypes){
			try{
				command = instantiateClass(baseName + ctype + ce.getNativeCommand());
				break;
			}
			catch(ParserException e){
				continue;
			}
		}
		if(command==null){
			throw new ParserException("ERROR: Don't know how to " + ce.getRawText());
		}
		
		command.setParameters(tree, parser, ce);
		return command;
	}

	@SuppressWarnings("rawtypes")
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
	
	@SuppressWarnings("rawtypes")
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
