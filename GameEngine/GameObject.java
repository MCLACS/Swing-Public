import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.awt.Rectangle;


public class GameObject
{
  private BufferedImage m_image;
  private int m_x;
  private int m_y;
  private int m_w;
  private int m_h;
  private int m_dX; // x velocity in pixels per second
  private int m_dY; // y velocity in pixels per second

  public GameObject(String image, int x, int y, int w, int h, int dX, int dY)
  {
    m_x = x;
    m_y = y;
    m_w = w;
    m_h = h;
    m_dX = dX;
    m_dY = dY;
    try
    {
      m_image = ImageIO.read(new File(image));
    }
    catch (IOException ex)
    {
      throw new Error(ex);
    }
  }

  public void tick(int maxw, int maxh)
  {
    // ----------------------------------------------------------
    // TODO: handle the movement of an object during each
    // tick, of an object leaves the screen, make sure they
    // wrap around...
    // ---------------------------------------------------------
  }

  public BufferedImage getImage()
  { return m_image; }

  public Rectangle getRectangle()
  { return new Rectangle(m_x, m_y, m_w ,m_h); }

  public int getX()
  { return m_x; }

  public int getY()
  { return m_y; }

  public int getW()
  { return m_w; }
  public int getH()
  { return m_h; }
}
