package gui.modelinterface;

import parser.ParserException;
import util.LineStyle;


public interface GUIPen {

    boolean isDown ();

    double getWidth ();

    void setWidth (double width) throws ParserException;

    int getColor ();

    void setColor (int color);

    LineStyle getStyle ();

    void setStyle (LineStyle style);
}
