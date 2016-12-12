import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Utilities
{
  public static JTextField makeTextField()
  {
    JTextField tf = new JTextField();
    tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, tf.getPreferredSize().height));
    return tf;
  }

  public static JPanel makeRow()
  {
    JPanel row = new JPanel();
    row.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
    row.setAlignmentX(Component.LEFT_ALIGNMENT);
    row.setAlignmentY(Component.TOP_ALIGNMENT);
    return row;
  }

  public static JPanel makeCol()
  {
    JPanel col = new JPanel();
    col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
    col.setAlignmentX(Component.LEFT_ALIGNMENT);
    col.setAlignmentX(Component.TOP_ALIGNMENT);
    return col;
  }
}
