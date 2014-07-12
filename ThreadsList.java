
package coreloading;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThreadsList
{
  private final int SleepCoefficient = 1000;
  
  private List<LoadingThread> m_loadingThreads = new ArrayList<LoadingThread>();
  private double m_averageExecutionTime;

  public ThreadsList(LoadingThreadType loadingThreadType, int waitingTime) throws InterruptedException
  {
    LoadingThread loadingThread = LoadingThreadFactory.CreateThread(loadingThreadType, waitingTime);
    m_averageExecutionTime = loadingThread.GetAverageExecutionTime(1000);
    m_loadingThreads.add(loadingThread);
    loadingThread.start();

    while(true)
    { 
      System.out.println("   Check perfirmance.");
      if (IsPerformanceStable())
      {
        loadingThread = LoadingThreadFactory.CreateThread(loadingThreadType, waitingTime);
        m_loadingThreads.add(loadingThread);
        System.out.printf("   Perfirmance is stable, add thread. List size: %d\r\n", m_loadingThreads.size());
        loadingThread.start();
      }
      else
      {
        LoadingThread threadToDelete = m_loadingThreads.remove(m_loadingThreads.size() - 1);
        threadToDelete.StopThread();
        System.out.printf("   Perfirmance is NOT stable, delete thread. List size: %d\r\n", m_loadingThreads.size());
        break;          
      }
    }
    
    System.out.printf("\r\nList of %S loading threads has been created. List size: %d\r\n", loadingThreadType.name(), m_loadingThreads.size());
    System.out.printf("Average thread execution time = %d ms \r\n", (int)m_averageExecutionTime);
  }

  public boolean IsPerformanceStable() throws InterruptedException
  {
    double currentExecutionTime = 0;
    int performanceDegradationCounter = 0;
    for(LoadingThread thread : m_loadingThreads)
      thread.ClearStatistics();
    
    Thread.sleep((int)(SleepCoefficient * m_averageExecutionTime));

    int i = 0;
    for(LoadingThread thread : m_loadingThreads)
    {
      currentExecutionTime = thread.GetCurrentExecutionTime();
      System.out.printf("     Thread №%d execution time: %d \r\n", ++i, (int)currentExecutionTime);
      
      if (currentExecutionTime <= m_averageExecutionTime)
        continue;

      if (m_averageExecutionTime == 0 || 
          currentExecutionTime/m_averageExecutionTime > 1.1)
      {
        performanceDegradationCounter++;
      }
    }
    
    return (2 * performanceDegradationCounter < m_loadingThreads.size());
  }
  
  public void StopAllThreads()
  {
    for (LoadingThread thread : m_loadingThreads)
      thread.StopThread();
  }
}
