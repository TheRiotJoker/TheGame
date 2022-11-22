public class SearcherNode {
    private Navigable node;
    private double f; //f = g + h
    private Navigable parentNavigable;
    private SearcherNode parentSearcherNode;
    private boolean visited;
    public SearcherNode(Navigable node, double f, Navigable parentNavigable, SearcherNode parentSearcherNode) {
        this.node = node;
        this.f = f;
        this.parentNavigable = parentNavigable;
        this.visited = false;
        this.parentSearcherNode = parentSearcherNode;
    }
    public boolean isVisited() {
        return visited;
    }
    public void visited() {
        visited = true;
    }
    public Navigable getNavigable() {
        return node;
    }

    public void setFloor(Navigable node) {
        this.node = node;
    }

    public double getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Navigable getParentNavigable() {
        return parentNavigable;
    }

    public SearcherNode getParentSearcherNode() {
        return parentSearcherNode;
    }
}
