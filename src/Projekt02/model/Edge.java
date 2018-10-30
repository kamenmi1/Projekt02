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
        return  0;
    }

}
