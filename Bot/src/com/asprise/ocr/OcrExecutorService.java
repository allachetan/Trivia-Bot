package com.asprise.ocr;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OcrExecutorService
  extends ThreadPoolExecutor
{
  public static final ThreadLocal<Ocr> threadLocalOcr = new ThreadLocal();
  ConcurrentHashMap<Thread, Ocr> threadOcrMap = new ConcurrentHashMap();
  String lang;
  String speed;
  
  public OcrExecutorService(String lang, String speed)
  {
    this(lang, speed, getCpuCores());
  }
  
  public OcrExecutorService(String lang, String speed, int poolSize)
  {
    super(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque());
    this.lang = lang;
    this.speed = speed;
    Ocr.setUp();
  }
  
  private Ocr getOcr(Thread t)
  {
    Ocr ocr = (Ocr)this.threadOcrMap.get(t);
    if (ocr == null)
    {
      ocr = new Ocr();
      ocr.startEngine(this.lang, this.speed, new Object[0]);
      
      this.threadOcrMap.put(t, ocr);
    }
    return ocr;
  }
  
  protected void beforeExecute(Thread t, Runnable r)
  {
    if (threadLocalOcr.get() == null) {
      threadLocalOcr.set(getOcr(t));
    }
  }
  
  protected void afterExecute(Runnable r, Throwable t)
  {
    super.afterExecute(r, t);
  }
  
  protected void terminated()
  {
    super.terminated();
  }
  
  public void shutdown()
  {
    super.shutdown();
    stopOcrEngines();
  }
  
  public void stopOcrEngines()
  {
    for (Ocr ocr : this.threadOcrMap.values()) {
      ocr.stopEngine();
    }
  }
  
  public static class OcrCallable
    implements Callable<String>
  {
    File[] files;
    String recognizeType;
    String outputFormat;
    Object[] propSpec;
    String filesString;
    int pageIndex;
    int startX;
    int startY;
    int width;
    int height;
    
    public OcrCallable(File[] files, String recognizeType, String outputFormat, Object... propSpec)
    {
      this.files = files;
      this.recognizeType = recognizeType;
      this.outputFormat = outputFormat;
      this.propSpec = propSpec;
    }
    
    public OcrCallable(String files, int pageIndex, int startX, int startY, int width, int height, String recognizeType, String outputFormat, Object... propSpec)
    {
      this.filesString = files;
      this.pageIndex = pageIndex;
      this.startX = startX;
      this.startY = startY;
      this.width = width;
      this.height = height;
      this.recognizeType = recognizeType;
      this.outputFormat = outputFormat;
      this.propSpec = propSpec;
    }
    
    public String call()
      throws Exception
    {
      Ocr ocr = (Ocr)OcrExecutorService.threadLocalOcr.get();
      if (ocr == null) {
        throw new RuntimeException("Internal error. Ocr is not found in thread local.");
      }
      if (this.files != null) {
        return ocr.recognize(this.files, this.recognizeType, this.outputFormat, this.propSpec);
      }
      return ocr.recognize(this.filesString, this.pageIndex, this.startX, this.startY, this.width, this.height, this.recognizeType, this.outputFormat, this.propSpec);
    }
  }
  
  public static int getCpuCores()
  {
    return Runtime.getRuntime().availableProcessors();
  }
}
