import java.io.IOException;
import java.net.MalformedURLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class APIreader {

	public static String fun(String city, String country) {
		Document doc = null;
		String random;
        random="http://api.openweathermap.org/data/2.5/weather?q="+city+","+country+"&mode=html&APPID=3ea8c4603341150369ad240ffece6189";
   		try {
			doc = Jsoup.connect(random).get();
			String body = doc.body().text();
			return body;
		 } catch (MalformedURLException me) {
	            System.out.println(me); 
	            return "null";
	 
	        } catch (IOException ioe) {
	            System.out.println(ioe);
	            return "null";
	        }
	}
}
