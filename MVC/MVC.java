import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class MVC extends JFrame
{
  private DefaultListModel<File> m_listModel;
  private JList<File> m_list;
  private JTextArea m_text;

  public static void main(String args[])
  {
    JFrame frame = new MVC();
    frame.setVisible(true);
  }

  public MVC()
  {
    super("BoxLayout Example");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // build rows...
    JPanel row1 = buildRow1();
    JPanel row2 = buildRow2();


    // build the center panel....
    JPanel center = Utilities.makeCol();
    center.add(row1);
    center.add(row2);

    // add center to the content pane...
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(center, BorderLayout.CENTER);

    populateList();

    // wire event listeners...
    m_list.addListSelectionListener(new OnChange());
  }

  private JPanel buildRow1()
  {
    m_listModel = new DefaultListModel<File>();
    m_list = new JList<File>(m_listModel);
    JScrollPane scrollPane = new JScrollPane(m_list);

    JPanel row1 =  Utilities.makeRow();
    row1.add(new JLabel("Java Files:"));
    row1.add(Box.createRigidArea(new Dimension(10, 0)));
    row1.add(scrollPane);
    row1.add(Box.createHorizontalGlue());
    return row1;
  }

  private JPanel buildRow2()
  {
    m_text = new JTextArea(10, 80);
    JScrollPane scrollPane = new JScrollPane(m_text);
    m_text.setEditable(false);

    JPanel row2 =  Utilities.makeRow();
    row2.add(scrollPane);
    row2.add(Box.createHorizontalGlue());

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

  private class OnChange implements ListSelectionListener
  {
    @Override
    public void valueChanged(ListSelectionEvent evt)
    {
      ListSelectionModel lsm = m_list.getSelectionModel();
      if (!evt.getValueIsAdjusting())
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
          }
          catch (FileNotFoundException ex)
          {
            m_text.setText(ex.getMessage());
          }
        }
      }
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
}
