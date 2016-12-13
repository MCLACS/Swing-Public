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

    // build rows...
    JPanel row1 = buildRow1();
    JPanel row2 = buildRow2();

    // build the center panel....
    JPanel center = Utilities.makeCol();
    center.add(row1);
    center.add(row2);

    // build the southern panel...
    JPanel south = Utilities.makeRow();
    south.add(Box.createHorizontalGlue());
    JButton btn = Utilities.makeButton("Greeting");
    south.add(btn);

    // add center and south to the content pane...
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(center, BorderLayout.CENTER);
    getContentPane().add(south, BorderLayout.SOUTH);

    // wire event listeners...
    btn.addActionListener(new OnClick());
  }

  private JPanel buildRow1()
  {
    JPanel row1 =  Utilities.makeRow();
    row1.add(new JLabel("Enter your name:"));
    row1.add(Box.createRigidArea(new Dimension(10, 0)));
    m_name = Utilities.makeTextField(150);
    row1.add(m_name);
    row1.add(Box.createHorizontalGlue());
    return row1;
  }

  private JPanel buildRow2()
  {
    JPanel row2 =  Utilities.makeRow();
    row2.add(new JLabel("Enter your age:"));
    row2.add(Box.createRigidArea(new Dimension(10, 0)));
    m_age = Utilities.makeTextField(50);
    row2.add(m_age);
    row2.add(Box.createHorizontalGlue());
    return row2;
  }

  private class OnClick implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent evt)
    {
      if (m_name.getText().isEmpty() || m_age.getText().isEmpty() || !Utilities.isNumeric(m_age.getText()))
      {
        JOptionPane.showMessageDialog(BoxLayoutFrame.this, "Please enter a valid name and age.", "Error!", JOptionPane.ERROR_MESSAGE);
      }
      else
      {
        String msg = String.format("Hello %s, your age is %s", m_name.getText(), m_age.getText());
        JOptionPane.showMessageDialog(BoxLayoutFrame.this, msg, "Greetings!", JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }
}
