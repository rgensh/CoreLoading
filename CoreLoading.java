package coreloading;

import coreloading.HighLoading;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CoreLoading 
{
  
  
    public static void main(String[] args) throws InterruptedException, IOException 
    {
     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Press Enter");
      int key = bufferedReader.read();
      System.out.println("Start");
      ThreadsList threadsList = new ThreadsList(LoadingThreadType.High, 0);
      threadsList.StopAllThreads();
 

//      System.out.printf("     Thread average execution time: %d \r\n", (int)LoadingThreadFactory.CreateThread(LoadingThreadType.High, 0).GetAverageExecutionTime(300));
//      for (int j = 0; j < 20; j++)
//      {
//      System.out.println("J = " + j);
//      List<LoadingThread> threads = new ArrayList<LoadingThread>();
//      
//        for (int i = 0; i < j; i++)
//        {
//          LoadingThread thread = LoadingThreadFactory.CreateThread(LoadingThreadType.High, 30);           
//          threads.add(thread);
//          thread.start();
//        }
//        
//        Thread.sleep(10000);
//        int i = 0;
//        for (LoadingThread thread: threads)
//        {
//          System.out.printf("     Thread №%d execution time: %d \r\n", ++i, (int)thread.GetCurrentExecutionTime());
//          thread.StopThread();
//        }
//        
//        for (LoadingThread thread: threads)
//        {
//          thread.join();
//        }
//        System.out.println("Finish\r\n\r\n");
//      }
		//new HighLoading(0, 1000).start();   
		//new HighLoading().start();   
		//new HighLoading().start();   
		//new HighLoading().start();             
		//new HighLoading().start();   
		//new HighLoading().start();    
    }
}