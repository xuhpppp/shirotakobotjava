import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class DownloadPicture {
	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);
		
		byte[] b = new byte[2048];
		int length = 0;
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
		
		System.out.println(imageUrl + " success");
		
		is.close();
		os.close();
	}
}
