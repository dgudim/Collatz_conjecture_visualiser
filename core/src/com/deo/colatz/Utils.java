package com.deo.colatz;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.deo.colatz.Main.HEIGHT;
import static com.deo.colatz.Main.WIDTH;
import static java.lang.Math.min;

public class Utils {
    
    public static void updateCamera(OrthographicCamera camera, Viewport viewport, int width, int height) {
        viewport.update(width, height);
        camera.position.set(WIDTH / 2f, HEIGHT / 2f, 0);
        float tempScaleH = height / (float) HEIGHT;
        float tempScaleW = width / (float) WIDTH;
        float zoom = min(tempScaleH, tempScaleW);
        camera.zoom = 1 / zoom;
        camera.update();
    }
    
}
