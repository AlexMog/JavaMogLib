package org.MogLib.library.utils;

import java.io.RandomAccessFile;
import java.security.MessageDigest;

public class HashFileSum {
	/**
	 * HashFile methods
	 *
	 * @author AlexMog (http://alexmog.tv/)
	 */
	private HashFileSum() { /* Don't allow the instantiation of this class */};

	/**
	 * Returns the MD5 hash of the file
	 * 
	 * @param file The file to calculate the MD5 sum
	 * @return The MD5 hash
	 * @throws Exception
	 */
	public static String md5sum(String file) throws Exception {
		byte[] data = readFile(file);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(data);
		byte[] digest = md.digest();
		
		StringBuffer hashString = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			String hex = Integer.toHexString(digest[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			}else
				hashString.append(hex.substring(hex.length() - 2));
		}
		
		return (hashString.toString());
	}
	
	/**
	 * Returns the SHA-1 hash of the file
	 * 
	 * @param file The file to calculate the SHA-1 sum
	 * @return The SHA-1 hash
	 * @throws Exception
	 */
	public static String sha1sum(String file) throws Exception {
		byte[] data = readFile(file);
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(data);
		byte[] digest = md.digest();
		
		StringBuffer hashString = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			String hex = Integer.toHexString(digest[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			}else
				hashString.append(hex.substring(hex.length() - 2));
		}
		
		return (hashString.toString());
	}

	/**
	 * Used to read the file fully
	 * @param filename the file to read
	 * @return datas readed
	 * @throws Exception
	 */
	private static byte[] readFile(String filename) throws Exception {
		RandomAccessFile raf = new RandomAccessFile(filename, "r");
		byte[] data = new byte[(int)raf.length()];
		raf.readFully(data);
		raf.close();
		
		return (data);
	}
}
