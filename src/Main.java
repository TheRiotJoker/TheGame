import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Random;

public class Main {
    public static final int COLS_ROWS = 60;
    private GUI gui;
    private Floor[][] floors;
    public Main(GUI gui) {
        this.gui = gui;
        int p = 0;
        floors = new Floor[COLS_ROWS][COLS_ROWS];
        for(int i = 0; i < floors.length; i++) {
            for(int j = 0; j < floors[i].length; j++) {
                floors[i][j] = new Floor();
            }
        }
        //set neighbors of every floor variable
        for(int i = 0; i < floors.length; i++) {
            for(int j = 0; j < floors[i].length; j++) {
                for(int n = -1; n < 2; n = n+2) {
                    try {
                        floors[i][j].addNeighbor(floors[i+n][j]);
                    } catch(ArrayIndexOutOfBoundsException ignored) {

                    }
                    try {
                        floors[i][j].addNeighbor(floors[i][j+n]);
                    }catch(ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        floors[i][j].addExpandedNeighbor(floors[i][j+n*2]);
                    } catch(ArrayIndexOutOfBoundsException ignored) {

                    }
                    try {
                        floors[i][j].addExpandedNeighbor(floors[i+n*2][j]);
                    } catch(ArrayIndexOutOfBoundsException ignored) {

                    }
                }
            }
        }
    }
    public void start() {
        gui.revalidateEverything();
        MazeCreator mazeCreator = new MazeCreator();
        mazeCreator.createMaze(floors);
        gui.revalidateEverything();
        Searcher searcher = new Searcher();
        int start1 = 0;
        int start2 = 0;
        int end1 = 55;
        int end2 = 55;
        floors[start1][start2].setTraversable(true);
        floors[end1][end2].setTraversable(true);
        searcher.findWay(floors, floors[start1][start2], floors[end1][end2]);
        floors[start1][start2].getLabel().setBackground(Color.green);
        floors[end1][end2].getLabel().setBackground(Color.red);
        System.out.println("job's done");

    }
    public JLabel getFloorLabel(int i, int j) {
        return floors[i][j].getLabel();
    }
    public void updateHitboxes() {
        for(Floor[] fa : floors) {
            for(Floor f : fa) {
                f.updateHitboxes();
            }
        }
    }
}