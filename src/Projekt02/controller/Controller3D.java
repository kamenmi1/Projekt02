package Projekt02.controller;

import Projekt02.view.Raster;

public class Controller3D {

    private Renderer3D renderer3D;

    public Controller3D(Raster raster) {
        renderer3D = new Renderer3D(raster);

    }
}
