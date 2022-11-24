
import java.util.Random;

public class MazeCreator {
    public void createMaze(Navigable[][] e) {
        Random rand = new Random();
        int index1 = rand.nextInt(e.length);
        int index2 = rand.nextInt(e[0].length);
        recursiveMazeOpener(e, index1, index2);
    }
    public void recursiveMazeOpener(Navigable[][] e, int index1, int index2) {
        Navigable currentCell = e[index1][index2];
        currentCell.setTraversable(true);
        Random rand = new Random();
        //we have to move by index of 2 in all 4 directions, if a direction was visited, we do nothing
        //if a direction is not visited, we call this function again with the new cell, from which we then AGAIN go in all directions
        int[] side = {0,1,2,3};
        for(int i = 0; i < 10; i++) {
            //we take an array which has numbers in order, and then we shuffle it 10 times, it should suffice to have a random order of sides
            int firstElement = rand.nextInt(side.length);
            int secondElement;
            do {
                secondElement = rand.nextInt(side.length);
            }while(secondElement == firstElement);
            int temp = side[firstElement];
            side[firstElement] = side[secondElement];
            side[secondElement] = temp;
        }
        //0 = up, 1 = right, 2 = down, 3 = left

        for(int i = 0; i < 4; i++) {

            switch(side[i]) {
                case 0:
                    //up
                    try {
                        if(!e[index1-2][index2].isTraversable() && index1-2 != 0) {
                            e[index1-1][index2].setTraversable(true);
                            recursiveMazeOpener(e, index1-2, index2);
                        }
                    } catch(ArrayIndexOutOfBoundsException ignored) {

                    }
                    break;
                case 1:
                    //right
                    try {
                        if(!e[index1][index2+2].isTraversable() && index2+2 != e.length-1) {
                            e[index1][index2+1].setTraversable(true);
                            recursiveMazeOpener(e, index1, index2+2);
                        }
                    } catch(ArrayIndexOutOfBoundsException ignored) {

                    }
                    break;
                case 2:
                    //down
                    try {
                        if(!e[index1+2][index2].isTraversable() && index1+2 != e.length-1) {
                            e[index1+1][index2].setTraversable(true);
                            recursiveMazeOpener(e, index1+2, index2);
                        }
                    } catch(ArrayIndexOutOfBoundsException ignored) {

                    }
                    break;
                case 3:
                    try {
                        if(!e[index1][index2-2].isTraversable() && index2-2 != 0) {
                            e[index1][index2-1].setTraversable(true);
                            recursiveMazeOpener(e, index1, index2-2);
                        }
                    } catch(ArrayIndexOutOfBoundsException ignored) {

                    }
                    break;
            }
        }
    }
}
