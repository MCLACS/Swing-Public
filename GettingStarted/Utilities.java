import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Utilities
{
  public static JTextField makeExpandingTextField()
  {
    JTextField tf = new JTextField();
    tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, tf.getPreferredSize().height));
    return tf;
  }

  public static JTextField makeTextField(int w)
  {
    JTextField tf = new JTextField();
    tf.setMaximumSize(new Dimension(w, tf.getPreferredSize().height));
    tf.setPreferredSize(new Dimension(w, tf.getPreferredSize().height));
    return tf;
  }

  public static JButton makeButton(String text)
  {
    return new JButton(text);
  }

  public static JPanel makeRow()
  {
    JPanel row = new JPanel();
    row.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
    return row;
  }

  public static JPanel makeCol()
  {
    JPanel col = new JPanel();
    col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
    return col;
  }

  public static boolean isNumeric(String s)
  {
    boolean ret = true;
    try
    {
      Integer.parseInt(s);
    }
    catch (NumberFormatException e)
    {
      ret = false;
    }
    return ret;
  }
}
