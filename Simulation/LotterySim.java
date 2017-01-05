import java.util.*;

public class LotterySim
{
  private static Random rand = new Random();

  public static void main(String[] args)
  {
    LotterySim sim = new LotterySim();
    sim.go();
  }

  private void go()
  {
    int dollars = 100;
    for (int days = 1; days <= 365; days++)
    {
      int chance = rand.nextInt(100);
      if (chance < 50)
        dollars -= 1;
      else
        dollars += 1;
      System.out.printf("After %d day(s) you have $%d dollar(s).%n", days, dollars);
    }
  }
}
