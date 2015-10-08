package parser.command;

import java.util.Collections;
import java.util.List;

public class CommandString {

	private List<String> mySplitString;
	private List<String> myTypeString;
	
	public CommandString(List<String> splitString, List<String> typeString){
		mySplitString = splitString;
		myTypeString = typeString;
	}
	
	public List<String> getSplitString(){
		return Collections.unmodifiableList(mySplitString);
	}
	
	public List<String> getTypeString(){
		return Collections.unmodifiableList(myTypeString);
	}
	
	public String toString(){
		return mySplitString.toString() + "\n" + myTypeString.toString();
	}
	
}
