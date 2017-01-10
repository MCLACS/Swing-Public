import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Rectangle;
import java.util.List;
import java.util.HashSet;
import java.util.Set;



public class GameController implements ActionListener
{
  private Timer m_timer;
  private List<GameObject> m_objects;
  private GameView m_view;

  public GameController(int interval, List<GameObject> objects, GameView view)
  {
    m_objects = objects;
    m_view = view;
    m_timer = new Timer(interval, this);
  }

  public void startGame()
  {
    m_timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    for (GameObject obj : m_objects)
    {
      obj.tick(m_view.getWidth(), m_view.getHeight());
    }

    // ----------------------------------------------------------
    // TODO: detect any collisions and destoy the objects
    // that collide.
    // ---------------------------------------------------------

    m_view.tick();
  }
}
