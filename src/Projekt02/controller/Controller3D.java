package Projekt02.controller;

import Projekt02.model3D.*;
import Projekt02.view.Raster;
import transforms.Camera;
import transforms.Mat4;
import transforms.Mat4RotXYZ;
import transforms.Vec3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller3D {

    private Renderer3D renderer3D;
    private Solid cube;
    private Camera camera;
    private int mx, my;
    private Solid pyramid;
    private Solid axisX, axisY, axisZ;

    public Controller3D(Raster raster) {
        renderer3D = new Renderer3D(raster);
        initObjects();
        initListeners(raster);
    }

    private void initListeners(Raster raster) {
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mx = e.getX();
                my = e.getY();
            }
        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    double diff = ((mx - e.getX()) / 500.0);
                    double azimut = camera.getAzimuth() + diff;
                    camera = camera.withAzimuth(azimut);

                    diff = ((my - e.getY()) / 500.0);
                    double zenit = camera.getZenith() + diff;
                    if (zenit > Math.PI /2)
                    {
                        zenit = Math.PI/2;
                    }
                    if (zenit < -(Math.PI /2)){
                        zenit = -Math.PI/2;
                    }
                    camera = camera.withZenith(zenit);

                    renderer3D.setView(camera.getViewMatrix());

                    // dodelat zenit, orezat <-PI/2,PI/2>
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    double rotX = ((mx - e.getX()) / -200.0);
                    double rotY = ((my - e.getY()) / -200.0) ;

                    Mat4 rot = renderer3D.getModel().mul(new Mat4RotXYZ(rotY, 0, rotX));
                    renderer3D.setModel(rot);
                }
                else if (SwingUtilities.isMiddleMouseButton(e)){ //posunuti Mat4Transl

                }
                mx = e.getX();
                my = e.getY();

            }
        });
        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        camera = camera.forward(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        camera = camera.backward(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        camera = camera.left(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        camera = camera.right(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                }
            }
        });
    }

    private void resetCamera() {
        camera = new Camera(new Vec3D(0, -5, 3),
                Math.toRadians(90), Math.toRadians(-40), 1, true);
        renderer3D.setView(camera.getViewMatrix());
    }

    private void initObjects() {
        cube = new Cube(Color.CYAN);
        pyramid = new Pyramid(Color.RED);
        axisX = new AxisX(Color.RED);
        axisY = new AxisY(Color.GREEN);
        axisZ = new AxisZ(Color.BLUE);
        renderer3D.add(cube, pyramid, axisX, axisY, axisZ);
        resetCamera();
    }
}
