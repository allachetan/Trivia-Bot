import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.text.html.parser.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class tersre {

	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

	public static void main(String[] args) {
		
		String question = "Pope supports donald Trump";

	        //Fetch the page
	        Document doc = null;
			try {
				doc = Jsoup.connect("https://google.com/search?q=" + question).userAgent(USER_AGENT).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        //Traverse the results
	        for (org.jsoup.nodes.Element result : doc.select("h3.r a")){

	            final String title = result.text();
	            final String url = result.attr("href");

	            //Now do something with the results (maybe something more useful than just printing to console)

	            System.out.println(title + " -> " + url);
	            System.out.println(check(url));
	            break;
	        }

		
	}
	
	public static boolean check(String url){
		
		String text = "";
		try {
			text = get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(text);
		
		return false;
		
	}
	
	public static String get(String url) throws Exception {
		   StringBuilder sb = new StringBuilder();
		   for(Scanner sc = new Scanner(new URL(url).openStream()); sc.hasNext(); )
		      sb.append(sc.nextLine()).append('\n');
		   return sb.toString();
		}
	

}
