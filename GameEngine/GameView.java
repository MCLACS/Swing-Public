import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends JPanel
{
    private List<GameObject> m_objects;

    public GameView(List<GameObject> objects)
    {
      setOpaque(true);
      setIgnoreRepaint(true);
      m_objects = objects;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) bufferedImage.getGraphics();

        for (GameObject obj : m_objects)
        {
          buffer.drawImage(obj.getImage(), obj.getX(), obj.getY(), obj.getW(), obj.getH(), this);
        }

        g.drawImage(bufferedImage, 0, 0, this);
    }

    public void tick()
    {
      repaint();
    }
}
