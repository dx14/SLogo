package parser.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandList {

	private List<CommandElement> myCommandList;
	
	public CommandList(List<CommandElement> commandList){
		myCommandList = commandList;
	}
	
	public String toString(){
		return myCommandList.toString();
	}
	
	public void remove(int index){
		myCommandList.remove(index);
	}
	

	public CommandList copy(){
		return new CommandList(new ArrayList<>(myCommandList));
	}

	
	public boolean isEmpty(){
		return myCommandList.isEmpty();
	}
	
	// TODO: implement object type that contains all three implementations (maybe a map)
	
	public String getNativeCommand(int index){
		return myCommandList.get(index).getNativeCommand();
	}
	
	public String getRawCommand(int index){
		return myCommandList.get(index).getRawText();
	}
	
	public String getCommandType(int index){
		return myCommandList.get(index).getType();
	}
	
}
