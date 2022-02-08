import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CountPageNhentai {

	public static int countPageNhentai(String url) {
		Document document;
		
		int count = 0;
		
		try {
			document = Jsoup.connect(url).get();
			
			Element content = document.getElementById("content");
			Elements divs = content.getElementsByTag("div");
			
			for (Element div : divs) {
				if (div.hasClass("thumb-container")) {
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
