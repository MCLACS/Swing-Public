import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoxLayoutFrame extends JFrame
{
  private JTextField m_name;
  private JTextField m_age;

  public static void main(String args[])
  {
    JFrame frame = new BoxLayoutFrame();
    frame.setVisible(true);
  }

  public BoxLayoutFrame()
  {
    super("Borderlayout Example");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // build UI
    getContentPane().setLayout(new BorderLayout());

    JPanel row1 =  Utilities.makeRow();
    row1.add(new JLabel("Enter your name:"));
    m_name = Utilities.makeTextField();
    row1.add(m_name);

    JPanel row2 =  Utilities.makeRow();
    row2.add(new JLabel("Enter your age:"));
    m_age = Utilities.makeTextField();
    row2.add(m_age);

    JPanel row3 =  Utilities.makeRow();
    row3.add(Box.createHorizontalGlue());
    JButton btn = new JButton("Greeting");
    btn.addActionListener(new OnClick());
    row3.add(btn);

    JPanel center = Utilities.makeCol();
    center.add(row1);
    center.add(row2);
    center.add(Box.createVerticalGlue());
    center.add(row3);

    getContentPane().add(center, BorderLayout.CENTER);
  }

  private class OnClick implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent evt)
    {
      String msg = String.format("Hello %s, your age is %s", m_name.getText(), m_age.getText());
      JOptionPane.showMessageDialog(BoxLayoutFrame.this, msg, "Greetings!", JOptionPane.INFORMATION_MESSAGE);
    }
  }
}
