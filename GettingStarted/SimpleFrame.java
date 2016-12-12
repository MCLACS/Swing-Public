import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleFrame extends JFrame
{
  public static void main(String args[])
  {
    JFrame frame = new SimpleFrame();
    frame.setVisible(true);
  }

  public SimpleFrame()
  {
    super("My Simple Frame");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton button = new JButton("Hello");
    getContentPane().add(button);
    button.addActionListener(new OnClick());
  }

  private class OnClick implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent evt)
    {
      JOptionPane.showMessageDialog(SimpleFrame.this, "Message", "Title", JOptionPane.INFORMATION_MESSAGE);
    }
  }
}
