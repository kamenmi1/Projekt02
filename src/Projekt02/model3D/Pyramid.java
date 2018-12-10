package Projekt02.model3D;

import transforms.Point3D;

import java.awt.*;

public class Pyramid extends Solid {
    public Pyramid( Color color) {
        this.color = color;
        vertices.add(new Point3D(0,0,1));

        vertices.add(new Point3D (-1,-1,-1));
        vertices.add(new Point3D (1,-1,-1));
        vertices.add(new Point3D (1,1,-1));
        vertices.add(new Point3D (-1,1,-1));

        indices.add(0); indices.add(1);
        indices.add(0); indices.add(2);
        indices.add(0); indices.add(3);
        indices.add(0); indices.add(4);

        indices.add(1); indices.add(2);
        indices.add(2); indices.add(3);
        indices.add(3); indices.add(4);
        indices.add(4); indices.add(1);
    }
}
