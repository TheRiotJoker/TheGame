import javax.swing.*;
import java.awt.*;

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
                System.out.println(floors[i][j].getLabel().getLocation());
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
        Searcher searcher = new Searcher();
        int start1 = 0;
        int start2 = 0;
        int end1 = 28;
        int end2 = 55;
        searcher.findWay(floors, floors[start1][start2], floors[end1][end2]);
        floors[start1][start2].getLabel().setBackground(Color.green);
        floors[end1][end2].getLabel().setBackground(Color.red);

    }
    public JLabel getFloorLabel(int i, int j) {
        return floors[i][j].getLabel();
    }
    public void updateHitboxes() {
        System.out.println(floors[0][0].getX());
        for(Floor[] fa : floors) {
            for(Floor f : fa) {
                f.updateHitboxes();
            }
        }
    }
}