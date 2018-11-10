package Projekt02.controller;

import Projekt02.fill.ScanLine;
import Projekt02.fill.SeedFill;
import Projekt02.model.Point;
import Projekt02.renderer.Renderer;
import Projekt02.view.PGRFWindow;
import Projekt02.view.Raster;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private SeedFill seedFill;
    private ScanLine scanLine;
    private Raster raster;
    private Renderer renderer;
    private final List<Point> polygonPoints = new ArrayList<>();
    private final List<Point> clipPoints = new ArrayList<>(); //TODO
    private final List<Point> linePoints = new ArrayList<>();

    public Controller(PGRFWindow window) {
        initObjects(window);
        initListeners();
    }

    private void initObjects(PGRFWindow window) {
        raster = new Raster();
        window.add(raster); // vložit plátno do okna
        raster.setFocusable(true);
        raster.grabFocus();

        renderer = new Renderer(raster);

        seedFill = new SeedFill();
        seedFill.setRaster(raster);

        scanLine = new ScanLine();
        scanLine.setRaster(raster);

    }

    private void initListeners() {

        raster.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                if (e.isControlDown() || e.isShiftDown()) return;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    polygonPoints.add(new Point(e.getX(), e.getY()));
                    if (polygonPoints.size() == 1) {
                        polygonPoints.add(new Point(e.getX(), e.getY())); // naplnit clipPoints -- na prostredni tlacitko mysi
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    linePoints.add(new Point(e.getX(), e.getY()));
                    linePoints.add(new Point(e.getX(), e.getY()));
                }
            }

            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    seedFill.init(e.getX(), e.getY(), 0x00ffff);
                    seedFill.fill();
                }
                else if (e.isShiftDown()){
                    scanLine.init(polygonPoints,0xff0000, 0xee82ee);
                    scanLine.fill();
                }

                else {
                    raster.drawPixel(e.getX(), e.getY(), 0xffffff);
                }
            }
        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    polygonPoints.get(polygonPoints.size() - 1).x = e.getX();
                    polygonPoints.get(polygonPoints.size() - 1).y = e.getY();
                    //zavolame update
                    update();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    linePoints.get(linePoints.size() - 1).x = e.getX();
                    linePoints.get(linePoints.size() - 1).y = e.getY();
                    //renderer.drawDDA(400, 300, e.getX(), e.getY(), 0xffff00);
                }
                update();
            }
        });
        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode());
                // na klávesu C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    raster.clear();
                }
            }
        });
        // chceme, aby canvas měl focus hned při spuštění
     //  raster.requestFocus();
    }

    private void update() {
        raster.clear();
        renderer.drawPolygon(polygonPoints, 0x00FFFF);
        for (int i = 0; i < linePoints.size(); i += 2) {
            renderer.drawDDA(linePoints.get(i).x, linePoints.get(i).y, linePoints.get(i + 1).x, linePoints.get(i + 1).y, 0xFF11FF);
        }

        //List<Point> out = renderer.clip(...);
        //renderer.drawPolygon(out,0xff0000);
    }
}
