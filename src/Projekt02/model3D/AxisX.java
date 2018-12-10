package Projekt02.model3D;

import transforms.Point3D;

import java.awt.*;

public class AxisX extends Axis {

    public AxisX(Color color) {
        this.color = color;
        vertices.add(new Point3D(0,0,0));
        vertices.add(new Point3D(2, 0, 0));

        indices.add(0);indices.add(1);
    }
}
