package com.salesforcetest.pages.salesforce.uatg;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetScreenShot {
    
    public static String capture(WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String srcName = "Image_"+randomDateTime();
        String snapNm= srcName+".png";
        //String dest = System.getProperty("user.dir") +"\\test-report\\stepWiseScreenshot\\"+snapNm;
        String dest = "C:\\stepWiseScreenshot\\"+snapNm;
        
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);        
        //String imgLocation = "//stepWiseScreenshot//"+snapNm;
        return dest;
        //return imgLocation;
    }
    public static String randomDateTime() {
	      Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
	      int second = cal.get(Calendar.SECOND);
	      String randomDateTime = String.valueOf(year) + String.valueOf(month) + String.valueOf(day)
	      + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
	      return randomDateTime;
	}
}
