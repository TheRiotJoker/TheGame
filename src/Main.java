import javax.swing.*;
import java.awt.*;

public class Main {
    public static final int COLS_ROWS = 40;
    private final GUI gui;
    private final Floor[][] floors;
    public Main(GUI gui) {
        this.gui = gui;
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
                }
            }
        }
    }
    public void start() {
        gui.revalidateEverything();
        MazeCreator mazeCreator = new MazeCreator();
        mazeCreator.createMaze(floors);
        //find the dead ends:
        //a dead end has 3 walls surrounding it:
        gui.revalidateEverything();
        Searcher searcher = new Searcher();
        int start1 = 1;
        int start2 = 1;
        int end1 = 2;
        int end2 = 2;
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
    /*
    private void findDeadEnds() { //will be used later
        for(Floor[] f : floors) {
            for(Floor floor : f) {
                if(floor.isTraversable()) {
                    int counter = 0;
                    for(Navigable n : floor.getNeighbors()) {
                        if(!n.isTraversable()) {
                            counter++;
                        }
                    }
                    if(counter == 3) {
                        floor.getLabel().setBackground(Color.pink);
                    }
                }
            }
        }
    }*/
}