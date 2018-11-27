package Projekt02.controller;

import Projekt02.model3D.Cube;
import Projekt02.model3D.Solid;
import Projekt02.view.Raster;

import java.awt.*;

public class Controller3D {

    private Renderer3D renderer3D;
    private Solid cube;

    public Controller3D(Raster raster) {
        renderer3D = new Renderer3D(raster);
        initObjects();
        initListeners();
        renderer3D.draw(cube);

    }

    private void initListeners() {
    }

    private void initObjects() {
        cube = new Cube(Color.CYAN);
    }
}
