import java.awt.*;
import java.util.ArrayList;

public interface Navigable {
    public ArrayList<Navigable> getNeighbors();
    public void addNeighbor(Navigable neighbor);

    public int getX();
    public int getY();
    public boolean isTraversable();
    public void setTraversable(boolean traversable);
    public ArrayList<Navigable> getExpandedNeighbors();


}
