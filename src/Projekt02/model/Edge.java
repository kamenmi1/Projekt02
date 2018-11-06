package Projekt02.model;

public class Edge {

    private int x1, y1, x2, y2;

    public Edge(Point p1, Point p2) {
        x1 = p1.x;
        y1 = p1.y;
        x2 = p2.x;
        y2 = p2.y;
    }

    //zjitisti jestli je hrana vodorovna
    //true pokud je vodorovna a false pokud ne
    public boolean isHorizontal() {
        // TODO y1 = y2
        return false;
    }

    /**
     * oritetujeme usecky odshora dolu
     */
    public void orientate() {
        // prohozeni, když y1 je vesti nez y2
    }

    /**
     * tady nam to zjisti, zda existuje prusecik s hranou
     *
     * @param y y-ova souradnice scanline
     * @return true, pokud prusecik existuje
     */
    public boolean intersectionExits(int y) {
        return false;
    }

    /**
     * vypocita a vrati x-ovou souradnici pruseciku se scanLine
     *
     * @param y y-ova souradnice scanLine
     * @return souradnice xS
     */

    public int getIntersection(int y) {
        //vypocitat prusecik pomoci y, k, q; -- trivialni algoritmus (OSA Y)
        return 0;
    }

    public boolean isInside(Point p) {
        Point t = new Point(x2 - x1, y2 - y1);
        Point n = new Point(t.y, -t.x);
//        Point n = new Point(-t.y, t.x);
        Point v = new Point(p.x - x1, p.y - y1);
        return (n.x * v.x + n.x * v.y < 0);
    }

    /**
     * Vypocitani pruseciku dvou hran
     *
     * @param v1 prvni bod druhe hrany
     * @param v2 druhy bod druhe hrany
     * @return prusecik
     */

    public Point getIntersection(Point v1, Point v2) {
        float x0 = ((v1.x * v2.y - v1.y * v2.x) * (x1 - x2) - (x1 * y2 - y1 * x2) * (v1.x - v2.x)) / (float) ((v1.x - v2.x) * (y1 - y2) - (x1 - x2) * (v1.y - v2.y));
        float y0 = ((v1.x * v2.y - v1.y * v2.x) * (y1 - y2) - (x1 * y2 - y1 * x2) * (v1.y - v2.y)) / (float) ((v1.x - v2.x) * (y1 - y2) - (x1 - x2) * (v1.y - v2.y));
        return new Point(Math.round(x0),Math.round(y0));
    }

}
