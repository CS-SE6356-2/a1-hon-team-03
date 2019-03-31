package main;

import gui.components.card.model.CardNumber;
import gui.components.card.model.Suit;
import gui.components.card.view.ViewCard;
import gui.components.imageloaders.CardImageLoader;
import gui.views.ViewHandler;
import gui.views.samples.DynamicObject;
import gui.views.samples.TestOval;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;


public class Main extends Application implements Runnable {
    public final static int WIDTH = 900;
    public final static int HEIGHT = 600;

    Stage window;
    ViewHandler vh;

    Canvas canvas;

    private Thread thread;
    private boolean running = false;

    public void init() {
        vh = new ViewHandler();

        for (int i = 0; i < 3; ++i) {
            vh.addRenderable(new ViewCard(Suit.Clubs, 4 * i + 2, WIDTH / 3 * i, 0));
        }
    }

    @Override
    public void run() {
        final double ticksPerSecond = 60.0;
        long lastRun = System.nanoTime();
        double gapTimeNanos = 1000000000.0 / 60.0;
        while (running) {
            if (System.nanoTime() >= lastRun + gapTimeNanos) {
                render();
                tick();
                lastRun += gapTimeNanos;
            }
        }
    }

    private void tick() {
        vh.tick();
    }


    private void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (gc == null) {
            System.err.println("MAIN: Cannot render to Canvas when GraphicsContext is null.");
            return;
        }

        // painting the whole screen with white
        gc.setFill(Color.WHITE);
        gc.fillRect(10, 10, WIDTH, HEIGHT);

        vh.render(gc);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("Generic Card Game Engine");

        StackPane root = new StackPane();
        canvas = new Canvas(WIDTH, HEIGHT);

        init();

        root.getChildren().addAll(canvas);

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();


        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
