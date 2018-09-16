package com.asprise.ocr;

public class OcrException
  extends RuntimeException
{
  public OcrException() {}
  
  public OcrException(String message)
  {
    super(message);
  }
  
  public OcrException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public OcrException(Throwable cause)
  {
    super(cause);
  }
}
