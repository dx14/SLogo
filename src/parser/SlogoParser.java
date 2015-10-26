package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observer;

import controller.SlogoController;
import parser.command.CommandInterpreter;
import parser.command.CommandList;
import parser.resource.ResourceParser;
import parser.structure.CommandContainer;
import parser.structure.Turtle;
import parser.structure.SimpleTurtle;
import parser.structure.TurtleContainer;
import parser.structure.VariableContainer;

// TODO: add save file

// TODO: implement controller functions
public class SlogoParser implements ParserInterface{

	private TurtleContainer myTurtleContainer;
	private VariableContainer myVariableContainer;
	private CommandContainer myCommandContainer;
	
	private ResourceParser myResourceParser;
	private CommandInterpreter myCommandInterpreter;
	
	private SlogoController myController;
	
//	public static void main(String args[]) throws ParserException{
//		
//		SlogoParser p = new SlogoParser(null);
//		
//		//p.runCommand("to walk [ :turns ] [ repeat :turns [ forward 50 2 ] ] walk walk 3");
//		//p.loadCommand("examples/procedures_with_parameters/random_range.logo");
//		//p.loadCommand("examples/simple/forward_complex.logo");
//		//p.runCommand("tell [ 1 2 3 10 ] fd 10 setheading 50 askwith [ equal? ycor 0 ] [ fd 50 ]");
//		p.runCommand("make :iteration 5 to recurse [ :testvar ] [ fd :iteration make :localvar :iteration make :iteration sum :iteration -1 if [ greater? :iteration 0 ] [ recurse :iteration ] ] recurse :iteration");
//		//p.runCommand("make :i 5 to test [ ] [ make :i 4 ] test");
//		p.getVariableContainer().debug();
//		p.getCommandContainer().debug();
//		p.getTurtleContainer().debug();
//	}
	
	public SlogoParser(SlogoController controller){
		myController = controller;
		myTurtleContainer = new TurtleContainer();
		myVariableContainer = new VariableContainer();
		myCommandContainer = new CommandContainer();
		myResourceParser = new ResourceParser();
		myCommandInterpreter = new CommandInterpreter(this, myResourceParser);
	}
	
	public Turtle getCurrentTurtle(){
		return myTurtleContainer.getCurrentTurtle();
	}
	
	public VariableContainer getVariableContainer(){
		return myVariableContainer;
	}
	
	public CommandContainer getCommandContainer(){
		return myCommandContainer;
	}
	
	public TurtleContainer getTurtleContainer() {
		return myTurtleContainer;
	}
	
	public void loadCommand(String filename) throws ParserException {
		try {
			runCommand(Files.lines(Paths.get(filename))
				.reduce((s1, s2) -> s1 + "\n" + s2).orElseGet(() -> ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ParserException("Error: cannot load file " + filename);
		}
	}
	
	@Override
	public void runCommand(String command) throws ParserException {
		myCommandInterpreter.interpret(command);
	}

	@Override
	public void runCommand(CommandList command) throws ParserException {
		myCommandInterpreter.interpret(command);
	}

	@Override
	public void setLanguage(String language) throws ParserException {
		myResourceParser.setLanguage(language);
	}
	
	public double setBackgroundColor(double color){
		myController.setBackgroundColor(color);
		return color;
	}
	
	public double setPaletteColor(double index, double r, double g, double b){
		myController.setPaletteColor(((Double)index).intValue(), ((Double)r).intValue()%255, ((Double)g).intValue()%255, ((Double)b).intValue()%255);
		return index;
	}
	
	public double clearStamps(){
		myController.clearStamps();
		return 1;
	}
	
	public void setVariableContainer(VariableContainer container){
		myVariableContainer = container;
	}
	
	@Override
	public void addVariableObserver(Observer o){
		myVariableContainer.addObserver(o);
	}
	
	@Override
	public void addCommandObserver(Observer o){
		myCommandContainer.addObserver(o);
	}
	
	@Override
	public void addTurtleObserver(Observer o){
		myTurtleContainer.addObserver(o);
		myTurtleContainer.update();
	}

	@Override
	public void debug() {
		myVariableContainer.debug();
		myCommandContainer.debug();
		myTurtleContainer.debug();
	}

	@Override
	public void outputCommandContainer(String filename) throws ParserException {
		// TODO Auto-generated method stub
		
	}
}
