package coreloading;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MiddleLoading extends LoadingThread
{

  public MiddleLoading(int taskWaitingTime, long iterationsCount)
  {
    super(taskWaitingTime);
    m_name = "MiddleLoading";
  }

  @Override
  protected long DoWork()
  {
    long inputLineLength = 0;
    try
    {
      //URL url = new URL("http://www.google.com/");
      URL url = new URL("http://www.google.com/");
      URLConnection urlConnection = url.openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
      String inputLine;
      while ((inputLine = reader.readLine()) != null)
      {
        inputLineLength++;
      }
      reader.close();
    } 
    catch (Exception exception)
    {
      System.out.println(exception.getMessage());
    }
    return inputLineLength;
  }
}
