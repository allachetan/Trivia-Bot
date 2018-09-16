import java.io.File;

import com.asprise.ocr.Ocr;

public class ocrEngine {
  public static String getText(String name) {
	  Ocr.setUp(); // one time setup
	  Ocr ocr = new Ocr(); // create a new OCR engine
	  ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
	  String s = ocr.recognize(new File[] {new File(name)}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
	  // ocr more images here ...
	  ocr.stopEngine();
	  return s;
  }
}