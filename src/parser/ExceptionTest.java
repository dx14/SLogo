package parser;

public class ExceptionTest {

	public static void main(){
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
			System.out.println("I CAUGHT THE EXCEPTION AND IT DIDN'T GO BACK");
		}
	}
	
	public static void func2() throws ParserException{
		func3();
		
		throw new ParserException("LOLOL");
	}
	
	public static void func3() throws ParserException{
		throw new ParserException("IM AN EXCEPTION YAY!");
	}
	
}
