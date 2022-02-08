import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetGallery {

	public static String getGallery(String url) throws IOException {
		String gallery = "";
		
		Document document;
		
		document = Jsoup.connect(url).get();
		
		Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

		gallery += images.get(0).attr("src");
		
		String galleryCode = "";
		for (int i = 0; i < gallery.length(); i++) {
			if (gallery.charAt(i) >= '0' && gallery.charAt(i) <= '9') {
				galleryCode += gallery.charAt(i);
			}
		}
		
		return galleryCode;
	}

}
