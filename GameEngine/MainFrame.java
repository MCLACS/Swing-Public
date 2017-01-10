import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class MainFrame extends JFrame
{
  private static Random m_rand = new Random();

  public static void main(String args[])
  {
    JFrame frame = new MainFrame();
    frame.setVisible(true);
  }

  public MainFrame()
  {
    super("Game Engine Example");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // ----------------------------------------------------------
    // TODO: generate 10 random game objects
    // they should be at random locations, random speed.
    // and random size, but the w and h should be equal...
    // ---------------------------------------------------------
    List<GameObject> objects = new ArrayList<GameObject>();
    objects.add(new GameObject("ball.png", 40, 40, 30, 30, 5, 3));

    GameView view = new GameView(objects);
    GameController controller = new GameController(100, objects, view);

    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(view, BorderLayout.CENTER);

    controller.startGame();
  }
}
