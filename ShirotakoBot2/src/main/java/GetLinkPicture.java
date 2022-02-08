import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetLinkPicture {

	public static String getLinkPicture(String url) {
		String res = "";
		
		Document document;
		
		try {
			document = Jsoup.connect(url).get();
			
			Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			
			for (Element image : images) {
				res += image.attr("src");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
