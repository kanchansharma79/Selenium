package utils;

import java.io.*;

public class FileUtils {

	public static void copyFile(File soureFile, File destinationFile) throws IOException {
		InputStream input = null;
		    OutputStream output = null;
		    try {
		        input = new FileInputStream(soureFile);
		        output = new FileOutputStream(destinationFile);
		        byte[] buf = new byte[1024];
		        int bytesRead;
		        while ((bytesRead = input.read(buf)) > 0) {
		            output.write(buf, 0, bytesRead);
		        }
		    } finally {
		        input.close();
		        output.close();
		    }

	}
}
