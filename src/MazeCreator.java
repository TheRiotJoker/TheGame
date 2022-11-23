import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MazeCreator {
    public void createMaze(Navigable[][] e) {
        ArrayList<Navigable> maze = new ArrayList<>();
        ArrayList<Navigable> wallsList = new ArrayList<>();
        ArrayList<Navigable> visited = new ArrayList<>();
        for(Navigable[] arr : e) { //iterates over every array that is in the 2d array
            //and adds all of the elements in each array into the wallsList, as a list
            wallsList.addAll(Arrays.asList(arr));
        }
        Random rand = new Random();
        Navigable currentCell = wallsList.get(rand.nextInt(wallsList.size()));//give me a random cell
        ArrayList<Navigable> frontierCells = new ArrayList<>(currentCell.getExpandedNeighbors());
        do {
            Navigable frontierCell = frontierCells.get(rand.nextInt(frontierCells.size()));
        }while(!frontierCells.isEmpty());

    }
}
