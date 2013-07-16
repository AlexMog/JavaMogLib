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
	 * @param recurcive Set it to true to use recursivity
	 * @return An ArrayList<String> containing the files
	 * @throws Exception
	 */
	public static ArrayList<String> readDir(File path, boolean recurcive) throws Exception {
		ArrayList<String> listFiles = new ArrayList<String>();
		
		if (path.isDirectory() && recurcive) {
		    File[] list = path.listFiles();
		    if (list != null) {
				for (int i = 0; i < list.length; i++)
				    listFiles.addAll(readDir(list[i], recurcive));
		    } else
		    	throw new Exception("File read error");
		} else if (!path.isDirectory()) {
		    String currentFilePath = path.getAbsolutePath();
		    listFiles.add(currentFilePath);
		}
		return (listFiles);
    }
	
	/**
	 * Function used to generate a list of files contained in the directory and sub-directories
	 * 
	 * @param path The path of the directory
	 * @return An ArrayList<String> containing the files
	 * @throws Exception
	 */
	public static ArrayList<String> readDir(File path) throws Exception {
		return (readDir(path, true));
	}
}
