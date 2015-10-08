package parser.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import parser.ParserException;
import parser.command.CommandString;

public class NativeTranslator {

	private ResourceBundle syntaxResources = ResourceBundle.getBundle("resources.languages.Syntax");
	
	public static final String NOTFOUND = "notfound";
	
	public static void main(String args[]) throws ParserException{
		NativeTranslator t = new NativeTranslator();
		//t.buildCommandString("LOL\nIM A STRING\n\nTESTING123\n\n\ntest");
		String command = "#I'm a comment haha lololol\n" +
				"fd 50\n" +
				":variable\n";
			CommandString s = t.buildCommandString(command);
				System.out.println(s);
	}
	
	public CommandString buildCommandString(String command) throws ParserException{		
		// workaround for now, fix later
		command = removeComments(command);
		
		ArrayList<String> splitString = new ArrayList<>(Arrays.asList(command.split("\\s")));
		ArrayList<String> typeString = new ArrayList<>();
		
		labelSyntax(splitString, typeString);
		validateTypes(splitString, typeString);
		
		return new CommandString(splitString, typeString);
	}
	
	private String removeComments(String command){
		return command.replaceAll(syntaxResources.getString("Comment"), "");
	}
	
	private void labelSyntax(List<String> splitString, List<String> typeString){
		List<String> syntaxTypes = Collections.list(syntaxResources.getKeys());
		
		for(int i=0; i<splitString.size(); i++){ typeString.add(NOTFOUND); }
		
		for(String type : syntaxTypes){
			for(int i = 0; i<splitString.size(); i++){
				if(splitString.get(i).matches(syntaxResources.getString(type))){
					typeString.set(i, type);
				}
			}
		}
	}
	
	private void validateTypes(List<String> splitString, List<String> typeString) throws ParserException{
		for(int i=0; i<typeString.size(); i++){
			if(typeString.get(i).equals(NOTFOUND)){
				throw new ParserException("ERROR: Unrecognized string " + splitString.get(i));
			}
		}
	}
	
}
