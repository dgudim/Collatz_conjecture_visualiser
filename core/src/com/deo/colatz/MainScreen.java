package com.deo.colatz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.deo.colatz.Main.HEIGHT;
import static com.deo.colatz.Main.WIDTH;
import static com.deo.colatz.Utils.updateCamera;
import static java.lang.StrictMath.max;
import static java.lang.StrictMath.sqrt;

public class MainScreen implements Screen {
    
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;
    private final Array<Array<Double>> graphs;
    
    private double scaleX;
    private double scaleY;
    private int maxIterations = 150;
    
    public MainScreen() {
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        viewport = new ScreenViewport(camera);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        graphs = new Array<>();
        
        for (int startingPoint = 3; startingPoint < 1000; startingPoint++) {
            graphs.add(processConjecture(startingPoint, maxIterations));
        }
        
        scaleX = WIDTH / (double) maxIterations;
        double maxValue = 0;
        
        for (int graph = 0; graph < graphs.size; graph++) {
            for (int point = 0; point < graphs.get(graph).size; point++) {
                maxValue = max(maxValue, graphs.get(graph).get(point));
            }
        }
        
        scaleY = HEIGHT / maxValue;
    }
    
    Array<Double> processConjecture(double startingPoint, double maxIterations) {
        Array<Double> points = new Array<>();
        double currentPoint = startingPoint;
        double iteration = 0;
        while (currentPoint != 1 && iteration < maxIterations) {
            if (currentPoint % 2 == 0) {
                currentPoint /= 2;
            } else {
                currentPoint *= 3;
                currentPoint++;
            }
            iteration++;
            points.add(sqrt(currentPoint / iteration));
        }
        return points;
    }
    
    @Override
    public void show() {
    
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        for (int graph = 0; graph < graphs.size; graph++) {
            shapeRenderer.setColor(new Color().fromHsv(graph * 280 / (float)graphs.size, 0.55f, 1));
            for (int point = 0; point < graphs.get(graph).size - 1; point++) {
                shapeRenderer.line(
                        (float) (point * scaleX), (float) (graphs.get(graph).get(point) * scaleY),
                        (float) (point * scaleX + scaleX), (float) (graphs.get(graph).get(point + 1) * scaleY));
            }
        }
        shapeRenderer.end();
    }
    
    @Override
    public void resize(int width, int height) {
        updateCamera(camera, viewport, width, height);
    }
    
    @Override
    public void pause() {
    
    }
    
    @Override
    public void resume() {
    
    }
    
    @Override
    public void hide() {
    
    }
    
    @Override
    public void dispose() {
    
    }
}
