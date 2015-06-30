/**
 * Created by Aditya on 6/28/2015.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Main extends Application{
    private Canvas canvas;
    private Player player;
    private View view;

    public static void main(String[] args) {
        launch(args);
    }



    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        canvas = new Canvas(800, 600);
        root.getChildren().add(canvas);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("basics.fxml"));
        player = new Player();
        view = new View(canvas);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Dungeon Crawler");
        Scene scene = new Scene(root, 800, 600);

        EventHandler handler = new EventHandler<MouseEvent>() {
            public void handle (MouseEvent mouseEvent) {
                view.newClick(mouseEvent.getSceneX(), mouseEvent.getSceneY());

            }
        };
        scene.setOnMouseClicked(handler);
        view.drawNewGameScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
