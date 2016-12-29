import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class ComboBoxExample extends JFrame
{
  private DefaultComboBoxModel<File> m_comboModel;
  private JComboBox<File> m_combo;
  private JTextArea m_text;

  public static void main(String args[])
  {
    JFrame frame = new ComboBoxExample();
    frame.setVisible(true);
  }

  public ComboBoxExample()
  {
    super("ComboBox Example");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // build the models...
    m_comboModel = new DefaultComboBoxModel<File>();
    populateList();

    // build the views...
    JPanel rowTop = buildRowTop();
    JPanel rowBottom = buildRowBottom();
    getContentPane().setLayout(new BorderLayout());
    JPanel center = Utilities.makeCol();
    center.add(rowTop);
    center.add(rowBottom);
    getContentPane().add(center, BorderLayout.CENTER);

    // build the controllers...
    m_combo.addActionListener(new OnChange());
  }

  private JPanel buildRowTop()
  {
    m_combo = new JComboBox<File>(m_comboModel);
    m_combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

    JPanel row =  Utilities.makeRow();
    row.add(new JLabel("Java Files:"));
    row.add(Box.createRigidArea(new Dimension(5, 0)));
    row.add(m_combo);

    return row1;
  }

  private JPanel buildRowBottom()
  {
    m_text = new JTextArea(10, 80);
    JScrollPane scrollPane = new JScrollPane(m_text);
    m_text.setEditable(false);

    JPanel row2 =  Utilities.makeRow();
    row2.add(scrollPane);

    return row2;
  }

  private void populateList()
  {
    File currentDir = new File(".");
    File[] files = currentDir.listFiles(new Filter());
    for (File f : files)
    {
      m_comboModel.addElement(f);
    }
  }

  private class Filter implements FilenameFilter
  {
      @Override
      public boolean accept(File dir, String name)
      {
        return name.toUpperCase().endsWith(".JAVA");
      }
  }

  // this handler serves as the controller...
  private class OnChange implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent evt)
    {
      int index = m_combo.getSelectedIndex();
      File file = m_comboModel.getElementAt(index);
      try (Scanner sc = new Scanner(file))
      {
        String code = "";
        while (sc.hasNextLine())
        {
          code = code + sc.nextLine() + System.lineSeparator();
        }
        m_text.setText(code);
        m_text.setCaretPosition(0);
      }
      catch (FileNotFoundException ex)
      {
        m_text.setText(ex.getMessage());
      }
    }
  }
}
