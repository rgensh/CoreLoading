package coreloading;

public class LoadingThreadFactory
{
  public static LoadingThread CreateThread(LoadingThreadType loadingThreadType, int taskWaitingTime)
  {
    switch(loadingThreadType)
    {
      case High:
        return new HighLoading(taskWaitingTime);
      default:
        throw new IllegalArgumentException("Unknown LoadingThreadType: " + loadingThreadType);
    }
  }
}
