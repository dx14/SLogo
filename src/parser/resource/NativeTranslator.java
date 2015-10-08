package parser.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import parser.ParserException;
import parser.command.CommandString;

public class NativeTranslator {

	private ResourceBundle syntaxResources = ResourceBundle.getBundle("resources.languages.Syntax");
	private String currentLanguage = "English";
	private ResourceBundle commandResources = ResourceBundle.getBundle("resources.languages." + currentLanguage);
	
	public static final String NOTFOUND = "NOTFOUND";
	public static final String USERDEFINED = "UserDefined";
	
	public CommandString buildCommandString(String command) throws ParserException{		
		// workaround for now, fix later
		command = removeComments(command);
		
		ArrayList<String> commandList = new ArrayList<>(Arrays.asList(command.split("\\s")));
		stripWhiteSpace(commandList);
		ArrayList<String> typeList = new ArrayList<>();
		
		labelSyntax(commandList, typeList);
		validateTypes(commandList, typeList);
		
		return new CommandString(commandList, typeList);
	}
	
	private void stripWhiteSpace(ArrayList<String> commandList) {
		Iterator<String> i = commandList.iterator();
		while(i.hasNext()){
			if(i.next().matches("\\s*")){
				i.remove();			
			}
		}
	}

	public List<String> buildNativeString(CommandString command) throws ParserException{
		ArrayList<String> nativeList = new ArrayList<>();
		
		for(int i = 0; i<command.getTypeString().size(); i++){
			nativeList.add( (command.getTypeString().get(i).equals("Command")) ? getNativeCommand(command.getSplitString().get(i)) : command.getTypeString().get(i) );
		}
		return nativeList;
	}
	
	private String getNativeCommand(String test) {
		List<String> commandNames = Collections.list(commandResources.getKeys());
		String command = USERDEFINED;
		
		for(String s : commandNames){
			if(test.matches(commandResources.getString(s))){
				command = s;
			}
		}
		
		return command;
	}

	private String removeComments(String command){
		return command.replaceAll(syntaxResources.getString("Comment"), "");
	}
	
	private void labelSyntax(List<String> commandList, List<String> typeList){
		List<String> syntaxTypes = Collections.list(syntaxResources.getKeys());
		
		for(int i=0; i<commandList.size(); i++){ typeList.add(NOTFOUND); }
		
		for(String type : syntaxTypes){
			for(int i = 0; i<commandList.size(); i++){
				if(commandList.get(i).matches(syntaxResources.getString(type))){
					typeList.set(i, type);
				}
			}
		}
	}
	
	private void validateTypes(List<String> commandList, List<String> typeList) throws ParserException{
		for(int i=0; i<typeList.size(); i++){
			if(typeList.get(i).equals(NOTFOUND)){
				throw new ParserException("ERROR: Unrecognized string " + commandList.get(i));
			}
		}
	}
	
}
