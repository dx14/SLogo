package parser.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import parser.ParserException;
import parser.command.CommandList;
import parser.command.CommandElement;

public class NativeTranslator {

	private ResourceBundle syntaxResources = ResourceBundle.getBundle("resources.languages.Syntax");
	private String currentLanguage = "English";
	private ResourceBundle commandResources = ResourceBundle.getBundle("resources.languages." + currentLanguage);
	
	private List<String> mySyntaxTypes;
	private List<String> myCommandNames;
	
	public static final String NOTFOUND = "NOTFOUND";
	public static final String USERDEFINED = "UserDefinedCommand";
	
	public CommandList buildCommandList(String command) throws ParserException{		
		command = removeComments(command);
		
		mySyntaxTypes = Collections.list(syntaxResources.getKeys());
		myCommandNames = Collections.list(commandResources.getKeys());
		
		ArrayList<String> rawCommands = new ArrayList<>(Arrays.asList(command.split("\\s")));
		List<CommandElement> commandList = rawCommands.stream()
				.filter(s -> !s.matches("\\s*"))
				.map( CommandElement::new )
				.map(c -> translate(c))
				.collect( Collectors.toList());
		
		validate(commandList);
		commandList.add(0, new CommandElement(Arrays.asList("root", "Command", "RootCommand")));
		CommandList cl = new CommandList(commandList);
		System.out.println(cl);
		return  cl;
	}
	
	public String getType(String s){
		String typeString = NOTFOUND;
		for(String type : mySyntaxTypes){
			if(s.matches(syntaxResources.getString(type)))
			{
				typeString = type;
				break;
			}
		}
		return typeString;
	}
	
	private CommandElement translate(CommandElement c){
		c.setType(getType(c.getRawText()));
		c.setNativeCommand(c.getType().equals("Command") ? getNativeCommand(c.getRawText()) : (c.getType()+"Command"));
		return c;
	}
	
	private void validate(List<CommandElement> commandList) throws ParserException{
		for(CommandElement c : commandList){
			if(c.getType().equals(NOTFOUND)){
				throw new ParserException("Command not found: " + c.getNativeCommand());
			}
		}
	}
	
	private String getNativeCommand(String test) {
				
		String command = USERDEFINED;
		
		for(String s : myCommandNames){
			if(test.matches(commandResources.getString(s))){
				command = s + "Command";
				break;
			}
		}
		
		return command;
	}

	private String removeComments(String command){
		return command.replaceAll(syntaxResources.getString("Comment"), "");
	}
}
