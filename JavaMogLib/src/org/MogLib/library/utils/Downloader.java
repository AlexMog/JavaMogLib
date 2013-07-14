package org.MogLib.library.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JProgressBar;

public class Downloader {
	/**
	 * Download Methods
	 * 
	 * @author AlexMog (http://alexmog.tv/)
	 */
	private Downloader() {/* Don't allow the instantiation of the class */};
	
	/**
	 * Used to download file from url to destination
	 * 
	 * @param sUrl The url of the file to download
	 * @param destination The destination of the file to download
	 * @param progressBar A progress bar to see the progression download of the file
	 * @param proxy A proxy object to select a proxy
	 * @throws Exception
	 */
	public static void downloadFile(String sUrl, String destination, JProgressBar progressBar, Proxy proxy) throws Exception {
		URL url = new URL(sUrl.replace(" ", "%20"));
		URLConnection conn;
		InputStream is;
		File f;
		File d;
		FileOutputStream fos;
		byte[] buff = new byte[1024];
		int n = 0;
		int fileSize = 0;
		
		if (proxy == null)
			conn = url.openConnection();
		else
			conn = url.openConnection(proxy);
		conn.connect();
		
		fileSize = conn.getContentLength();
		if (fileSize == -1)
			throw new Exception("Impossible to open the file " + sUrl + " - 404 Error certainly");
		
		if (progressBar != null)
			progressBar.setMaximum(progressBar.getMaximum() + fileSize);
		
		is = new BufferedInputStream(url.openStream());
		
		f = new File(destination);
    	if (!f.exists()) {
    		d = f.getParentFile();
    		if (d != null && !d.exists())
    			d.mkdirs();
    		f.createNewFile();
    	} else
    		f.delete();
        fos = new FileOutputStream(destination);
        
        while ((n = is.read(buff)) != -1) {
            fos.write(buff, 0, n);
            if (progressBar != null)
            	progressBar.setValue(progressBar.getValue() + n);
        }
    	
        fos.flush();
    	fos.close();
    	is.close();
	}
	
	/**
	 * Used to download file from url to destination
	 * 
	 * @param sUrl The url of the file to download
	 * @param destination The destination of the file to download
	 * @throws Exception
	 */
	public static void downloadFile(String sUrl, String destination) throws Exception {
		downloadFile(sUrl, destination, null, null);
	}
	
	/**
	 * Used to download file from url to destination
	 * 
	 * @param sUrl The url of the file to download
	 * @param destination The destination of the file to download
	 * @param progressBar A progressBar to the file progression
	 * @throws Exception
	 */
	public static void downloadFile(String sUrl, String destination, JProgressBar progressBar) throws Exception {
		downloadFile(sUrl, destination, progressBar, null);
	}
}
