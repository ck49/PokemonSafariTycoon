/**
 * Created by Aditya on 6/28/2015.
 */


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class View {
    private Canvas canvas;
    public int viewType = 0; //0 = new game screen, 1 = overview screen, 2 = individual zone screen, 3 = pokedex screen
    private Player player;
    private List<Point2D[]> listOfButtons =  new ArrayList<Point2D[]>(); //lower left followed by upper right

    public View(Canvas cvs){
        canvas = cvs;
    }

    public void update(){}

    public void newClick(double X, double Y){}

    public void drawNewGameScreen(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        LinearGradient lg = new LinearGradient(0, 0, 800, 600, true,
                CycleMethod.REFLECT,
                new Stop(0.0, Color.ORANGE),
                new Stop(800.0, Color.VIOLET));
        gc.setFill(lg);
        gc.fillRect(0, 0, 820, 620);
        gc.setFill(Color.ALICEBLUE);
        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);
        gc.fillRect(30, 30, 740, 250);
        gc.strokeRect(30, 30, 740, 250);
        Point2D[] point = {new Point2D.Double(30, 30), new Point2D.Double(740, 250)};
        listOfButtons.add(point);

        gc.setFont(Font.font("Verdana", 30));
        gc.setFill(Color.BLACK);
        gc.strokeText("Click here to start a new game", 50, 70, 750);
        gc.strokeText("Created by ck49 (also known as mgmfa)", 50, 500);

    }




}
