package parser.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import parser.ParserException;
import parser.command.CommandList;
import parser.command.CommandElement;

public class ResourceParser {

	private ResourceBundle syntaxResources;
	private ResourceBundle commandResources;
	
	private static final ResourceBundle LANGUAGELIST = ResourceBundle.getBundle("resources.config.Languages");
	
	private String myCurrentLanguage;
	private List<String> mySyntaxTypes;
	private List<String> myCommandNames;
	private List<String> myLanguages;
	
	public static final String NOTFOUND = "NOTFOUND";
	public static final String USERDEFINED = "UserDefinedCommand";
	
	public ResourceParser(){
		myCurrentLanguage = loadLanguages().get(0);
		reload();
	}
	
	private List<String> loadLanguages(){
		myLanguages = Collections.list(LANGUAGELIST.getKeys()).stream()
				.map((s) -> LANGUAGELIST.getString(s))
				.collect(Collectors.toList());
		return myLanguages;
	}
	
	public ResourceParser(String language){
		loadLanguages();
		myCurrentLanguage = language;
		reload();
	}
	
	public CommandList buildCommandList(String command) throws ParserException{		
		List<CommandElement> commandList = Arrays.asList(command.split("\n+")).stream()
				.map( s -> s.trim() )
				.filter( s -> !s.matches(syntaxResources.getString("Comment")) )
				.flatMap( s -> Arrays.asList(s.split("\\s+")).stream() )
				.filter( s -> !s.matches("\\s*") )
				.map( CommandElement :: new )
				.map( this::translate )
				.collect( Collectors.toList() );
		
		return new CommandList(commandList).validate(NOTFOUND).initialize();
	}
	
	private CommandElement translate(CommandElement c){
		c.setType(getType(c.getRawText()));
		c.setNativeCommand(c.getType().equals("Command") ? getNativeCommand(c.getRawText()) : (c.getType()+"Command"));
		return c;
	}
	
	private String getType(String s){
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
	
	private String getNativeCommand(String raw) {
		for(String s : myCommandNames){
			if(raw.matches(commandResources.getString(s)))
				return s + "Command";
		}
		return USERDEFINED;
	}
	
	public void setLanguage(String language) throws ParserException{
		if(!myLanguages.contains(language)){
			throw new ParserException("Error: don't know language " + language);
		}
		else {
			myCurrentLanguage = language;
			reload();
		}
	}
	
	private void reload(){
		syntaxResources = ResourceBundle.getBundle("resources.languages.Syntax");
		commandResources = ResourceBundle.getBundle("resources.languages." + myCurrentLanguage);
		
		mySyntaxTypes = Collections.list(syntaxResources.getKeys());
		myCommandNames = Collections.list(commandResources.getKeys());
	}
	

}
