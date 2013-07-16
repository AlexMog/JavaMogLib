package org.MogLib.library.utils;

import java.io.File;
import java.util.ArrayList;

public class DirUtils {
	/**
	 * Class used to dir functions
	 * 
	 * @author AlexMog (http://alexmog.tv/)
	 */
	private DirUtils() {/* Don't allow the instantiation of this class*/};
	
	/**
	 * Function used to generate a list of files contained in the directory and sub-directories
	 * 
	 * @param path The path of the directory
	 * @return An ArrayList<String> containing the files
	 * @throws Exception
	 */
	public static ArrayList<String> readDir(File path) throws Exception {
		ArrayList<String> listFiles = new ArrayList<String>();
		String currentFilePath;
		File[] list;
		
		if (path.isDirectory()) {
		    list = path.listFiles();
		    
		    if (list != null) {
				for (int i = 0; i < list.length; i++)
				    listFiles.addAll(readDir(list[i]));
		    } else
		    	throw new Exception("File read error");
		} else {
		    currentFilePath = path.getAbsolutePath();
		    listFiles.add(currentFilePath);
		}
		return (listFiles);
    }
	
	/**
	 * Used to copy a directory
	 * 
	 * @param dir The directory to copy
	 * @param destination The destination of the directory
	 * @throws Exception
	 */
	public static void copyDir(File dir, File destination) throws Exception {
		ArrayList<String> files = readDir(dir);
		File file;
		File d;
		
		if (!dir.isDirectory() || !destination.isDirectory())
			throw new Exception("Dir or Destination is not a directory.");
		
		for(String sFile : files) {
			file = new File(destination.getAbsolutePath() + sFile.replace(dir.getAbsolutePath(), ""));
			d = file.getParentFile();
			if (!d.exists())
				d.mkdirs();
			FileUtils.copyFile(new File(sFile), file);
		}
	}
	
	/**
	 * Used to move a directory
	 * 
	 * @param dir The directory to move
	 * @param destination The destination of the directory
	 * @throws Exception
	 */
	public static void moveDir(File dir, File destination) throws Exception {
		ArrayList<String> files = readDir(dir);
		File file;
		File d;
		
		if (!dir.isDirectory() || !destination.isDirectory())
			throw new Exception("Dir or Destination is not a directory.");
		
		for(String sFile : files) {
			file = new File(destination.getAbsolutePath() + sFile.replace(dir.getAbsolutePath(), ""));
			d = file.getParentFile();
			if (!d.exists())
				d.mkdirs();
			FileUtils.moveFile(new File(sFile), file);
		}
	}
}
