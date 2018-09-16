import java.io.IOException;

public class test {

	public static void main(String[] args) {
		/*String question = "Who defeated Napoleon at the Battle of Waterloo?";
		String [] answers = {
				"Jack Skellington",
				"The Duke of Wellington",
				"Beef Wellington"
		};
		
		String r1 = "";;
		try {
			r1 = search.instances(question, answers[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String r2 = "";
		try {
			r2 = search.instances(question, answers[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String r3 = "";
		try {
			r3 = search.instances(question, answers[2]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		*/
		try {
			search.get("https://www.google.com/search?q=Who+defeated+Napoleon+at+the+Battle+of+Waterloo%3F%3FJack+Skellington+-+Google+Search&rlz=1C1CHBF_enUS720US720&oq=Who+defeated+Napoleon+at+the+Battle+of+Waterloo%3F%3FJack+Skellington+-+Google+Search&aqs=chrome..69i57.907j0j7&sourceid=chrome&ie=UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
