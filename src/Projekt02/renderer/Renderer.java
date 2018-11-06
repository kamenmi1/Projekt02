package Projekt02.renderer;

import Projekt02.model.Point;
import Projekt02.view.Raster;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private Raster raster;

    public Renderer(Raster raster) {
        this.raster = raster;
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color) {

        float k = (y2 - y1) / (float) (x2 - x1);
        float q = y1 - k * x1;

        if (Math.abs(k) < 1) {
            if (x1 > x2) {
                int a = x1;
                x1 = x2;
                x2 = a;
            }
            for (int x = x1; x <= x2; x++) {
                int y = Math.round(k * x + q);
                raster.drawPixel(x, y, color);
            }
        } else {
            if (y1 > y2) {
                int a = y1;
                y1 = y2;
                y2 = a;
            }

            for (int y = y1; y <= y2; y++) {
                int x = Math.round((y - q) / k);
                raster.drawPixel(x, y, color);
            }
        }
    }

    public void drawDDA(int x1, int y1, int x2, int y2, int color) {
        int dx, dy;
        float k, h, g, x, y;

        dx = x2 - x1;
        dy = y2 - y1;
        k = (float) dy / dx;

        x = x1;
        y = y1;

        if (Math.abs(dx) > Math.abs(dy)) {
            g = 1;
            h = k;

            if (x1 > x2) {
                int a = x1;
                x1 = x2;
                x2 = a;
                int b = y1;
                y1 = y2;
                y2 = b;
            }
            y = y1;
            x = x1;
            for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
                raster.drawPixel(Math.round(x), Math.round(y), color);
                x = x + g;
                y = y + h;
            }
        } else {
            g = 1 / k;
            h = 1;
            if (y1 > y2) {
                int a = y1;
                y1 = y2;
                y2 = a;
                int b = x1;
                x1 = x2;
                x2 = b;
            }
            y = y1;
            x = x1;

            for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
                raster.drawPixel(Math.round(x), Math.round(y), color);
                x = x + g;
                y = y + h;
            }
        }
    }

    public void drawPolygon01(List<Integer> points) {
        raster.clear();
        if (points.size() >= 6) {
            for (int i = 0; i < points.size() - 2; i += 2) {
                drawLine(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3), 0xFF4488);
            }
            drawLine(points.get(0), points.get(1), points.get(points.size() - 2), points.get(points.size() - 1), 0xFF4488);
        }
    }
    public void polygon(int x1, int y1, int x2, int y2, int vrcholy) {
        double x0 = x2 - x1;
        double y0 = y2 - y1;
        double circleRadius = 2 * Math.PI;
        double step = circleRadius / (double) vrcholy;
        for (double i = 0; i < circleRadius; i += step) {
            double x = x0 * Math.cos(step) + y0 * Math.sin(step);
            double y = y0 * Math.cos(step) - x0 * Math.sin(step);
            drawDDA((int) x0 + x1, (int) y0 + y1, (int) x + x1, (int) y + y1, 0xFF4488);
            x0 = x;
            y0 = y;
        }
    }

    public void drawPolygon(List<Point> polygonPoints, int color) {
        for(int i =0; i<polygonPoints.size()-1;i++ ){
            drawDDA(polygonPoints.get(i).x,polygonPoints.get(i).y, polygonPoints.get(i+1).x,polygonPoints.get(i+1).y,color);
        }
        drawDDA(polygonPoints.get(0).x,
                polygonPoints.get(0).y, polygonPoints.get(polygonPoints.size()-1).x, polygonPoints.get(polygonPoints.size()-1).y,color);
    }

    public List<Point> clip(List<Point> polygonPoints, List<Point> clipPoints){
        // in - seznam vrcholu orezavaneho polygonu (na tabuly ten cerny)
        // clipPoints - seznam vrcholu orezevaneho polygonu (na tabuly ten zeleny)
        // out - seznam vrcholu toho orezaneho

        List<Point> in = polygonPoints;
        Point p1 = null; //vlozit ten posledni clip point

        for (Point p2: clipPoints){
            List<Point> out = new ArrayList<>();
            //vytvorit hranu z bodu p1 a p2

            //Point v1 = in.last;
            for (Point v2 : in) {

            }
            //TODO opsat kod z prednasky C
            p1 = p2;
            in = out;
        }

        return in;
    }
}
