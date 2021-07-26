package com.salesforcetest.pages.aao.manager;

import com.salesforcetest.pages.aao.DataProvider.AaoFileReader;

public class FileManager {
	
	private AaoFileReader reader;
	
	private FileManager() {
}

	private static class FileManagerHelper{
		private static final FileManager INSTANCE=new FileManager();
	}
	
	public static FileManager  getInstance() {
		
		return FileManagerHelper.INSTANCE;
		
	}
	
	public AaoFileReader getReader()
	{
		 return (reader == null) ? new AaoFileReader() : reader;
	}
	
}
