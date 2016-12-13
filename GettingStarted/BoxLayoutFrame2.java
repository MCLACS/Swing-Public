import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoxLayoutFrame2 extends JFrame
{
  public static void main(String args[])
  {
    JFrame frame = new BoxLayoutFrame2();
    frame.setVisible(true);
  }

  public BoxLayoutFrame2()
  {
    super("Boxlayout Example 2");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // build rows...
    JPanel row1 =  Utilities.makeRow();
    row1.add(Utilities.makeButton("Button 10"));
    row1.add(Box.createRigidArea(new Dimension(20, 0)));
    row1.add(Utilities.makeButton("Button 11"));
    row1.add(Box.createRigidArea(new Dimension(20, 0)));
    row1.add(Utilities.makeButton("Button 12"));
    row1.add(Box.createRigidArea(new Dimension(20, 0)));
    row1.add(Utilities.makeButton("Button 13"));
    row1.add(Box.createHorizontalGlue());

    JPanel row2 =  Utilities.makeRow();
    row2.add(Box.createHorizontalGlue());
    row2.add(Utilities.makeButton("Button 2"));

    // build the center panel....
    JPanel center = Utilities.makeCol();
    center.add(Box.createVerticalGlue());
    center.add(row1);
    center.add(row2);
    center.add(Box.createVerticalGlue());
    center.setBorder(BorderFactory.createLineBorder(Color.red, 5, true));

    // add center and south to the content pane...
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(center, BorderLayout.CENTER);
  }
}
