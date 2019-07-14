package shapes.game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;
import static javafx.scene.paint.Color.*;

public class MainWindow extends Application {
    private int canvasSize = 500;
    private int controlAreaWidth = 500;
    private int controlAreaHeight = 200;
    private GridPane vBoxGridPane;
    private Circle shape;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Responsible for creating the UI.
     *
     * @param primaryStage - {@link Stage}
     */
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setCenter(createCanvas());
        root.setBottom(controlPanel());
        final Scene sceneMain = new Scene(root, canvasSize, canvasSize);
        primaryStage.setScene(sceneMain);
        primaryStage.show();
    }

    /**
     * Creates the center part of the application, responsible for rendering shapes.
     *
     * @return new {@link VBox}
     */
    private VBox createCanvas() {
        //Creating the canvas container
        VBox vbox = new VBox();
        vbox.setMinSize(canvasSize / 2, canvasSize / 2);

        // -------------------- Grid Setup --------------------
        //Creating a Grid Pane
        vBoxGridPane = new GridPane();
        //Setting a minimum size
        vBoxGridPane.setMinSize(canvasSize / 2, canvasSize / 2);
        //Setting the padding
        vBoxGridPane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the Grid alignment
        vBoxGridPane.setAlignment(CENTER);

        vbox.getChildren().add(vBoxGridPane);
        vbox.setBackground(createNewBackgroundColor(CORNFLOWERBLUE));
        return vbox;
    }

    /**
     * Helper method for easy background color creation.
     *
     * @param color - color constant from {@link javafx.scene.paint.Color}
     * @return new {@link Background}
     */
    private Background createNewBackgroundColor(Color color) {
        return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
    }

    /**
     * Creates new shape.
     *
     * @return new {@link Shape}
     */
    private Shape createShape() {
        //TODO make generic implementation
        shape = new Circle();
        shape.setCenterX(10.0f);
        shape.setCenterY(10.0f);
        shape.setRadius(50.0f);
        return shape;
    }

    /**
     * Creates the bottom part of the application, responsible for creating and manipulating shapes.
     *
     * @return new {@link HBox}
     */
    private HBox controlPanel() {
        //Creating the control group container
        HBox hBox = new HBox();

        // -------------------- Grid Setup --------------------
        //Creating the root Grid Pane
        GridPane rootPane = new GridPane();
        //Root control pane setup
        //Setting a new temporary background TODO only for visual aid in debug
        rootPane.setBackground(createNewBackgroundColor(BEIGE));
        rootPane.setMinSize(controlAreaWidth, controlAreaHeight);

        // -------------------- Position Setup --------------------
        //Setting position for child panes
        rootPane.add(createShapeControlPane(), 0, 0);
        rootPane.add(createShapeManipulationPane(), 1, 0);

        hBox.getChildren().add(rootPane);
        return hBox;
    }

    /**
     * Creates the UI responsible for allowing the user to choose the desired shape.
     *
     * @return new {@link GridPane} containing labels, text fields and buttons for shape creation
     */
    private GridPane createShapeControlPane() {
        // -------------------- Grid Setup --------------------
        //Creating shape control pane
        GridPane shapeControlPane = new GridPane();
        //Shape control pane setup
        shapeControlPane.setBackground(createNewBackgroundColor(GRAY));
        shapeControlPane.setPrefSize(controlAreaWidth / 2, controlAreaHeight);
        shapeControlPane.setVgap(10);
        shapeControlPane.setHgap(20);
        shapeControlPane.setAlignment(CENTER);

        // -------------------- Child Node Setup --------------------
        //TODO Rename text and label fields
        TextField text1 = new TextField();
        text1.setMaxWidth(100);
        TextField text2 = new TextField();
        text2.setMaxWidth(100);
        TextField text3 = new TextField();
        text3.setMaxWidth(100);
        TextField text4 = new TextField();
        text4.setMaxWidth(100);
        text1.getText();

        Label label1 = new Label("Circle");
        Label label2 = new Label("Square");
        Label label3 = new Label("Triangle");
        Label label4 = new Label("Trapeze");

        Button createShape = new Button();
        createShape.setText("CREATE");
        createShape.setOnAction(e -> vBoxGridPane.add(createShape(), 0, 0));
        //TODO add missing buttons
        shapeControlPane.add(label1, 0, 0);
        shapeControlPane.add(text1, 1, 0);
        shapeControlPane.add(createShape, 2, 0);

        // -------------------- Position Setup --------------------
        //Setting position for child nods
        shapeControlPane.add(text2, 1, 1);
        shapeControlPane.add(label2, 0, 1);
        shapeControlPane.add(text3, 1, 2);
        shapeControlPane.add(label3, 0, 2);
        shapeControlPane.add(text4, 1, 3);
        shapeControlPane.add(label4, 0, 3);
        return shapeControlPane;
    }

    /**
     * Creates the UI responsible for allowing the user to manipulate the created shape, by changing it's position
     * (Ex.: move up, move down, move left, move right).
     *
     * @return new {@link GridPane} containing direction buttons
     */
    private GridPane createShapeManipulationPane() {
        // -------------------- Grid Setup --------------------
        //Creating a Grid Pane
        GridPane buttonPane = new GridPane();
        buttonPane.setBackground(createNewBackgroundColor(GHOSTWHITE));
        buttonPane.setPrefSize(controlAreaWidth / 2, controlAreaHeight);
        //Setting the padding
        buttonPane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the Grid alignment
        buttonPane.setAlignment(CENTER);

        // -------------------- Child Node Setup --------------------
        //Holds the latest X position
        int[] newX = new int[1];
        //Holds the latest Y position
        int[] newY = new int[1];

        //Creating and setting action for "right" button
        Button buttonMoveRight = new Button();
        buttonMoveRight.setText("Right");
        buttonMoveRight.setOnAction(e -> moveRight(shape, newX));
        buttonMoveRight.setMaxWidth(Double.MAX_VALUE);

        //Creating and setting action for "left" button
        Button buttonMoveLeft = new Button();
        buttonMoveLeft.setText("Left");
        buttonMoveLeft.setOnAction(e -> moveLeft(shape, newX));
        buttonMoveLeft.setMaxWidth(Double.MAX_VALUE);

        //Creating and setting action for "up" button
        Button buttonMoveUp = new Button();
        buttonMoveUp.setText("Up");
        buttonMoveUp.setOnAction(e -> moveUp(shape, newY));
        buttonMoveUp.setMaxWidth(Double.MAX_VALUE);

        //Creating and setting action for "down" button
        Button buttonMoveDown = new Button();
        buttonMoveDown.setText("Down");
        buttonMoveDown.setOnAction(e -> moveDown(shape, newY));
        buttonMoveDown.setMaxWidth(Double.MAX_VALUE);

        // -------------------- Position Setup --------------------
        //Setting position for buttons
        buttonPane.add(buttonMoveUp, 1, 0);
        buttonPane.add(buttonMoveLeft, 0, 1);
        buttonPane.add(buttonMoveRight, 2, 1);
        buttonPane.add(buttonMoveDown, 1, 2);
        return buttonPane;
    }

    /**
     * Button action that moves the given shape to the right, by changing it's current
     * X value by the default amount.
     *
     * @param shape    - {@link Shape}
     * @param currentX - the current X position of the given shape
     */
    private void moveRight(Shape shape, int[] currentX) {
        currentX[0] += 10;
        shape.setTranslateX(currentX[0]);
    }

    /**
     * Button action that moves the given shape to the left, by changing it's current
     * X value by the default amount.
     *
     * @param shape    - {@link Shape}
     * @param currentX - the current X position of the given shape
     */
    private void moveLeft(Shape shape, int[] currentX) {
        currentX[0] -= 10;
        shape.setTranslateX(currentX[0]);
    }

    /**
     * Button action that moves the given shape up, by changing it's current
     * Y value by the default amount.
     *
     * @param shape    - {@link Shape}
     * @param currentY - the current Y position of the given shape
     */
    private void moveUp(Shape shape, int[] currentY) {
        currentY[0] -= 10;
        shape.setTranslateY(currentY[0]);
    }

    /**
     * Button action that moves the given shape down, by changing it's current
     * Y value by the default amount.
     *
     * @param shape    - {@link Shape}
     * @param currentY - the current Y position of the given shape
     */
    private void moveDown(Shape shape, int[] currentY) {
        currentY[0] += 10;
        shape.setTranslateY(currentY[0]);
    }
}
