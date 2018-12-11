package Projekt02.model3D;

import transforms.Cubic;
import transforms.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cubic3D extends Solid {

    private List<Point3D> points = new ArrayList<>();

    public Cubic3D() {
        points.add(new Point3D(0.5, 0.5, 0));
        points.add(new Point3D(0.2, 0.7, 0));
        points.add(new Point3D(0.1, 0.2, 1));
        points.add(new Point3D(0.5, 0, 0));
        color = Color.GREEN;
    }

    public void create() {
        List<Cubic> cubics = new ArrayList<>();
        for (int i = 0; i < points.size() - 3; i = i + 3) {
            cubics.add(new Cubic(Cubic.BEZIER, points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3)));
        }
        int i = 0;
        for (Cubic cubic : cubics) {
            Point3D p1 = cubic.compute(0);
            for (double a = 0.1; a < 1; a += 0.1) {
                    Point3D p2 = cubic.compute(a);
                    vertices.add(p1);
                    vertices.add(p2);
                    p1=p2;
                    indices.add(i);
                    indices.add(++i);
            }
        }
    }
}
