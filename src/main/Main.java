package main;

import gui.components.card.model.Suit;
import gui.components.card.view.ViewCard;
import gui.views.ViewHandler;
import gui.views.samples.DynamicObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application implements Runnable {
    public final static int WIDTH = 900;
    public final static int HEIGHT = 600;

    Stage window;
    ViewHandler vh;

    Thread thread;

    Canvas canvas;

    private boolean running = false;

    public void init() {
        vh = new ViewHandler();

        for (int i = 0; i < 3; ++i) {
            vh.addRenderable(new ViewCard(Suit.Clubs, 4 * i + 2, WIDTH / 3 * i, 0));
        }
        vh.addRenderable(new DynamicObject(0, 0));
    }

    public void run() {
        final Runnable updater = () -> {
            render();
            tick();
        };

        long lastUpdate = System.nanoTime();
        final double tickRate = 60.0;
        final double tickGap = Math.pow(10, 9) / tickRate;
        while (running) {
            if (System.nanoTime() >= lastUpdate + tickGap) {
                // UI update is run on the Application thread
                Platform.runLater(updater);
                lastUpdate += tickGap;
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
        gc.fillRect(0, 0, WIDTH, HEIGHT);

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

        thread = new Thread(this);
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
        try {
            if (thread != null)
                thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
