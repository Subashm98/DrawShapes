
import java.util.ArrayList;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.Font;



public class MakeButtons extends Application{
	
	GraphicsContext gc;
	Canvas canvas = new Canvas(800,800);   // make the canvas of size 800X800
	EventHandler shape = null;   // this will store events

	String color = "black";      // make the default color of the shape black
	private double shapeHeight = 100,shapeWidth = 100;         // will store the height or the width for the shape.
	
	TextField heightObj,widthObj;
	
	double area;
	int countRec;
	int totalShape;
	
	ArrayList<double[]> un;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void start(Stage primaryStage){
		
		 un = new ArrayList<>();
		
		primaryStage.setTitle("Draw Shapes");
		
		// create a canvas objects to be placed on the stage
		BorderPane pane = new BorderPane();
		//Canvas canvas = new Canvas(800,800);
		VBox buttonsLayout = new VBox(5);
		
		// create Button objects for the different shapes 
		
		Button rec = new Button("Rectangle");
		Button tri = new Button("Triangle");
		Button par = new Button("Parallelogram");
		Button hour = new Button("HourGlass");
		
		// create button objects for the color for the shape
		Button red = new Button("Red");
		Button blue = new Button("Blue");
		Button green = new Button("Green");
		Button yellow = new Button("Yellow");
		Button brown = new Button("Brown");
		
		// create a button object to clear the screen/canvas 
		Button clear = new Button("Clear");
		
		// maker the buttons the same width
		rec.setMinWidth(150);
		tri.setMinWidth(150);
		par.setMinWidth(150);
		hour.setMinWidth(150);
		clear.setMinWidth(150);
		red.setMinWidth(150);
		blue.setMinWidth(150);
		green.setMinWidth(150);
		yellow.setMinWidth(150);
		brown.setMinWidth(150);
		
		
		
		// create TextField objects to take in user's input
		Label height = new Label("Height:");
		Label width = new Label("Width");
		Label numRec = new Label("Number of Rectangle(s) " + countRec);
		
	//	heightIn.setMaxWidth(150);  // set the max width for the text field for the height 
	//	widthIn.setMaxWidth(150);   // set the max width for the text field for the width 

		widthObj = new TextField();
		heightObj = new TextField();
		
		// this will read in the input from the user
		EventHandler<ActionEvent> heightTextField = new HeightTextField();
		heightObj.setOnAction(heightTextField);
		EventHandler<ActionEvent> widthTextField = new WidthTextField();
		widthObj.setOnAction(widthTextField);

		
		rec.setOnAction(new RectangleEvent());
		tri.setOnAction(new TriangleEvent());
		par.setOnAction(new ParEvent());
		hour.setOnAction(new HourEvent());
		
		clear.setOnAction(new Clear());
		Font font = new Font("Arial",14);

		red.setOnAction(new RedColor());
		blue.setOnAction(new BlueColor());
		green.setOnAction(new GreenColor());
		yellow.setOnAction(new YellowColor());
		brown.setOnAction(new BrownColor());
		
		red.setStyle("-fx-background-color: #FF0000;");
		blue.setStyle("-fx-background-color: blue;");
		green.setStyle("-fx-background-color: green;");
		yellow.setStyle("-fx-background-color: yellow;");
		brown.setStyle("-fx-background-color: brown;");
		
		red.setTextFill(Color.GREEN);
		blue.setTextFill(Color.ORANGE);
		yellow.setTextFill(Color.PURPLE);
		brown.setTextFill(Color.GREEN);
		green.setTextFill(Color.RED);
		
		
		
	
		buttonsLayout.getChildren().addAll(rec,tri,par,hour,red,blue,green,yellow,brown,height,heightObj,width,widthObj,numRec,clear);
		Scene scene = new Scene(pane);
		
		buttonsLayout.setStyle("-fx-border-color: blue;"+"-fx-border-width: 2;");
	//	buttonsLayout.opacityProperty();
	
		pane.setLeft(buttonsLayout);
		pane.setRight(canvas);
		
		
		gc = canvas.getGraphicsContext2D();
		primaryStage.setScene(scene);
		primaryStage.show();
			
	}
	
	// this draws the shape Rectangle where ever the mouse was clicked on the canvas.
	private class Rectangle implements EventHandler<MouseEvent> {
		@Override
        public void handle(MouseEvent event) {
	
        	double x = event.getX();             // get the x position of where the mouse was clicked on the canvas.
        	double y = event.getY();             // get the y position of where the mouse was clicked on the canvas.
        	
        	double height = shapeHeight;
        	double width = shapeWidth;
        	
        	gc.setFill(Paint.valueOf(color));   // set the color for the shape.
        	gc.fillRect(x-width/2, y-height/2, width, height);  // makes the shape using the coordinates.
        	area = height*width;
        	countRec++;
        	totalShape++;
        }
    }
	
	// this draws the shape triangle where ever the mouse was clicked on the canvas.
	private class Triangle implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
        	
        	double x = event.getX();              // get the x position of where the mouse was clicked on the canvas.
        	double y = event.getY();              // get the y position of where the mouse was clicked on the canvas.
        	
