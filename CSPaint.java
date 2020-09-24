/*In order to help learn course concepts, I worked on the homework with Tusheet Goli
discussed homework topics and issues with them.*/
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.util.ArrayList;
/**
 * Class CSPaint models a user friendly graphics editor. Using JavaFX,
 CSPaint uses user inputs to model a graphic interface.
 * @version 2.9
 * @author Tejas Pradeep
 * @since 4-9-2019
 */
public class CSPaint extends Application {
    private Rectangle canvas = null;
    private RadioButton draw = null;
    private RadioButton erase = null;
    private  RadioButton circle = null;
    private  RadioButton ring = null;
    private  RadioButton line = null;
    private  Button clear = null;
    private  Button canvasImage = null;
    private RadioButton selectColor = null;
    private  RadioButton multiColor = null;
    private RadioButton makeColor = null;
    private Slider red = null;
    private Slider green = null;
    private Slider blue = null;
    private TextField canvasColor = null;
    private TextField color = null;
    private  Color canColor = null;
    private Label bottomText = null;
    //private ArrayList<Integer> countCircle = new ArrayList<>();
    private ArrayList<Integer> countDraw = new ArrayList<>();
    private  int countD = 0;
    private  int countE = 0;
    private  ArrayList<Integer> countErase = new ArrayList<>();
    private int countShapes = 0;
    //private  int countShapes = 0;
    private int countLines = 0;
    //private int count_ring = 0;
    //private RadioButton makeColor = null;
    @Override
    /**
     * Method start assigns the stage =, scene and places the nodes.
     * @param primaryStage Parameter primary stage is the stage for the JAVAFX program.
     */
    public void start(Stage primaryStage) {
        VBox toolbar = makeVBox();
        VBox colorBar = makeColorBar();
        canvas = new Rectangle(650, 450);
        canColor = Color.WHITE;
        HBox bottom = makeHbox();
        BorderPane borderpane = new BorderPane();
        borderpane.setBottom(bottom);
        Pane pane = new Pane();
        canColor = getCanvasColor(toolbar);
        canvas.setFill(canColor);
        pane.getChildren().addAll(canvas);
        borderpane.setCenter(pane);
        borderpane.setLeft(toolbar);
        borderpane.setBottom(bottom);
        borderpane.setRight(colorBar);
        toolbarFunc(toolbar, borderpane);
        bottomFunc(bottom, borderpane);
        Scene scene = new Scene(borderpane);
        primaryStage.setTitle("CSPaint");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Method makeVBox(), makes a vertical pane, which is the left toolbar
     * @return vbox A vertical pane which is the toolbar.
     */
    public VBox makeVBox() {
        draw = new RadioButton("Draw");
        draw.setPadding(new Insets(5, 10, 5, 10));
        Label thickness = new Label("Thickness of Pencil :");
        thickness.setPadding(new Insets(5, 10, 5, 10));
        Slider drawThincknes = new Slider(0, 25, 1);
        drawThincknes.setValue(2);
        Label thickness1 = new Label("Thickness of Eraser :");
        thickness.setPadding(new Insets(5, 10, 5, 10));
        drawThincknes.setMajorTickUnit(5);
        drawThincknes.setPadding(new Insets(5, 10, 5, 10));
        drawThincknes.setShowTickMarks(true);
        drawThincknes.setShowTickLabels(true);
        erase = new RadioButton("Erase");
        Label thickness2 = new Label("Radius of Circle or Ring :");
        thickness.setPadding(new Insets(5, 10, 5, 10));
        erase.setPadding(new Insets(5, 10, 5, 10));
        Slider eraseThickness = new Slider(10, 40, 5);
        eraseThickness.setValue(10);
        eraseThickness.setMajorTickUnit(10);
        eraseThickness.setPadding(new Insets(5, 10, 5, 10));
        eraseThickness.setShowTickMarks(true);
        eraseThickness.setShowTickLabels(true);
        line = new RadioButton("Line");
        line.setPadding(new Insets(5, 10, 5, 10));
        circle = new RadioButton("Circle");
        circle.setPadding(new Insets(5, 10, 5, 10));
        Slider cirlceThickness = new Slider(0, 60, 10);
        cirlceThickness.setValue(15);
        cirlceThickness.setMajorTickUnit(15);
        cirlceThickness.setPadding(new Insets(5, 10, 5, 10));
        circle.setPadding(new Insets(5, 10, 5, 10));
        cirlceThickness.setShowTickMarks(true);
        cirlceThickness.setShowTickLabels(true);
        Label canvasLabelColor = new Label("Color of next canvas");
        canvasLabelColor.setPadding(new Insets(5, 10, 5, 10));
        Label canvasImageText = new Label("Link Image to canvas");
        canvasImageText.setPadding(new Insets(5, 10, 5, 10));
        TextField canvasImageField = new TextField("https://i.imgur.com/QRERgmW.jpg");
        canvasImageField.setPadding(new Insets(5, 10, 5, 10));
        canvasColor = new TextField("white");
        canvasColor.setPadding(new Insets(5, 10, 5, 10));
        clear = new Button("Clear Canvas");
        clear.setPadding(new Insets(5, 20, 5, 20));
        canvasImage = new Button("Image as a canvas");
        canvasImage.setPadding(new Insets(5, 10, 5, 10));
        Label empty = new Label(" ");
        VBox vbox = new VBox();
        ring = new RadioButton("Ring");
        ring.setPadding(new Insets(5, 10, 5, 10));
        ToggleGroup group = new ToggleGroup();
        erase.setToggleGroup(group);
        circle.setToggleGroup(group);
        draw.setToggleGroup(group);
        line.setToggleGroup(group);
        ring.setToggleGroup(group);
        Button undoDraw = new Button("Undo Draw");
        Button undoCirlce = new Button("Undo Shape");
        Button undoErase = new Button("Undo Erase");
        undoCirlce.setPadding(new Insets(5, 10, 5, 10));
        undoDraw.setPadding(new Insets(5, 10, 5, 10));
        undoErase.setPadding(new Insets(5, 10, 5, 10));
        vbox.getChildren().addAll(draw, thickness, drawThincknes, erase, thickness1, eraseThickness,
                circle, thickness2, cirlceThickness, ring, canvasLabelColor, canvasColor,
                canvasImageText, canvasImageField, canvasImage, empty, undoDraw, undoErase,
                undoCirlce, clear);
        return vbox;

    }

    /**
     * Method makeColorBar() makes the right toolbar, which it then returns to start.
     * @return vbox A vertical pane which is the right tool bar.
     */
    public VBox makeColorBar() {
        ToggleGroup group = new ToggleGroup();
        selectColor = new RadioButton("Select Color");
        selectColor.setToggleGroup(group);
        selectColor.setPadding(new Insets(5, 10, 5, 10));
        Label colorDraw = new Label("Color of pencil");
        color = new TextField("black");
        color.setPadding(new Insets(5, 10, 5, 10));
        multiColor = new RadioButton("Multi Color");
        multiColor.setToggleGroup(group);
        multiColor.setPadding(new Insets(5, 10, 5, 10));
        makeColor = new RadioButton("Make your own color");
        makeColor.setToggleGroup(group);
        makeColor.setPadding(new Insets(5, 10, 5, 10));
        Label redLabel = new Label("Red Slider: ");
        redLabel.setPadding(new Insets(5, 10, 5, 10));
        red = new Slider(0.0, 255.0, 0.0);
        red.setPadding(new Insets(5, 10, 5, 10));
        red.setShowTickLabels(true);
        red.setShowTickMarks(true);
        red.setMajorTickUnit(50.0);
        Label blueLabel = new Label("Blue Slider: ");
        blueLabel.setPadding(new Insets(5, 10, 5, 10));
        green = new Slider(0.0, 255.0, 0.0);
        green.setPadding(new Insets(5, 10, 5, 10));
        green.setShowTickLabels(true);
        green.setShowTickMarks(true);
        green.setMajorTickUnit(50.0);
        Label greenLabel = new Label("Green Slider: ");
        greenLabel.setPadding(new Insets(5, 10, 5, 10));
        green.setPadding(new Insets(5, 10, 5, 10));
        blue = new Slider(0.0, 255.0, 0.0);
        blue.setPadding(new Insets(5, 10, 5, 10));
        blue.setShowTickLabels(true);
        blue.setShowTickMarks(true);
        blue.setMajorTickUnit(50.0);
        VBox colorBar = new VBox();
        colorBar.getChildren().addAll(selectColor, colorDraw, color, multiColor,
                makeColor, redLabel, red, greenLabel, green, blueLabel, blue);
        return colorBar;

    }

    /**
     * Method makeHBox() makes a horizontal pane which it then returns to the start.
     * @return hbox A horizontal pane which contains the coordinates.
     */
    public HBox makeHbox() {
        bottomText = new Label("");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(bottomText);
        return hbox;
    }

    /**
     * Method to implement functionality of the tool bar
     * @param vbox Toolbar, which is a vertical box.
     * @param borderPane The borderPane that is CSPaint.
     */
    public void toolbarFunc(VBox vbox, BorderPane borderPane) {
        Button undoDraw = (Button) vbox.getChildren().get(16);
        undoDraw.setOnAction(e -> {
                ObservableList al = ((Pane) borderPane.getCenter()).getChildren();
                if (countDraw.size() > 0) {
                    int limit = countDraw.get(countDraw.size() - 1);
                    countDraw.remove(countDraw.size() - 1);
                    al.remove(al.size() - limit, al.size());
                }
            });
        Button undoErase = (Button) vbox.getChildren().get(17);
        undoErase.setOnAction(e -> {
                ObservableList al = ((Pane) borderPane.getCenter()).getChildren();
                if (countErase.size() > 0) {
                    int limit = countErase.get(countErase.size() - 1);
                    countErase.remove(countErase.size() - 1);
                    al.remove(al.size() - limit, al.size());
                }
            });
        Button undoCircle = (Button) vbox.getChildren().get(18);
        undoCircle.setOnAction(e -> {
                ObservableList al = ((Pane) borderPane.getCenter()).getChildren();
                if (al.size() - 1 > 0 && countShapes > 0) {
                    al.remove(al.size() - 1);
                    countShapes--;
                }
            });
//        Button undo_line = (Button)vbox.getChildren().get(22);
//        undo_line.setOnAction( e -> {
//            ObservableList al = ((Pane)borderPane.getCenter()).getChildren();
//            if (al.size() - 1 > 0) {
//                al.remove(al.size() - 1);
//                countLines--;
//            }
//        });
//        Button undo_ring = (Button)vbox.getChildren().get(19);
//        undo_ring.setOnAction( e -> {
//            ObservableList al = ((Pane)borderPane.getCenter()).getChildren();
//            if (al.size() - 1 > 0 && count_ring > 0) {
//                al.remove(al.size() - 1);
//                count_ring--;
//            }
//        });
        clear = (Button) vbox.getChildren().get(19);
        clear.setOnAction(e -> {
            System.out.println("Hi");
                Rectangle r = new Rectangle(650, 450);
                r.setFill(getCanvasColor(vbox));
                ((Pane) borderPane.getCenter()).getChildren().add(r);
            //countCircle.clear();
                countDraw.clear();
                countErase.clear();
            //countShapes = 0;
                countLines = 0;
                countShapes = 0;
            });
        draw = (RadioButton) vbox.getChildren().get(0);
        Slider drawThickness = (Slider) vbox.getChildren().get(2);
        erase = (RadioButton) vbox.getChildren().get(3);
        Slider eraseThickness = (Slider) vbox.getChildren().get(5);
        //RadioButton randomColor = (RadioButton)vbox.getChildren().get(10);
        ToggleGroup group = draw.getToggleGroup();
        (borderPane.getCenter()).setOnMouseDragged(event -> {
                String coordinates = String.format("(%.2f, %.2f)    Number of Shapes: %d ", event.getX(), event.getY(),
                    countShapes, countLines);
                bottomText.setText(coordinates);

            //borderPane.setBottom(hbox)
                //hi
                //bye
                if (draw.isSelected() && group.getSelectedToggle().equals(draw)) {

                    if (inBounds(event.getX(), event.getY(), drawThickness.getValue())) {
                        Circle c = new Circle(event.getX(), event.getY(), drawThickness.getValue());
                        c.setFill(getColor((VBox) borderPane.getRight()));
                        ((Pane) borderPane.getCenter()).getChildren().add(c);
                        countD++;
                        (borderPane.getCenter()).setOnMouseReleased(e -> {
                                countDraw.add(countD);
                                countD = 0;
                            });

                    }
                } else if (erase.isSelected() && group.getSelectedToggle().equals(erase)) {
                    if (inBounds(event.getX(), event.getY(), eraseThickness.getValue())) {
                        Circle c = new Circle(event.getX(), event.getY(), eraseThickness.getValue());
                        c.setFill(getCanvasColor(vbox));
                        ((Pane) borderPane.getCenter()).getChildren().add(c);
                        countE++;
                        (borderPane.getCenter()).setOnMouseReleased(e -> {
                                countErase.add(countE);
                                countE = 0;
                            });
                    }
                }

            });
        //Slider lineThick = (Slider)vbox.getChildren().get(8);
        circle = (RadioButton) vbox.getChildren().get(6);
        ring = (RadioButton) vbox.getChildren().get(9);
        Slider cirlceThickness = (Slider) vbox.getChildren().get(8);
        (borderPane.getCenter()).setOnMouseClicked(event -> {
                if (inBounds(event.getX(), event.getY(), cirlceThickness.getValue())) {
                    if (circle.isSelected() && group.getSelectedToggle().equals(circle)) {
                        Circle c = new Circle(event.getX(), event.getY(), cirlceThickness.getValue(),
                                getColor((VBox) borderPane.getRight()));
                        c.setStroke(getCanvasColor(vbox));
                        c.setCenterX(event.getX());
                        c.setCenterY(event.getY());
                        ((Pane) borderPane.getCenter()).getChildren().add(c);
                        countShapes++;
                    } else if (ring.isSelected() && group.getSelectedToggle().equals(ring)) {
                        Circle c = new Circle(event.getX(), event.getY(), cirlceThickness.getValue(),
                                Color.TRANSPARENT);
                        c.setStroke(getColor(vbox));
                        c.setCenterX(event.getX());
                        c.setCenterY(event.getY());
                        ((Pane) borderPane.getCenter()).getChildren().add(c);
                        countShapes++;
                    }
                }
            });
//        (borderPane.getCenter()).setOnMousePressed(event -> {
//            if (line.isSelected() && group.getSelectedToggle().equals(line)) {
//                if ( inBounds(event.getX(), event.getY(), lineThick.getValue())) {
//                    double x1 = event.getX();
//                    double y1 = event.getY();
//                    (borderPane.getCenter()).setOnMouseReleased(e -> {
//                        if ( inBounds(e.getX(), e.getY(), lineThick.getValue())) {
//                            double x2 = e.getX();
//                            double y2 = e.getY();
//                            //System.out.printf("%f, %f, %f, %f", x1, y1, x2, y2);
//                            Line line1 = new Line(x1, y1, x2, y2);
//                            line1.setStrokeWidth(lineThick.getValue());
//                            //line1.setFill(getColor(vbox));
//                            ((Pane) borderPane.getCenter()).getChildren().add(line1);
//                            countLines++;
//                        }
//                    });
//                }
//            }
//        });
        canvasImage = (Button) vbox.getChildren().get(14);
        canvasImage.setOnAction(e -> {
                ImageView image = getImageView(vbox);
                ((Pane) borderPane.getCenter()).getChildren().add(image);
                canvasColor.setText("white");

            });

    }

    /**
     * Method to implement functionality of the color bar
     * @param vbox Color bar, which is a vertical box.
     * @return Returns the required color.
     */
    public Color getColor(VBox vbox) {
        ToggleGroup group = selectColor.getToggleGroup();
        if (selectColor.isSelected() && group.getSelectedToggle().equals(selectColor)) {
            Color color1 = Color.BLACK;
            try {
                color1 = Color.valueOf(color.getText());
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid color entered, "
                        + "please enter a valid color.\n Default color set to Black.");
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Invalid Color");
                alert.setResizable(true);
                color.setText("black");
                alert.showAndWait();
            }
            return  color1;
        } else if (multiColor.isSelected() && group.getSelectedToggle().equals(multiColor)) {
            double r = (Math.random());
            double g = (Math.random());
            double b = (Math.random());
            return Color.color(r, g, b);
        } else if (makeColor.isSelected() && group.getSelectedToggle().equals(makeColor)) {
            return Color.color(red.getValue() / 255.0 , green.getValue() / 255.0, blue.getValue() / 255.0);
        }
        return Color.BLACK;
    }

