import java.awt.*;

// Child class for image panel
public class ImagePanel extends GuiComponent {
    public ImagePanel(String imagePath) {
        this.imagePath = imagePath;
        setupComponent();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
