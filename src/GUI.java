import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GUI extends JFrame {
    private JPanel contentPanel;
    private Main main;
    public GUI() {
        init();
        main = new Main(this);
        contentInit();
    }
    private void init() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setSize(800,800);
        setDefaultLookAndFeelDecorated(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setResizable(false);
        double screenWidth = tk.getScreenSize().getWidth();
        double screenHeight = tk.getScreenSize().getHeight();
        double height = this.getHeight();
        double width = this.getWidth();
        double x =(screenWidth/2) - width/2;
        double y = (screenHeight/2) - height/2;
        setLocation((int)x, (int)y);
        setVisible(true);
    }
    private void contentInit() {
        JPanel underlyingPanel = (JPanel) getContentPane();
        SpringLayout windowLayout = new SpringLayout();
        underlyingPanel.setLayout(windowLayout);
        contentPanel = new JPanel(true);
        setContentPanelSize();
        contentPanel.setBackground(Color.yellow);
        windowLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, contentPanel, 0, SpringLayout.HORIZONTAL_CENTER, underlyingPanel);
        windowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, contentPanel, 0, SpringLayout.VERTICAL_CENTER, underlyingPanel);
        underlyingPanel.add(contentPanel);
        contentPanel.setLayout(new GridLayout(Main.COLS_ROWS,Main.COLS_ROWS));
        for(int i = 0; i < Main.COLS_ROWS; i++) {
            for(int j = 0; j < Main.COLS_ROWS; j++) {
                contentPanel.add(main.getFloorLabel(i,j));
            }
        }
        setVisible(true);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setContentPanelSize();
                revalidateEverything();
                setVisible(true);
            }
        });
        main.start();
    }
    private void setContentPanelSize() {
        int cWidth = (this.getWidth()*4)/5;
        int cHeight = (this.getHeight()*4)/5;
        if(cWidth < cHeight) {
            cHeight = cWidth;
        } else {
            if(cWidth > cHeight) {
                cWidth = cHeight;
            }
        }
        contentPanel.setPreferredSize(new Dimension(cWidth,cHeight));
        this.repaint();
        setVisible(true);
    }
    public void revalidateEverything() {
        contentPanel.revalidate();
        this.revalidate();
        main.updateHitboxes();
    }
    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}
