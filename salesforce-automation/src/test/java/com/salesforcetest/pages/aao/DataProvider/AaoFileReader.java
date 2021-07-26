package com.salesforcetest.pages.aao.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.salesforcetest.pages.aao.manager.DriverType;

public class AaoFileReader {

	 private Properties properties;
  private final String propertyFilePath= "configs//Properties";
   
   public AaoFileReader(){
       BufferedReader reader;
       try {
           reader = new BufferedReader(new FileReader(propertyFilePath));
           properties = new Properties();
           try {
               properties.load(reader);
               reader.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
           throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
       }       
   }
   
   public String getUserId(){
       String userid = properties.getProperty("userid");
       if(userid!= null) return userid;
       else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");      
   }
   
   public String getPassWord() {       
       String pwd = properties.getProperty("password");
       if(pwd != null) return pwd;
       else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");      
   }
   
   public String getApplicationUrl() {
       String url = properties.getProperty("url");
       if(url != null) return url;
       else throw new RuntimeException("url not specified in the Configuration.properties file.");
       
   }
   
   public String getEnvironment() {
       String env = properties.getProperty("environment");
       if(env != null) return env;
       else throw new RuntimeException("url not specified in the Configuration.properties file.");
       
   }
   
   public DriverType getBrowser() {
	   String browserName = properties.getProperty("browser");
	   if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
	   else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
	   else if(browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
	   else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	   }
   
}