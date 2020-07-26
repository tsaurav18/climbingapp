package util;
import java.io.*;


public class Util {
	  public static void saveImage(String base, String id, String filename, byte[] data) throws IOException{
			File f=  new File(base);
			if(!f.exists()) f.mkdir();
			
			//make a dir for each user id if not exist
			String dir = base +"/"+id;
			f = new File(dir);
			if(!f.exists()) f.mkdir();
			
			 FileOutputStream out = new FileOutputStream(new File(dir+"/"+filename));
			 out.write(data);
			 out.close();
		}
}
