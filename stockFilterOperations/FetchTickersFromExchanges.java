package stockFilterOperations;


import java.util.*;
import java.io.IOException;
import stockFilterOperations.StockFilters;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedInputStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


public class FetchTickersFromExchanges {


	public HashMap<String,String> getMapOfUrls() {

		HashMap<String,String> urls = new HashMap<String,String>();

		urls.put("NASDAQ","ftp://ftp.nasdaqtrader.com/symboldirectory/nasdaqlisted.txt");
		urls.put("NYSE","ftp://ftp.nasdaqtrader.com/symboldirectory/otherlisted.txt");


		return urls;
	}

	public String setLocation(String location) {

		if (location.equalsIgnoreCase("Desktop")){

			String Templocation = System.getProperty("user.home")+"/Desktop";
			
			location = Templocation.replace("\\", "/");



		}
		if (location.equalsIgnoreCase("Documents")){

			String Templocation = System.getProperty("user.home")+"/Documents";
			location = Templocation.replace("\\", "/");



		}

		return location;
	}

	public void getCSV(HashMap<String,String> Exchanges,String Exchange,String path) {

		String url=null;
		String tempfileName= "/"+Exchange +"temp"+".csv";
		String fileName= "/"+Exchange +"beforeClean"+".csv";
		String line;
		String tempfullPath=path+tempfileName;
		String fullPath=path+fileName;

		Iterator<Map.Entry<String, String>> entries = Exchanges.entrySet().iterator();

		while(entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			if(entry.getKey().equalsIgnoreCase(Exchange)) {

				url=entry.getValue();
				System.out.println("True");
				System.out.println(url);
				System.out.println(tempfullPath);
				System.out.println(Exchange);


			}
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

		}	
		
		try (InputStream in = URI.create(url).toURL().openStream()){
			
			 
		
			
			if(Files.deleteIfExists(Paths.get(tempfullPath))){
				Files.copy(in, Paths.get(tempfullPath));
				in.close();
			}else {
			
				Files.copy(in, Paths.get(tempfullPath));
				in.close();
				System.out.println("test1");


			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("test2");

		BufferedReader br;
	    BufferedWriter bw;
	    
		try {
			System.out.println("test3");
			br = new BufferedReader(new FileReader(tempfullPath));
			System.out.println("br");

			System.out.println("test3");

			bw = new BufferedWriter(new FileWriter(fullPath));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				line=line.replaceAll("\\|",",");
				bw.write(line);
				bw.newLine();
				System.out.println(line);
			}
			br.close();
			bw.close();
			
			Path fileToDeletePath = Paths.get(tempfullPath);
			Files.delete(fileToDeletePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}


	public static void cleanNasdaqCSV(String location, String Exchange) {


		String fileName= "/"+Exchange +"beforeClean"+".csv";
		String fullPath= location+fileName;
		String exitName="/"+Exchange+".csv";
		String exitPath= location+exitName;
		try
		{
			// Read CSV file.
			Path pathInput = Paths.get(fullPath);
			Path pathOutput = Paths.get (exitPath);
			try (
					final BufferedReader reader = Files.newBufferedReader ( pathInput , StandardCharsets.UTF_8 );            	
					final CSVPrinter printer = CSVFormat.RFC4180.withHeader("Symbol").print (pathOutput , StandardCharsets.UTF_8);
					)
			{
				Iterable < CSVRecord > records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
				// We expect these headers: ID,name1,name2,name3
				for (CSVRecord record : records)
				{
					// Read
					String name1 = record.get("Symbol");
					System.out.println ( "Symbol: " + name1 );

					// Write.
					printer.printRecord (name1);
				}
				reader.close();
				printer.close();
				Path fileToDeletePath = Paths.get(fullPath);
				Files.delete(fileToDeletePath);
			}
		} catch ( InvalidPathException e )
		{
			e.printStackTrace ();
		} catch ( IOException e )
		{
			e.printStackTrace ();
		}
		System.out.println("complete");
	}	
	
	
	public static void cleanNyseCSV(String location, String Exchange) {


		String fileName= "/"+Exchange +"beforeClean"+".csv";
		String fullPath= location+fileName;
		String exitName="/"+Exchange+".csv";
		String exitPath= location+exitName;
		try
		{
			// Read CSV file.
			Path pathInput = Paths.get(fullPath);
			Path pathOutput = Paths.get (exitPath);
			try (
					final BufferedReader reader = Files.newBufferedReader ( pathInput , StandardCharsets.UTF_8 );            	
					final CSVPrinter printer = CSVFormat.RFC4180.withHeader("Symbol").print (pathOutput , StandardCharsets.UTF_8);
					)
			{
				Iterable < CSVRecord > records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
				// We expect these headers: ID,name1,name2,name3
				for (CSVRecord record : records)
				{
					// Read
					String name1 = record.get("ACT Symbol");
					System.out.println ( "Symbol: " + name1 );

					// Write.
					printer.printRecord (name1);
				}
				reader.close();
				printer.close();
				Path fileToDeletePath = Paths.get(fullPath);
				Files.delete(fileToDeletePath);
			}
		} catch ( InvalidPathException e )
		{
			e.printStackTrace ();
		} catch ( IOException e )
		{
			e.printStackTrace ();
		}
		System.out.println("complete");
	}
	
	
	public List<String> getTickers(String location, String Exchange) {
		
		String exitName="/"+Exchange+".csv";
		String exitPath= location+exitName;
		
		List<String> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(exitPath))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null && !line.contains("File Creation")) {
		    	records.add(line);
		    	
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return records;
		
	}
	
	
	public static void main(String[] args) {
		HashMap<String, String> map=new HashMap<String, String>();
		FetchTickersFromExchanges tcks=new FetchTickersFromExchanges();
		String location=tcks.setLocation("Desktop");
		
		map=tcks.getMapOfUrls();
		System.out.println(map);
		System.out.println(location);
		tcks.getCSV(map,"NYSE",location);
		//tcks.cleanNasdaqCSV(location, "Nasdaq");
		tcks.cleanNyseCSV(location, "NYSE");
		tcks.getTickers(location,"NYSE");

		
	}
	


}


