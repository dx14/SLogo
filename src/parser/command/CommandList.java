package parser.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandList {

	private List<String> myRawCommandList;
	private List<String> myTypeList;
	private List<String> myNativeList;
	
	public CommandList(List<String> commandList, List<String> typeList, List<String> nativeList){
		myRawCommandList = commandList;
		myTypeList = typeList;
		myNativeList = nativeList;
	}
	
	public List<String> getRawCommandString(){
		return Collections.unmodifiableList(myRawCommandList);
	}
	
	public List<String> getTypeString(){
		return Collections.unmodifiableList(myTypeList);
	}
	
	public List<String> getNativeCommandString(){
		return Collections.unmodifiableList(myNativeList);
	}
	
	public String toString(){
		return myRawCommandList.toString() + "\n" + myTypeList.toString();
	}
	
	public void remove(int index){
		myRawCommandList.remove(index);
		myTypeList.remove(index);
		myNativeList.remove(index);
	}
	

	public CommandList copy(){
		return new CommandList(new ArrayList<String>(myRawCommandList), new ArrayList<String>(myTypeList), new ArrayList<String>(myNativeList));
	}

	
	// TODO: implement object type that contains all three implementations (maybe a map)
	
	public String getNativeCommand(int index){
		return myNativeList.get(index);
	}
	
	public String getRawCommand(int index){
		return myRawCommandList.get(index);
	}
	
	public String getCommandType(int index){
		return myTypeList.get(index);
	}
	
}
