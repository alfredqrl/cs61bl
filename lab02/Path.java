/** A class that represents a path via pursuit curves. */
public class Path {
    private Point next;
    private Point curr;
    public Path(double x, double y) {
        next = new Point(x,y);
        curr = new Point(1,0);
    }
    
    public double getCurrX(){
        return curr.getX();
    }
    
    public double getCurrY(){
        return curr.getY();
    }
    public double getNextX(){
        return next.getX();
    }
    public double getNextY(){
        return next.getY();
    }
    public Point getCurrentPoint(){
        return curr;
    }
    public void setCurrentPoint(Point point){
        curr.setX(point.getX());
        curr.setY(point.getY());
    }
    public void iterate(double dx, double dy){
        curr.setX(next.getX());
        curr.setY(next.getY());
        next.setX(curr.getX() + dx);
        next.setY(curr.getY() + dy);
    }

}
