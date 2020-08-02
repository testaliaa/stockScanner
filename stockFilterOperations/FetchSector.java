package stockFilterOperations;

import com.jaunt.Element;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class FetchSector {
	private static UserAgent userAgent;
	private static String ticker;
	private String url= "https://finance.yahoo.com/quote/"+ticker+"/profile?p=" + ticker;

	public FetchSector(String ticker,UserAgent userAgent) {

		FetchSector.ticker=ticker;
		FetchSector.userAgent=new UserAgent();

	}


	public boolean goToYahooFinanceProfile() {


		try {

			userAgent.visit("https://finance.yahoo.com/quote/"+ticker+"/profile?p=" + ticker);


			return true;


		} catch (JauntException e) {
			System.err.println(e);
			return false;
		}


	}
	private String getSector() {

		String item = null;

		try {

			Element body = userAgent.doc.findEvery("<span class=");
			Element element = body.getElement(0);

			item=element.getTextContent();
			System.out.println(item);


		} catch (JauntException e) {
			System.err.println(e);

		}
		return item;


	}

	
public static void main(String[] args) {
	
	FetchSector fetch= new FetchSector("GH", userAgent);
	fetch.goToYahooFinanceProfile();
	fetch.getSector();
	
}
	
	
	
	
	
	
	
}
