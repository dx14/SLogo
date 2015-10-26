package gui;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import util.SlogoPath;


public class GUITurtleAreaDrawer extends GUIComponent {
    private double xCanvas, yCanvas;
    private GraphicsContext gc;
    private Canvas canvas;

    public GUITurtleAreaDrawer (double width, double height) {
        xCanvas = width;
        yCanvas = height;
        canvas = new Canvas(xCanvas, yCanvas);
        gc = canvas.getGraphicsContext2D();
    }

    public void drawBackground (Color backgroundColor) {
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, xCanvas, yCanvas);
    }

    public void drawTurtleImage (GUITurtle turtle, Image image) {
        gc.save();
        Double[] offsetCoords =
                findImageCenter(turtle.getCoordinate().getX(), -1 * turtle.getCoordinate().getY(),
                                image);
        Double[] offsetguiCoords = realToGUICoordinates(offsetCoords[0], offsetCoords[1]);
        Double[] guiCoords =
                realToGUICoordinates(turtle.getCoordinate().getX(),
                                     -1 * turtle.getCoordinate().getY());
        setGCTransform(turtle.getHeading(), guiCoords[0], guiCoords[1]);
        gc.drawImage(image, offsetguiCoords[0], offsetguiCoords[1]);
        gc.restore();
    }

    public void drawPath (SlogoPath path, Color color) {
        Double[] guiStartCoords =
                realToGUICoordinates(path.getStart().getX(), -1 * path.getStart().getY());
        Double[] guiEndCoords =
                realToGUICoordinates(path.getEnd().getX(), -1 * path.getEnd().getY());
        System.out.println(path.getPen().getColor());
        gc.setStroke(color);
        gc.setLineWidth(path.getPen().getWidth());
        switch (path.getPen().getStyle()) {
            case DOTTED:
                gc.setLineDashes(1, path.getPen().getWidth() * 2);
                gc.setLineCap(StrokeLineCap.ROUND);
                break;
            case DASHED:
                gc.setLineDashes(path.getPen().getWidth() * 2, path.getPen().getWidth());
                gc.setLineCap(StrokeLineCap.BUTT);
                break;
            default:
                gc.setLineDashes(0);
                gc.setLineCap(StrokeLineCap.BUTT);
                break;
        }
        gc.strokeLine(guiStartCoords[0], guiStartCoords[1], guiEndCoords[0], guiEndCoords[1]);
    }

    // ___helper functions___
    private Double[] realToGUICoordinates (double xOnGrid, double yOnGrid) {
        Double[] d = new Double[2];
        d[0] = xOnGrid + xCanvas / 2;
        d[1] = yOnGrid + yCanvas / 2;
        return d;
    }

    private Double[] findImageCenter (double xOnGrid, double yOnGrid, Image image) {
        Double[] d = new Double[2];
        d[0] = xOnGrid - image.getWidth() / 2;
        d[1] = yOnGrid - image.getHeight() / 2;
        return d;
    }

    private void setGCTransform (double angle, double x, double y) {
        Rotate r = new Rotate(angle, x, y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    @Override
    public Node returnNodeToDraw () {
        return canvas;
    }
}
