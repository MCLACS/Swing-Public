import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BorderLayoutFrame extends JFrame
{
  public static void main(String args[])
  {
    JFrame frame = new BorderLayoutFrame();
    frame.setVisible(true);
  }

  public BorderLayoutFrame()
  {
    super("Borderlayout Example");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // build UI
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(makePanel(Color.red, "North"), BorderLayout.NORTH);
    getContentPane().add(makePanel(Color.yellow, "West"), BorderLayout.WEST);
    getContentPane().add(makePanel(Color.green, "East"), BorderLayout.EAST);
    getContentPane().add(makePanel(Color.gray, "South"), BorderLayout.SOUTH);
    getContentPane().add(makePanel(Color.white, "Center"), BorderLayout.CENTER);
  }

  private JPanel makePanel(Color c, String txt)
  {
    JPanel panel = new JPanel();
    panel.setBackground(c);
    JLabel l = new JLabel(txt);
    panel.add(l);
    return panel;
  }
}
