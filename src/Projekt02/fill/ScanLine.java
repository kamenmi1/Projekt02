package Projekt02.fill;

import Projekt02.model.Edge;
import Projekt02.model.Point;
import Projekt02.view.Raster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLine implements Filler{
    private List<Point> points;
    private int fillColor, edgeColor;
    private Raster raster;

    @Override
    public void setRaster(Raster raster) {
        this.raster = raster;

    }

    @Override
    public void fill() {
        scanLine();

    }

    public void init(List<Point> points, int fillColor, int edgeColor){
        this.points = points;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
    }

    private void scanLine() {
        List<Edge> edges = new ArrayList<>();
        // projet vsechny vrcholy a vytvorit z nich hrany
        // 0 a 1 bod budou
        // posleni a 0 budou hrana
        // pred pridelenim do seznamy hrany orientujeme
        // nepridavame vodorovne hrany
        // najit max Y a min Y

        int minY = points.get(0).y;
        int maxY = minY;
        //projet vsechny mody a najit minimalni a maximalni Y

        // pro vsechna y od nalazeneho minima od nalezenho maxima
        for (int y = minY; y <= maxY; y++){

            List<Integer> intersections = new ArrayList<>();
            //projit vsechny hrany
            // pokud ma usecka prusecik na tomot Y
            // tak vypocitat a ulozit hodnotu pruseciku do seznamu

            Collections.sort(intersections);
            //nebo volitene implementovat vlastni algoritmus
            //provedeme vzbarveni mezi pruseciky
            //tzn. vykreslit caru mezi kazdym sudym a lichym prusecikem
        }
        // obtahnuti hranice
        //TODO:renderer.drawPolygon(points, edgeColor);
    }
}
