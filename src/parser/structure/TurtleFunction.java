package parser.structure;

import parser.ParserException;

@FunctionalInterface
public interface TurtleFunction<T, R> {

	public R apply(T t) throws ParserException;
}
