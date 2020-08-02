package stockFilterOperations;

import java.util.*;

import java.io.IOException;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.NotFound;
import com.jaunt.UserAgent;

public class FetchYahooAnalystOutlook {


	static UserAgent userAgent;
	static String ticker;
	static String url= "https://www.barchart.com/stocks/quotes/"+ticker+"/analyst-ratings";




	public FetchYahooAnalystOutlook(String ticker, UserAgent userAgent) {


		FetchYahooAnalystOutlook.ticker=ticker;
		FetchYahooAnalystOutlook.userAgent=new UserAgent();	

	}



	private boolean goToYahooPerformanceOutlook() {


		try {

			userAgent.visit("https://www.barchart.com/stocks/quotes/"+ticker+"/analyst-ratings");


			return true;


		} catch (JauntException e) {
			System.err.println(e);
			return false;
		}


	}



	private String getMarketOutlook() {

		String item = null;

		try {

			Element body = userAgent.doc.findEvery("<div class=\"block__colored-header\"");
			Element element = body.getElement(3);

			item=element.getTextContent();
			System.out.println(item);


		} catch (JauntException e) {
			System.err.println(e);
			return "N/A";
		}

		return item;





	}


	private int getCurrentRating() {

		String item = null;
		int currentRating=0;

		try {

			Element body = userAgent.doc.findEvery("<div class=\"block__average_value\"");
			Element element = body.getElement(3);
			item=element.getTextContent();

			currentRating = (int) Double.parseDouble(item); 

			System.out.println(item);


		} catch (JauntException e) {
			System.err.println(e);
			return 0;

		}

		return currentRating;





	}


	private List<String> getAnalystCriteria() {

		String item = null;
		List<String> analystCriteria=new ArrayList();
		try {

			Element body = userAgent.doc.findEvery("<ul class=\"data analyst breakdown\">");
			Element body1 = body.findEvery("<li>");
			for(int i=0;i<5;i++){
				Element element = body1.getElement(i);
				item=element.getTextContent();
				analystCriteria.add(item);

			}			
			System.out.println(analystCriteria);



		} catch (JauntException e) {
			System.err.println(e);
			

		}

		return analystCriteria;

	}

	private int getNumberOfAnalysts() {

		String item = null;
		int numOfAnalysts=0;

		try {

			Element body = userAgent.doc.findEvery("<span class=\"bold\"");
			Element element = body.getElement(3);

			item=element.getTextContent();
			numOfAnalysts = (int) Double.parseDouble(item); 

			System.out.println(numOfAnalysts);


		} catch (JauntException e) {
			System.err.println(e);
			return 0;
		}

		return numOfAnalysts;


	}



	public static void main(String[] args) {

		FetchYahooAnalystOutlook analystOutlook= new FetchYahooAnalystOutlook("TPH",userAgent);

		analystOutlook.goToYahooPerformanceOutlook();
		analystOutlook.getMarketOutlook();
		analystOutlook.getCurrentRating();
		analystOutlook.getAnalystCriteria();
		analystOutlook.getNumberOfAnalysts();


	}





}













































