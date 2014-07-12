package coreloading;

public class HighLoading extends LoadingThread
{
  private int m_index = 0;
  private boolean m_increase = true;

  public HighLoading(int taskWaitingTimet)
  {
    super(taskWaitingTimet);
    m_name = "HighLoading";
  }

  public HighLoading(double taskWaitingTime)
  {
    super(taskWaitingTime);
    m_name = "HighLoading";
  }
  
//  protected long DoWork()
//  {
//    for (int i = 0; i < 90000; i++)
//    {
//      if (m_increase)
//        m_index++;
//      else
//        m_index--;
//      double a = Math.sin(m_index) + Math.cos(m_index);
//      m_index = m_index + 100 * (int) a;
//
//      if (m_index > 1000000)
//        m_increase = false;
//      else if (m_index < -1000000)
//        m_increase = true;
//    }
//    return m_index;
//  }
  
  protected long DoWork()
  {
    double randomNumber = Math.random();
    for (int i = 0; i < 550000; i++)
    {
      if (m_increase)
        m_index++;
      else
        m_index--;
      double a = Math.pow(m_index + randomNumber, 0.5);
      m_index = m_index + 100 * (int) a;

      if (m_index > 1000000)
        m_increase = false;
      else if (m_index < 1)
        m_increase = true;
    }
    return m_index;
  }
}
