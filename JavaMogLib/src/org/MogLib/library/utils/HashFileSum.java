package org.MogLib.library.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class HashFileSum {
	/**
	 * HashFile methods
	 * Thoes methods are not mine, thx to Mr-Hide to have done it.
	 *
	 * @author Mr-Hide (JB, http://www.mrhide.fr), Adapted by AlexMog (http://alexmog.tv/)
	 */
	private HashFileSum() { /* Don't allow the instantiation of this class */};
	
	/**
	 * Returns the SHA-1 encrypton of the file
	 * 
	 * @param file The file who you want the SHA-1 encrypton
	 * @return The SHA-1 encrypton
	 * @throws Exception 
	 */
	public static String sha1sum(File file) throws Exception{
		String localSha1Sum = null;
		
		if (file.exists() && file.isFile() && file.canRead()) {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md);
			dis.on(true);
			
			while (dis.read() != -1);

			byte[] b = md.digest();
			localSha1Sum = getHexString(b);
			dis.close();
		} else
			throw new Exception("Impossible to open the file " + file.getAbsolutePath());
	    return (localSha1Sum);
	}
	
	/**
	 * Returns the MD5 encrypton of the file
	 * 
	 * @param file the file you want to know the MD5 encrypton
	 * @return The MD5 encrypton of the file
	 * @throws Exception 
	 */
	public static String md5sum(File file) throws Exception {
	    String localMd5Sum = null;
	    
	    if (file.exists() && file.isFile() && file.canRead()){
			MessageDigest md = MessageDigest.getInstance("MD5");
			DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md);
			dis.on(true);

			while (dis.read() != -1);
			byte[] b = md.digest();
			localMd5Sum = getHexString(b);
			dis.close();
	    } else
	    	throw new Exception("Impossible to open the file " + file.getAbsolutePath());
	    return (localMd5Sum);
	}
	
	/**
	 * Used to convert the byte value to Hex value
	 * 
	 * @param bytes the bytes
	 * @return HexValue calculated
	 */
	private static String getHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			if (b <= 0x0F && b >= 0x00) {
				sb.append('0');
	        }
			sb.append( String.format("%x", b) );
		}
		return sb.toString();
	}
}
