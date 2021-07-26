package com.salesforcetest.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

public class StartTest {
	
	@Test
	public void kill_chrome_driver_instance() throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "taskkill /f /im chromedriver.exe");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }
}
