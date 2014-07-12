package coreloading;

import static java.lang.Thread.sleep;
import coreloading.HighLoading;
import java.util.Calendar;

public abstract class LoadingThread extends Thread
{
	protected String m_name;
	
  private int m_taskWaitingTime;
  private double m_taskWaitingTimePart;
	private boolean m_stopExecution;
  private boolean m_clearStatistics;
  
  private long m_executionTimeSum = 0;
  private long m_iterationsCount = 0;
  
	protected LoadingThread(int taskWaitingTime)
	{
		m_taskWaitingTime = taskWaitingTime;
	}
  
  //taskWaitingTime - part of execution time
  protected LoadingThread(double taskWaitingTime)
	{
    if (taskWaitingTime < 0 || taskWaitingTime > 5)
      throw new IllegalArgumentException("taskWaitingTime should be in interval [0..5]. taskWaitingTime = " + taskWaitingTime);
		m_taskWaitingTimePart = taskWaitingTime;
	}
	
	protected abstract long DoWork();
   
	public void run()
	{
    System.out.println(Calendar.getInstance().getTime() + " " + m_name + " started");
    long startTime;
		long executionTime;
            
    while(true)
		{
			startTime = System.currentTimeMillis();
			DoWork();
			executionTime = (System.currentTimeMillis() - startTime);
			m_executionTimeSum += executionTime;
			
			try
			{
				if (m_taskWaitingTime > 0)
					sleep(m_taskWaitingTime);
        else if (m_taskWaitingTimePart > 0.00001) 
          sleep((long)(executionTime * m_taskWaitingTimePart));
			}
			catch(InterruptedException exception)
			{
				System.out.println(exception.getMessage());
			}
      
      m_iterationsCount++;
      
      if (m_clearStatistics)
      {
        m_executionTimeSum = 0;
        m_iterationsCount = 0;
        m_clearStatistics = false;
      }
      
      if (m_stopExecution)
        break;
		}
		
    //if (m_iterationsCount > 0)
      //System.out.println("Average execution time of " + m_name + " is " + (int)(m_executionTimeSum / m_iterationsCount) + " ms");
		System.out.println(Calendar.getInstance().getTime() + " "+ m_name + " completed!!!");
    System.out.println(m_name + " completed!!!");
	}
  
  public void StopThread()
  {
    m_stopExecution = true;
  }
  
  public void ClearStatistics()
  {
    m_clearStatistics = true;
  }
  
  public double GetCurrentExecutionTime()
  {
    if (m_iterationsCount > 0)
      return m_executionTimeSum / (double)m_iterationsCount;
    
    return 0;
  }
  
  public double GetAverageExecutionTime(int iterationsCount)
  {
    long startTime;
    long executionTimeSum = 0;
    
    for (int i = 0; i < iterationsCount; i++)
		{
			startTime = System.currentTimeMillis();
			DoWork();
			long executionTime = (System.currentTimeMillis() - startTime);
			executionTimeSum += executionTime;
    }
    
    return executionTimeSum / (double)iterationsCount;
  }
}