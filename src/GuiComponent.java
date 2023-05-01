import javax.swing.*;
import java.awt.*;

// Parent class for GUI components
public abstract class GuiComponent extends JComponent implements GameComponent {
    protected String imagePath;
    protected Image image;

    @Override
    public void setupComponent() {
        loadImage();
    }

    protected void loadImage() {
        ImageIcon ii = new ImageIcon(imagePath);
        image = ii.getImage();
    }
}