        	double height = shapeHeight;
        	double base = height * 2 - 1; 
        	
        	gc.setFill(Paint.valueOf(color));      // set the color for the shape.
        	
        	// makes the mouse click coordinate the center of the shape,then adjusts the coordinates to match the given width and height.
        	double[] xPos = { x, x-(base/2), x + base/2};           
        	double[] yPos = {y-(height/2),y+height/2,y+height/2};
        	
        	gc.fillPolygon(xPos,yPos,3);  // makes the shape using the coordinates.
        	     
        }
    } // Triangle
	
	
	private class HourGlass implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
        	
        	double x = event.getX();              // get the x position of where the mouse was clicked on the canvas.
        	double y = event.getY();              // get the y position of where the mouse was clicked on the canvas.
        	
        	double height = shapeHeight;
        	double base = height * 2 - 1; 
       
        	
        	gc.setFill(Paint.valueOf(color));      // set the color for the shape.
        	
        	// makes the mouse click coordinate the center of the shape,then adjusts the coordinates to match the given width and height.
        	double[] xPos = { x-(height/2), x+(base/2)-(height/2), x-(height/2),x + base/2-(height/2)};           
        	double[] yPos = {y-(height/2),y-(height/2),y+height-(height/2),y+height-(height/2)};
        	
        	gc.fillPolygon(xPos,yPos,4);  // makes the shape using the coordinates.
        	
          
        }
    } // Triangle
	
	private class Parallelogram implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
        	
        	double x = event.getX();              // get the x position of where the mouse was clicked on the canvas.
        	double y = event.getY();              // get the y position of where the mouse was clicked on the canvas.
        	
        	double height = shapeHeight;
        	double width = shapeWidth;
        	
        	gc.setFill(Paint.valueOf(color));      // set the color for the shape.
        	
        	// makes the mouse click coordinate the center of the shape,then adjusts the coordinates to match the given width and height.
        	double[] xPos = {x+height-(height) , x-(height) + width+height, x + width-(height), x-(height)};
    		double[] yPos = {y-(height/2),y-(height/2),y+height-(height/2),y+height-(height/2)};
    		gc.fillPolygon( xPos, yPos, 4);  // makes the shape using the coordinates.
        	
          
        }
	}
	
	// if the rectangle button is clicked, then it calls the class Rectangle which then draws the shape
    private class RectangleEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	if(shape != null)
        		canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, shape);  // removes the previous events so only one shape will be drawn.
        	shape = new Rectangle();                                         // create a new Rectangle Objects.
        	canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, shape);         // draw the shape on the canvas on the coordinate of the mouse click.
        	countRec++;
        	//initBoard();
        }
    }
    
    // if the triangle button is clicked, then it calls the class Triangle which then draws the shape
    private class TriangleEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	
        	if(shape!=null)
        		canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, shape); // removes the previous events so only one shape will be drawn.
        	shape = new Triangle();                                         // create a new Triangle Objects.
        	canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, shape);        // draw the shape on the canvas on the coordinate of the mouse click.
        	
        }
    }
    
    private class ParEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	if(shape!=null)
        		canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, shape); // removes the previous events so only one shape will be drawn.
        	shape = new Parallelogram();                                    // create a new Parallelogram Objects.
        	canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, shape);        // draw the shape on the canvas on the coordinate of the mouse click.
        	
        }
    }
    
    private class HourEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	
        	if(shape!=null)
        		canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, shape); // removes the previous events so only one shape will be drawn.
        	shape = new HourGlass();                                        // create a new HourGlass Objects.
        	canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, shape);        // draw the shape on the canvas on the coordinate of the mouse click.
        	
        }
    } 
    
    // if the color red button is clicked, then it saves the color choice, so that the next shape will be red.
    private class RedColor implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            color = "red";
        }
    }
    
    // if the color blue button is clicked, then it saves the color choice, so that the next shape will be blue.
    private class BlueColor implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            color = "blue";
        }
    }
    
    // if the color green button is clicked, then it saves the color choice, so that the next shape will be green.
    private class GreenColor implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            color = "green";
        }
    }

    // if the color yellow button is clicked, then it saves the color choice, so that the next shape will be yellow.
    private class YellowColor implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            color = "yellow";
        }
    }
    
    // if the color brown button is clicked, then it saves the color choice, so that the next shape will be brown.
    private class BrownColor implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            color = "brown";
        }
    }
    
    
    private class HeightTextField implements EventHandler<ActionEvent>{
    	public void handle(ActionEvent arg0) {
    		shapeHeight = Integer.parseInt(heightObj.getText());
    	}
    }
    
    private class WidthTextField implements EventHandler<ActionEvent>{
    	public void handle(ActionEvent arg0) {
    		shapeWidth = Integer.parseInt(widthObj.getText());
    		
    	}
    	
    }
    
       
    
    // this clears the screen/canvas removing all the shapes on the canvas.
    private class Clear implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	totalShape = 0;
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 800, 800);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
