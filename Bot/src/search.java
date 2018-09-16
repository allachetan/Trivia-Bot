import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class search {

	public static long results(String question, String answer) {
		
		String QandA = question + "?" +  answer;
		String [] split = QandA.split(" ");
		String query = "";
		for(int i = 0; i < split.length; i ++){
			query = query + split[i] + "+";
		}
		
		String url = "https://www.google.com/search?q=" + query;

		Document document = null;
		try {
			document = Jsoup //
			                   .connect(url) //
			                   .userAgent("Mozilla/5.0 (Windows; U; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)") //
			                   .get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		org.jsoup.nodes.Element divResultStats = document.select("div#resultStats").first();
		if (divResultStats==null) {
		    throw new RuntimeException("Unable to find results stats.");
		}
		
		System.out.println((divResultStats).text());
		String results = ((divResultStats).text());
		int a = 0;
		if(results.length() >= 10)
			a = 6;
		int b = 1;
		if(results.contains("results"))
			b = results.indexOf(results);
		results = results.replaceAll("About ", "");
		results = results.replaceAll(" results", "");
		results = results.replaceAll(" ", "");
		results = results.replaceAll(",", "");
		long total = Long.parseLong(results);
		
		return total;
		
	}
	
	public static String instances(String question, String answer) throws MalformedURLException, IOException{
		String QandA = question + "?" +  answer;
		String [] split = QandA.split(" ");
		String query = "";
		for(int i = 0; i < split.length; i ++){
			query = query + split[i] + "+";
		}
		
		String URL = "https://www.google.com/search?q=" + query;

		// Make a URL to the web page
        URL url = new URL(URL);

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.


        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        
        String text = "";
        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            text = text + " " +  line;
        }
			
        return text;
	}
	public static void get(String URL) throws Exception {

		URL url;

		try {
			// get URL content
			url = new URL(URL);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

			String inputLine;

			//save to this filename
			String fileName = "/users/mkyong/test.txt";
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			//use FileWriter to write file
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			while ((inputLine = br.readLine()) != null) {
				bw.write(inputLine);
			}

			bw.close();
			br.close();

			System.out.println("Done");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
}
