import java.awt.*;
import java.util.ArrayList;

public class Searcher {
    public ArrayList<Navigable> findWay(Navigable[][] e, Navigable start, Navigable end) {
        ArrayList<Navigable> set = new ArrayList<>(); //set which will be returned
        ArrayList<SearcherNode> searcherSet = new ArrayList<>(); //set in which all the discovered nodes are

        SearcherNode currentNode; //the node that is currently evaluated and saved
        double h = calculateDistance(start,end); //h = distance(current, EndNode)
        double g = calculateDistance(start,start); //g = distance(current, startNode)
        double f = g+h; //f = g + h
        ArrayList<SearcherNode> neighbors = new ArrayList<>();
        currentNode = new SearcherNode(start,f,start, null); //calculate the important values for the current node
        //searchernode object basically exists only for the calculations of the nodes
        //meaning that it holds all the values and the important navigable objects which will later be used to track the way
        Navigable arrayNode = start;
        searcherSet.add(currentNode);
        do {
            currentNode.visited();
            arrayNode = currentNode.getNavigable();
            //get the fgh values for the neighbors of the currently evaluated node
            for(Navigable navi : arrayNode.getNeighbors()) {
                h = calculateDistance(navi, end);
                g = calculateDistance(start, navi);
                f = g+h;
                //System.out.println(f);
                //if the neighbor is already in the open list, don't add them:
                boolean nodeAlreadyInList = false;
                for(SearcherNode s : searcherSet) {
                    if(s.getNavigable() == navi) {
                        nodeAlreadyInList = true;
                        break;
                    }
                }
                if(!nodeAlreadyInList && navi.isTraversable()) {
                    searcherSet.add(new SearcherNode(navi, f, arrayNode, currentNode));
                }
            }
            //the next evaluated node will be the node with the smallest f value (smallest heuristic to the end)
            SearcherNode node = findOptimalNode(searcherSet);
            if(node == null) { //if there is no other node to be evaluated, there is probably no way
                return set;
            }
            currentNode = node;
        }while(arrayNode != end);
        //draw the way
        //go backwards from the endNode to the start node
        for(SearcherNode n : searcherSet) {
            if(n.getNavigable() == end) {
                currentNode = n;
                break;
            }
        }
        for(SearcherNode se : searcherSet) {
            ((Floor)se.getNavigable()).getLabel().setBackground(Color.gray);
        }
        do {
            //find searcherNode which has the END node
            arrayNode = currentNode.getNavigable();
           // ((Floor)arrayNode).getLabel().setBackground(Color.black);
            set.add(arrayNode);
            ((Floor)arrayNode).getLabel().setBackground(Color.blue);
            currentNode = currentNode.getParentSearcherNode();
        }while(arrayNode != start);
        return set;
    }

    private SearcherNode findOptimalNode(ArrayList<SearcherNode> nodeList) {
        SearcherNode retNode = null;
        for(SearcherNode n : nodeList) {
            if(!n.isVisited()) {
                retNode = n;
                break;
            }
        }
        if(retNode == null) {
            return null;
        }
        for(SearcherNode n : nodeList) {
            if((retNode.getF() > n.getF()) && !n.isVisited()) {
                retNode = n;
            }
        }
        return retNode;
    }

    private double calculateDistance(Navigable currentNode, Navigable endNode) {
        double deltaX = currentNode.getX()-endNode.getX();
        deltaX = Math.abs(deltaX);
        deltaX = deltaX*deltaX;
        double deltaY = currentNode.getY()-endNode.getY();
        deltaY = Math.abs(deltaY);
        deltaY = deltaY*deltaY;
        return Math.sqrt(deltaX+deltaY);
    }

}
