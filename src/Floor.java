import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Floor implements Navigable{
    private int x;
    private int y;
    private int width;
    private final ArrayList<Navigable> neighbors;
    private final Rectangle hitbox;
    private JLabel label;
    public Floor() {
        setLabel(new JLabel());
        hitbox = getLabel().getBounds();
        neighbors = new ArrayList<>();
    }
    public void updateHitboxes() {
        hitbox.setBounds(label.getBounds());
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
        label.setOpaque(true);
    }


    @Override
    public ArrayList<Navigable> getNeighbors() {
        return neighbors;
    }

    @Override
    public void addNeighbor(Navigable neighbor) {
        neighbors.add(neighbor);
    }

    @Override
    public int getX() {
        return hitbox.x;
    }

    @Override
    public int getY() {
        return hitbox.y;
    }

}
