package util;

public abstract class SlogoPath {

	private Coordinate start;
	private Coordinate end;
	
	
	public SlogoPath(Coordinate start, Coordinate end){
		this.start = start;
		this.end = end;
	}
	
	public Coordinate getStart(){
		return start;
	}
	
	public Coordinate getEnd(){
		return end;
	}

	@Override
	public String toString(){
		return "Path from: " + start + " to " + end;
	}
}
