
import java.util.ArrayList;

 interface Navigable {
     ArrayList<Navigable> getNeighbors();
     void addNeighbor(Navigable neighbor);

     int getX();
     int getY();
     boolean isTraversable();
     void setTraversable(boolean traversable);


}