    /**
     * Gets color of next canvas from user.
     * @param vbox The toolbar
     * @return Returns the color which is taken from the user.
     */
    public Color getCanvasColor(VBox vbox) {
        Color color1 = Color.WHITE;
        try {
            color1 = Color.valueOf(canvasColor.getText());
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid Color");
            alert.setResizable(true);
            String content = String.format("Invalid color entered, please enter a valid color."
                    + "\n Default color set to White.");
            alert.setContentText(content);
            canvasColor.setText("white");
            alert.showAndWait();
        }
        return  color1;
    }

    /**
     * Method used to get the image from a link.
     * @param vbox Toolbar
     * @return ImageView which contains the desired picture.
     */
    public ImageView getImageView(VBox vbox) {
        TextField imageUrl = (TextField) vbox.getChildren().get(13);
        ImageView image = new ImageView("https://i.imgur.com/QRERgmW.jpg");
        try {
            image = new ImageView(imageUrl.getText());

        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid Image URL");
            alert.setResizable(true);
            String content = String.format("Invalid Image URL Entered! Please enter a valid url.\n"
                    + "Default image is now place as canvas.");
            imageUrl.setText("https://i.imgur.com/QRERgmW.jpg");
            alert.setContentText(content);
            alert.showAndWait();
        }
        image.setFitHeight(450);
        image.setFitWidth(650);
        image.setPreserveRatio(false);
        return image;

    }

