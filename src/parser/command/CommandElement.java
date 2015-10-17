package parser.command;

import java.util.Arrays;
import java.util.List;

public class CommandElement {

	private String myRawText;
	private String myType;
	private String myNativeCommand;
	
	private String mySeparator;
	
	public CommandElement(String s){
		myRawText = s;
		myType = "";
		myNativeCommand = "";
		mySeparator = "";
	}
	
	public CommandElement(List<String> commandList){
		myRawText = commandList.get(0);
		myType = commandList.get(1);
		myNativeCommand = commandList.get(2);
	}
	
	@Override
	public String toString(){
		return "\n" + Arrays.asList(myRawText, myType, myNativeCommand).toString();
	}
	
	public void setType(String type){
		myType = type;
	}
	
	public void setNativeCommand(String command){
		myNativeCommand = command;
	}

	public String getRawText(){
		return myRawText;
	}
	
	public String getType(){
		return myType;
	}
	
	public String getNativeCommand(){
		return myNativeCommand;
	}
	
}
