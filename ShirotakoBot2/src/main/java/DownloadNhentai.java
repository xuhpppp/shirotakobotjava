import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class DownloadNhentai {
	public static void downloadNhentai(int countPage, String destinationFile, String gallery) throws IOException {
		
		InputStream is = null;
		OutputStream os = null;
		
		for (int i = 1; i <= countPage; i++) {
			URL imageUrl = new URL("https://i.nhentai.net/galleries/" + gallery + "/" + i + ".jpg");
			URL imageUrl2 = new URL("https://i.nhentai.net/galleries/" + gallery + "/" + i + ".png");
			
			try {
				is = imageUrl.openStream();
				os = new FileOutputStream(destinationFile + i + ".jpg");
			} catch (Exception e) {
				is = imageUrl2.openStream();
				os = new FileOutputStream(destinationFile + i + ".png");
			}

			byte[] b = new byte[8192];
			int length = 0;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			
			System.out.println(i + " success");
		}
		
		is.close();
		os.close();
	}
}
