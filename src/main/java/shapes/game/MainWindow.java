package shapes.game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private int canvasSize = 500;
    private int controlAreaWidth = 200;
    private int controlAreaHeight = 500;

    public static void main(String[] args) {
        launch(args);
    }

    private void moveRight(Shape shape, int[] newX) {
        newX[0] += 10;
        shape.setTranslateX(newX[0]);
    }

    private void moveLeft(Shape shape, int[] newX) {
        newX[0] -= 10;
        shape.setTranslateX(newX[0]);
    }

    private void moveUp(Shape shape, int[] newY) {
        newY[0] -= 10;
        shape.setTranslateY(newY[0]);
    }

    private void moveDown(Shape shape, int[] newY) {
        newY[0] += 10;
        shape.setTranslateY(newY[0]);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        vbox.setMinSize(canvasSize / 2, canvasSize / 2);

        GridPane gp = new GridPane();
        //Creating a Grid Pane
        gp.setMinSize(canvasSize / 2, canvasSize / 2);

        //Setting the padding
        gp.setPadding(new Insets(10, 10, 10, 10));

        //Setting the Grid alignment
        gp.setAlignment(Pos.CENTER);

        final Circle circle = new Circle();
        circle.setCenterX(10.0f);
        circle.setCenterY(10.0f);
        circle.setRadius(50.0f);

        gp.add(circle, 50, 50);

        vbox.getChildren().add(gp);
        vbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setCenter(vbox);

        root.setBottom(controlPanel(primaryStage, circle));
        final Scene sceneMain = new Scene(root, canvasSize, canvasSize);
        primaryStage.setScene(sceneMain);
        primaryStage.show();
    }

    private HBox controlPanel(Stage primaryStage, Shape shape) {
        HBox controlGroup = new HBox();

        //Creating a Grid Pane
        GridPane rootPane = new GridPane();
        //Setting size for the pane
        rootPane.setMinSize(controlAreaHeight, controlAreaWidth);

        //Creating a Grid Pane
        GridPane buttonPane = new GridPane();
        //Setting the padding
        buttonPane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the Grid alignment
        buttonPane.setAlignment(Pos.CENTER);

        int[] newX = new int[1];
        int[] newY = new int[1];

        Button buttonMoveRight = new Button();
        buttonMoveRight.setText("Right");
        buttonMoveRight.setOnAction(e -> moveRight(shape, newX));

        Button buttonMoveLeft = new Button();
        buttonMoveLeft.setText("Left");
        buttonMoveLeft.setOnAction(e -> moveLeft(shape, newX));

        Button buttonMoveUp = new Button();
        buttonMoveUp.setText("Up");
        buttonMoveUp.setOnAction(e -> moveUp(shape, newY));

        Button buttonMoveDown = new Button();
        buttonMoveDown.setText("Down");
        buttonMoveDown.setOnAction(e -> moveDown(shape, newY));

        buttonPane.add(buttonMoveUp,1, 0);
        buttonPane.add(buttonMoveLeft, 0, 1);
        buttonPane.add(buttonMoveRight, 2, 1);
        buttonPane.add(buttonMoveDown, 1, 2);

        GridPane textPane = new GridPane();
        textPane.add(new TextField(),1, 0);

        textPane.setAlignment(Pos.CENTER);

        rootPane.add(buttonPane, 0, 0);
        rootPane.add(textPane, 1, 1);

        controlGroup.getChildren().add(rootPane);
        return controlGroup;
    }

}
