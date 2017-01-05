import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class LotterySimGUI extends JFrame
{
  private JLabel m_days;
  private JLabel m_dollars;
  private int m_iDays = 0;
  private int m_iDollars = 100;
  private static Random rand = new Random();

  public static void main(String args[])
  {
    JFrame frame = new LotterySimGUI();
    frame.setVisible(true);
  }

  public LotterySimGUI()
  {
    super("Lottery Simulation");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // build rows...
    JButton start = Utilities.makeButton("Start");
    JPanel row1 =  Utilities.makeRow();
    row1.add(start);
    row1.add(Box.createHorizontalGlue());

    m_days = new JLabel("");
    m_dollars = new JLabel("");

    JPanel row2 =  Utilities.makeRow();
    row2.add(new JLabel("Days: "));
    row2.add(Box.createRigidArea(new Dimension(5, 0)));
    row2.add(m_days);
    row2.add(Box.createRigidArea(new Dimension(20, 0)));
    row2.add(new JLabel("Dollars: "));
    row2.add(Box.createRigidArea(new Dimension(5, 0)));
    row2.add(m_dollars);
    row2.add(Box.createHorizontalGlue());

    // build the center panel....
    JPanel center = Utilities.makeCol();
    center.add(Box.createVerticalGlue());
    center.add(row1);
    center.add(row2);
    center.add(Box.createVerticalGlue());

    // add center and south to the content pane...
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(center, BorderLayout.CENTER);

    start.addActionListener(new OnClick());
  }

  private class OnClick implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent evt)
    {
      Simulation sim = new Simulation();
      Thread t = new Thread(sim);
      t.start();
    }
  }

  private class Simulation implements Runnable
  {
    @Override
    public void run()
    {
      Updater updater = new Updater();

      for (m_iDays = 1; m_iDays <= 365; m_iDays++)
      {
        int chance = rand.nextInt(100);
        if (chance < 50)
          m_iDollars -= 1;
        else
          m_iDollars += 1;

        try
        {
          SwingUtilities.invokeAndWait(updater);
        }
        catch (Exception e)
        {
          e.printStackTrace();}
        }
      }
    }

  private class Updater implements Runnable
  {
    @Override
    public void run()
    {
      m_days.setText(m_iDays+"");
      m_dollars.setText(m_iDollars+"");
    }
  }
}
