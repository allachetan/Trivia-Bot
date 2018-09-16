import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

public class scrnsht {

	public static String getScreenShot(){
		
		try {
            Robot robot = new Robot();
            String format = "jpg";
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            String fileName = timeStamp + "screenshot.jpg";
             
            BufferedImage screenFullImage = robot.createScreenCapture(ScreenCaptureRectangle.captureRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
             
            return fileName;
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
		return null;
	}

}
