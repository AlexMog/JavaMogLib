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
	private FileUtils() {/* Don't allow the instantiation of this class*/}
	
	/**
	 * Used to copy file to destination
	 * 
	 * @param file The file to copy
	 * @param destination The destination where he will be copied
	 * @param progressBar A progress bar to see the progression of the file
	 * @throws IOException 
	 */
	public static void copyFile(File file, File destination, JProgressBar progressBar) throws IOException {
		InputStream is;
		OutputStream os;
		byte[] buff = new byte[1024];
		int readed = 0;
		
		if (file.isDirectory()) {
			destination.mkdirs();
			return ;
		}
		
		is = new FileInputStream(file);
		os = new FileOutputStream(destination);
		
		if (progressBar != null)
			progressBar.setMaximum((int) (progressBar.getMaximum() + file.length()));
		
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
	public static void copyFile(File file, File destination) throws IOException {
		copyFile(file, destination, null);
	}
	
	/**
	 * Used to move file to destination
	 * 
	 * @param file The file to move
	 * @param destination The destination where he will be moved
	 * @param progressBar A progress bar to see the progression of the file
	 * @throws IOException 
	 */
	public static void moveFile(File file, File destination, JProgressBar progressBar) throws IOException {
		InputStream is;
		OutputStream os;
		byte[] buff = new byte[1024];
		int readed = 0;
		
		if (file.isDirectory()) {
			destination.mkdirs();
			return ;
		}
		
		is = new FileInputStream(file);
		os = new FileOutputStream(destination);
		
		if (progressBar != null)
			progressBar.setMaximum((int) (progressBar.getMaximum() + file.length()));
		
		while ((readed = is.read(buff)) != -1) {
			os.write(buff);
			if (progressBar != null)
				progressBar.setValue(progressBar.getValue() + readed);
		}
		
		file.delete();
		
		os.flush();
		os.close();
		is.close();
	}
	
	/**
	 * Used to move file to destination
	 * 
	 * @param file The file to move
	 * @param destination The destination where he will be moved
	 * @throws IOException A progress bar to see the progression of the file
	 */
	public static void moveFile(File file, File destination) throws IOException {
		moveFile(file, destination, null);
	}
}