    /**
     * Implements the functionality of the bottom bar.
     * @param hbox The pane that represents the bottom bar.
     * @param borderPane The borderPane that is CSPaint.
     */
    public void bottomFunc(HBox hbox, BorderPane borderPane) {
        bottomText = (Label) hbox.getChildren().get(0);
        Pane canvasPane = (Pane) borderPane.getCenter();
        canvasPane.setOnMouseMoved(e -> {
                double x = e.getX();
                double y = e.getY();
                String coordinates = String.format("(%.2f, %.2f)    Number of Shapes: %d ", x,
                    y, countShapes, countLines);
                bottomText.setText(coordinates);

            //borderPane.setBottom(hbox)
                //hi
                //bye
            });

    }

    /**
     * Method checks if the following x, and y values will be in bounds.
     * @param x X coordinate
     * @param y Y coordinate
     * @param thick Thickness of shape
     * @return A boolean, true if the shape is in bounds.
     */
    public Boolean inBounds(double x, double y, double thick) {
        double maxx = x + thick;
        double maxy = y + thick;
        double minx = x - thick;
        double miny = y - thick;
        return maxx <= 650 && maxy <= 450 && minx >= 0 && miny >= 0;
    }

    /**
     * Main() implements the Application.
     *@param args Args parameter for main.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
