package stockFilterOperations;

import com.jaunt.UserAgent;

import java.util.*;

import java.io.IOException;

import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;


public class FetchYahooSummary {

	private static UserAgent userAgent;
	private static String ticker;
	private String url= "https://finance.yahoo.com/quote/" + ticker + "?p=" + ticker + "&.tsrc=fin-srch";

	public FetchYahooSummary(String ticker,UserAgent userAgent) {

		FetchYahooSummary.ticker=ticker;
		FetchYahooSummary.userAgent=new UserAgent();

	}


	public boolean goToYahooFinanceSummary() {


		try {

			userAgent.visit("https://finance.yahoo.com/quote/" + ticker + "?p=" + ticker + "&.tsrc=fin-srch");


			return true;


		} catch (JauntException e) {
			System.err.println(e);
			return false;
		}


	}
	private String getName() {

		String item = null;

		try {

			Element	element = userAgent.doc.findFirst("<h1>");

			item=element.getTextContent();
			System.out.println(item);


		} catch (JauntException e) {
			System.err.println(e);

		}
		return item;



	}


	private int getPrice() {
		String value = null;
		int price=0;
		try {
			Element body = userAgent.doc.findFirst("<span class=");
			value=body.getTextContent().toString();
			if(value.contains(",")) {

				String y = value.replace(",", "");
				price = (int) Double.parseDouble(y); 

			}else {

				price = (int) Double.parseDouble(value); 


			}

			System.out.println(price);
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return price;

	}








	private int getPreviousClosePrice() {
		String value = null;
		int closePrice=0;
		try {
			Element body = userAgent.doc.findEvery("<td>");
			Element element = body.getElement(1);
			value=element.getTextContent().toString();
			if(value.contains(",")) {

				String y = value.replace(",", "");
				closePrice = (int) Double.parseDouble(y); 

			}else {

				closePrice = (int) Double.parseDouble(value); 


			}
			System.out.println(closePrice);
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return closePrice;

	}




	private int getOpenPrice() {
		String value = null;
		int openPrice = 0;

		try {
			Element body = userAgent.doc.findEvery("<td>");
			Element element = body.getElement(3);
			value=element.getTextContent();
			if(value.contains(",")) {

				String y = value.replace(",", "");
				openPrice = (int) Double.parseDouble(y); 

			}
			else {

				openPrice = (int) Double.parseDouble(value); 


			}
			System.out.println(openPrice);

		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return openPrice;

	}

	private String getDaysRange() {
		String value = null;

		try {
			Element body = userAgent.doc.findEvery("<td>");
			Element element = body.getElement(9);
			value=element.getTextContent();
			System.out.println(value);

		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;

	}


	private String getAvgVolume() {
		String value = null;
		//int avgVolume = 0;
		try {
			Element body = userAgent.doc.findEvery("<td>");
			Element element = body.getElement(15);
			value=element.getTextContent();
			//avgVolume = (int) Double.parseDouble(value); 

			System.out.println(value);

		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;

	}


	private String getMarketCap() {
		String value = null;

		try {
			Element body = userAgent.doc.findEvery("<td>");
			Element element = body.getElement(17);
			value=element.getTextContent();
			System.out.println(value);

		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;

	}

	private String earningDate() {
		String value = null;

		try {
			Element body = userAgent.doc.findEvery("<td>");
			Element element = body.getElement(25);
			value=element.getTextContent();
			System.out.println(value);

		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;

	}

	private int oneYearTargetEstimate() {
		String value = null;
		int targetEstimate =0;

		try {
			Element body = userAgent.doc.findEvery("<td>");
			Element element = body.getElement(31);
			value=element.getTextContent();

			if(value.contains(",")) {

				String y = value.replace(",", "");
				targetEstimate = (int) Double.parseDouble(y); 

			}else {

				targetEstimate = (int) Double.parseDouble(value); 


			}

			System.out.println(targetEstimate);
			return targetEstimate;

		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	private int getPE() {
		String value = null;
		int pe = 0;

		try {
			Element body = userAgent.doc.findEvery("<td>");
			Element element = body.getElement(21);
			value=element.getTextContent();
			try {
				pe = (int) Double.parseDouble(value); 
			}catch(NumberFormatException e){


			}
			System.out.println(pe);

		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pe;

	}

	private int getEPS() {
		String value = null;
		int eps = 0;

		try {
			Element body = userAgent.doc.findEvery("<td>");
			Element element = body.getElement(23);
			value=element.getTextContent();
			eps = (int) Double.parseDouble(value); 
			System.out.println(eps);

		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return eps;

	}

	private String getValuation() {

		String item = null;

		try {

			Element body = userAgent.doc.findEvery("<div class=");
			Element element = body.getElement(79);

			item=element.getTextContent();
			System.out.println(item);


		} catch (JauntException e) {
			System.err.println(e);

		}
		return item;


	}


	private HashMap<String,String> getMarketOutLook() {
		List<String> listOfTimeFrames= new ArrayList<String>();
		List<String> listOfEstimates= new ArrayList<String>();

		HashMap<String,String> performanceOutlook=new HashMap<String, String>();
		String shortTerm = null;
		String intermediateTerm = null;
		String longTerm = null;
		try {

			Element body = userAgent.doc.findEvery("<li class=");
			Element element = body.getElement(12);

			shortTerm=element.getTextContent();
			StringBuilder resultforShortTerm = new StringBuilder();


			for (int i = 0; i < shortTerm.length(); i++) {
				if (i == 10) {
					resultforShortTerm.append(" ");
				}

				resultforShortTerm.append(shortTerm.charAt(i));
			}
			shortTerm=resultforShortTerm.toString();
			listOfTimeFrames.add(shortTerm);



			Element Intermediate = userAgent.doc.findEvery("<li class=");
			Element element2 = Intermediate.getElement(13);

			intermediateTerm=element2.getTextContent();
			StringBuilder resultForIntermediate = new StringBuilder();


			for (int i = 0; i < intermediateTerm.length(); i++) {
				if (i == 8) {
					resultForIntermediate.append(" ");
				}

				resultForIntermediate.append(intermediateTerm.charAt(i));
			}


			intermediateTerm=resultForIntermediate.toString();
			listOfTimeFrames.add(intermediateTerm);



			Element Long = userAgent.doc.findEvery("<li class=");
			Element element3 = Long.getElement(14);

			longTerm=element3.getTextContent();
			StringBuilder resultForLong = new StringBuilder();


			for (int i = 0; i < longTerm.length(); i++) {
				if (i == 9) {
					resultForLong.append(" ");
				}

				resultForLong.append(longTerm.charAt(i));
			}

			longTerm=resultForLong.toString();
			listOfTimeFrames.add(longTerm);


			String shortTermOutLook=null;
			String IntermediateOutLook=null;
			String LongOutLook=null;




			Element body1 = userAgent.doc.findEvery("<svg>");
			Element ShortTermArrow = body1.getElement(7);

			Element body2 = userAgent.doc.findEvery("<svg>");
			Element IntermediateArrow = body2.getElement(8);

			Element body3 = userAgent.doc.findEvery("<svg>");
			Element LongArrow = body3.getElement(9);


			String content=ShortTermArrow.toString();
			String content2=IntermediateArrow.toString();
			String content3=LongArrow.toString();

			if(content.contains("RotateZ(180deg)")){
				shortTermOutLook="Bearish";
			}else {

				shortTermOutLook="Bullish";
			}

			listOfEstimates.add(shortTermOutLook);

			if(content2.contains("RotateZ(180deg)")){
				IntermediateOutLook="Bearish";
			}else {

				IntermediateOutLook="Bullish";
			}

			listOfEstimates.add(IntermediateOutLook);

			if(content3.contains("RotateZ(180deg)")){
				LongOutLook="Bearish";
			}else {

				LongOutLook="Bullish";
			}

			listOfEstimates.add(LongOutLook);
			System.out.println(listOfEstimates);

			for(int i=0;i<listOfTimeFrames.size();i++) {

				performanceOutlook.put(listOfTimeFrames.get(i),listOfEstimates.get(i));

			}



			System.out.println(listOfTimeFrames);
			System.out.println(performanceOutlook);




		} catch (JauntException e) {
			System.err.println(e);

		}

		return performanceOutlook;
	}
	private String getSector() {

		String item = null;

		try {

			Element body = userAgent.doc.findEvery("<div id=");
			for(int i=0;i<30;i++) {
			Element element = body.getElement(i);

			item=element.getTextContent();
			System.out.println(item);
			}

		} catch (JauntException e) {
			System.err.println(e);

		}
		return item;


	}





	public static void main(String[] args) {
		List<String> String=new ArrayList<String>(Arrays.asList("PLAN"));
		
		for(String ticker:String) {
		
		FetchYahooSummary stockSnapshot= new FetchYahooSummary(ticker,userAgent);
		stockSnapshot.goToYahooFinanceSummary();
		stockSnapshot.getName();
		stockSnapshot.getPrice();
		stockSnapshot.getPreviousClosePrice();
		stockSnapshot.getOpenPrice();
		stockSnapshot.getDaysRange();
		stockSnapshot.getAvgVolume();
		stockSnapshot.getMarketCap();
		stockSnapshot.earningDate();
		stockSnapshot.oneYearTargetEstimate();
		stockSnapshot.getPE();
		stockSnapshot.getEPS();
		stockSnapshot.getValuation();
		stockSnapshot.getMarketOutLook();
		stockSnapshot.getSector();

		}
	}





}

