package org.MogLib.library.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JProgressBar;

public class FileUtils {
	/**
	 * Some file utilities
	 * 
	 * @author AlexMog (http://alexmog.tv/)
	 */
	
	/**
	 * Used to copy file to destination
	 * 
	 * @param file The file to copy
	 * @param destination The destination where he will be copied
	 * @param progressBar A progress bar to see the progression of the file
	 * @throws IOException 
	 */
	public static void copyFile(String file, String destination, JProgressBar progressBar) throws IOException {
		InputStream is;
		OutputStream os;
		byte[] buff = new byte[1024];
		int readed = 0;
		
		is = new FileInputStream(new File(file));
		os = new FileOutputStream(new File(destination));
		
		if (progressBar != null)
			progressBar.setMaximum(progressBar.getMaximum() + file.length());
		
		while ((readed = is.read(buff)) != -1) {
			os.write(buff);
			if (progressBar != null)
				progressBar.setValue(progressBar.getValue() + readed);
		}
		
		os.flush();
		os.close();
		is.close();
	}
	
	/**
	 * Used to copy file to destination
	 * 
	 * @param file The file to copy
	 * @param destination The destination where he will be copied
	 * @throws IOException A progress bar to see the progression of the file
	 */
	public static void copyFile(String file, String destination) throws IOException {
		copyFile(file, destination, null);
	}
}
