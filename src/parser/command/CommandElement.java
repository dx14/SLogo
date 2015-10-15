package parser.command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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
	
//	public void setType(Function<CommandElement, String> f){
//		myType = f.apply(this);
//	}
//	
//	public void setNativeCommand(Function<CommandElement, String> f){
//		myNativeCommand = f.apply(this);
//	}
//	
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
