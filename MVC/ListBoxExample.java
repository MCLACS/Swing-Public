import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class ListBoxExample extends JFrame
{
  private DefaultListModel<File> m_listModel;
  private JList<File> m_list;
  private JTextArea m_text;

  public static void main(String args[])
  {
    JFrame frame = new ListBoxExample();
    frame.setVisible(true);
  }

  public ListBoxExample()
  {
    super("ListBox Example");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // build the models...
    m_listModel = new DefaultListModel<File>();
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
    m_list.addListSelectionListener(new OnChange());
  }

  private JPanel buildRowTop()
  {
    m_list = new JList<File>(m_listModel);
    m_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane = new JScrollPane(m_list);

    JPanel row1 =  Utilities.makeRow();
    row1.add(new JLabel("Java Files:"));
    row1.add(Box.createHorizontalGlue());

    JPanel col = Utilities.makeCol();
    col.add(row1);
    col.add(Box.createRigidArea(new Dimension(0, 5)));
    col.add(scrollPane);

    JPanel row2 =  Utilities.makeRow();
    row2.add(col);

    return row2;
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
      m_listModel.addElement(f);
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
  private class OnChange implements ListSelectionListener
  {
    @Override
    public void valueChanged(ListSelectionEvent evt)
    {
      ListSelectionModel lsm = m_list.getSelectionModel();
      if (!lsm.getValueIsAdjusting())
      {
        if (lsm.isSelectionEmpty())
        {
          m_text.setText("");
        }
        else
        {
          int index = lsm.getMaxSelectionIndex();
          File file = m_listModel.getElementAt(index);
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
  }
}
