package tests;
import parser.ParserException;

public class ParserTest {

	public static void main(String[] args){
		try {
			func1();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			System.out.println("EXCEPTION CAUGHT");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void func1() throws ParserException{
		
		try
		{ 
			func2();
		}
		catch(ParserException e){
			System.out.println("I am going to add to the exception.");
			throw new ParserException(e.getMessage() + " Here's some more text.");
			//throw new ParserException("THIS IS MY NEW TEXT");
		}
	}
	
	public static void func2() throws ParserException{
		func3();
		
		throw new ParserException("LOLOL");
	}
	
	public static void func3() throws ParserException{
		System.out.println("THROWING");
		if(true){
			throw new ParserException("IM AN EXCEPTION YAY!");
		}
		else{
			System.out.println("I SHOULD NOT PRINT");
		}
	}
}
