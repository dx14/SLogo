package parser.command;

import java.util.Collections;
import java.util.List;

public class CommandString {

	private List<String> myCommandList;
	private List<String> myTypeList;
	
	public CommandString(List<String> commandList, List<String> typeList){
		myCommandList = commandList;
		myTypeList = typeList;
	}
	
	public List<String> getSplitString(){
		return Collections.unmodifiableList(myCommandList);
	}
	
	public List<String> getTypeString(){
		return Collections.unmodifiableList(myTypeList);
	}
	
	public String toString(){
		return myCommandList.toString() + "\n" + myTypeList.toString();
	}
	
}
