package view.views.samples;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import view.views.interfaces.Renderable;

public class DynamicObject implements Renderable {
    int x = 0, y = 0;
    final int xmov = 5, ymov = 5;
    int xvel = 5, yvel = 5;

    public DynamicObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x - 2, y - 2, 10, 10);

    }

    public void tick() {
        if (x == 900) xvel = -xmov;
        else if (x == 0) xvel = xmov;

        if (y == 600) yvel = -yvel;
        else if (y == 0) yvel = ymov;

        x += xvel;
        y += yvel;
    }
}
