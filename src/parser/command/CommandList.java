package parser.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parser.ParserException;

public class CommandList {

	private List<CommandElement> myCommandList;
	String rawString;
	
	public CommandList(List<CommandElement> commandList){
		myCommandList = commandList;
	}
	
	public String toString(){
		return myCommandList.toString();
	}
	
	public void remove(int index){
		myCommandList.remove(index);
	}
	
	public CommandElement get(int index){
		return myCommandList.get(index);
	}
	
	public void insert(int index, CommandElement element){
		myCommandList.add(index, element);
	}
	

	public CommandList copy(){
		return new CommandList(new ArrayList<>(myCommandList));
	}

	
	public boolean isEmpty(){
		return myCommandList.isEmpty();
	}
		
	public String getNativeCommand(int index){
		return myCommandList.get(index).getNativeCommand();
	}
	
	public String getRawCommand(int index){
		return myCommandList.get(index).getRawText();
	}
	
	public String getCommandType(int index){
		return myCommandList.get(index).getType();
	}
	
	public CommandList initialize(){
		myCommandList.add(0, new CommandElement(Arrays.asList("root", "Command", "RootCommand")));
		return this;
	}
	
	public CommandList validate(String invalid) throws ParserException{
		for(CommandElement c : myCommandList){
			if(c.getType().equals(invalid)){
				throw new ParserException("Command not found: " + c.getRawText());
			}
		}
		return this;
	}
	
}
