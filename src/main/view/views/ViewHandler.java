package view.views;

import javafx.scene.canvas.GraphicsContext;
import view.views.interfaces.Renderable;

import java.util.ArrayList;

public class ViewHandler {
    private ArrayList<Renderable> drawObjs;

    public ViewHandler() {
        drawObjs = new ArrayList<>();
    }

    public void addRenderable(Renderable r) {
        drawObjs.add(r);
    }

    public boolean removeRenderable(Renderable r) {
        return drawObjs.remove(r);
    }

    public void tick() {
        for (Renderable r : drawObjs) r.tick();
    }


    public void render(GraphicsContext gc) {
        for (Renderable r : drawObjs) r.render(gc);
    }
}
