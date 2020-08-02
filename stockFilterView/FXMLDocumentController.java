package stockFilterView;


import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import stockFilterOperations.*;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Callback;
import javafx.scene.control.TableView;

public class FXMLDocumentController implements Initializable {
	@FXML
	private Label progress;
	public static Label label;
	public Button ButtonClick;
	public Button ButtonClick1;
	public Button GenerateReport;
	public Button ExportToCSVButton;
	private CheckBox DesktopSelector;
	private CheckBox DocumentsSelector;
	public ToggleButton ShortTerm;
	public ToggleButton MidTerm;
	public ToggleButton LongTerm;
	private ChoiceBox AnalystOperator;
	private ChoiceBox VolumeOperator;
	private ChoiceBox MarketCapComparison;
	private TextField MarketCapInput;
	private TextField VolumeInput;
	private TextField AnalystInput;
	private ComboBox SectorFilter;
	private ToggleButton circleButton;
	private ToggleButton circleButton1;
	private ToggleButton circleButton2;
	public TableView TableView;
	private CheckBox StockEarningDate;
	private CheckBox StockName;
	private CheckBox StockPE;
	private CheckBox StockPrice;
	private CheckBox ClosePrice;
	private CheckBox OpenPrice;
	private CheckBox StockDayRange;
	private CheckBox StockVolume;
	private CheckBox StockMarketCap;
	private CheckBox StockTargetEstimate;
	private CheckBox StockEPS;
	private CheckBox StockValuation;
	private CheckBox StockOutlook;
	private CheckBox StockAVGRating;
	private CheckBox StockNumAnalysts;
	private CheckBox StockBuyorSell;
	
	TableColumn StockEarningColumn= new TableColumn("Earning Date");
	TableColumn StockNameColumn= new TableColumn("Name");
	TableColumn StockPEColumn= new TableColumn("P/E");
	TableColumn StockPriceColumn= new TableColumn("Price");
	TableColumn ClosePriceColumn= new TableColumn("Close Price");
	TableColumn OpenPriceColumn= new TableColumn("Open Price");
	TableColumn StockDayRangeColumn= new TableColumn("Day Range");
	TableColumn StockVolumeColumn= new TableColumn("Volume");
	TableColumn StockMarketCapColumn= new TableColumn("Market Cap");
	TableColumn StockTargetEstimatesColumn= new TableColumn("Target Estimate");
	TableColumn StockEPSColumn= new TableColumn("EPS");
	TableColumn StockValuationColumn= new TableColumn("Valuation");
	TableColumn StockOutlookColumn= new TableColumn("Performance Outlook");
	TableColumn StockAVGRatingColumn= new TableColumn("Avg Analyst Rating");
	TableColumn StockNumAnalystsColumn= new TableColumn("Number of Analysts");
	TableColumn StockBuyorSellColumn= new TableColumn("Buy or Sell");
	ArrayList<String> exchangeList=new ArrayList<String>();
	ArrayList<String> nasdaqList=new ArrayList<String>();
	ArrayList<String> nyseList=new ArrayList<String>();

	HashMap<String,String> mapOfExchangeURLs= new HashMap<String,String>();
	String fileLocation;
	String fileUrl;
	Media media = null;
	FetchTickersFromExchanges fetchTickers = new FetchTickersFromExchanges();

	@FXML
	private Slider BuySellSlider;
	@FXML
	private TextField TextFieldForRating;

	@FXML
	private ProgressBar progressBar;
	public static ProgressBar statProgressBar;

	public FXMLDocumentController() {
	}


	@FXML
	private void SelectExchangeNasdaq(ActionEvent event) {
		if (event.getSource() == ButtonClick) {
			String bstyle=String.format("-fx-background-color: %s;", "#383838");
			ButtonClick.setStyle(bstyle);
		}
		exchangeList.add("NASDAQ");
	}

	@FXML
	private void SelectExchangeNyse(ActionEvent event) {
		if (event.getSource() == ButtonClick1) {
			String bstyle=String.format("-fx-background-color: %s;", "#383838");
			ButtonClick1.setStyle(bstyle);
		}
		exchangeList.add("NYSE");

	}

	
	@FXML
	private void DesktopSelector(ActionEvent event) {
		String Text=null;
		if (event.getSource() instanceof CheckBox) {
			DesktopSelector = (CheckBox) event.getSource();
			if(DesktopSelector.isSelected()) {
				Text= DesktopSelector.getText();
			}else {
				Text="false";
			}
			System.out.println(Text);

		}
		fileLocation= Text;
	}

	@FXML
	private void DocumentsSelector(ActionEvent event) {
		String Text=null;
		if (event.getSource() instanceof CheckBox) {
			DocumentsSelector = (CheckBox) event.getSource();
			if(DocumentsSelector.isSelected()) {
				Text= DocumentsSelector.getText();
			}else {
				Text="false";
			}
			System.out.println(Text);

		}
		fileLocation= Text;
	}

	@FXML
	private void AnalystOperator(ActionEvent event) {
		String Text = "";
		if (event.getSource() instanceof ChoiceBox) {
			AnalystOperator = (ChoiceBox) event.getSource();
			if(AnalystOperator.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Greater")) {

				Text=">";
			}
			else {
				Text="<";
			}
			System.out.println(Text);

		}
	}

	@FXML
	private void VolumeOperator(ActionEvent event) {
		String Text = "";
		if (event.getSource() instanceof ChoiceBox) {
			VolumeOperator = (ChoiceBox) event.getSource();
			if(VolumeOperator.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Greater")) {

				Text=">";
			}
			else {
				Text="<";
			}
			System.out.println(Text);

		}
	}
	@FXML
	private void MarketCapOperator(ActionEvent event) {
		String Text = "";
		if (event.getSource() instanceof ChoiceBox) {
			MarketCapComparison = (ChoiceBox) event.getSource();
			if(MarketCapComparison.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Greater")) {

				Text=">";
			}
			else {
				Text="<";
			}
			System.out.println(Text);

		}
	}

	@FXML
	private void AnalystInput(ActionEvent event) {
		int value;
		Alert errorAlert = new Alert(AlertType.ERROR);
		try {
			if (event.getSource() instanceof TextField) {
				AnalystInput = (TextField) event.getSource();

				value= Integer.parseInt(AnalystInput.getText());
				System.out.println(value);
			}
		}
		catch(NumberFormatException e) {
			errorAlert.setHeaderText("Invalid Input");
			errorAlert.setContentText("Must Be Number With No Commas");
			errorAlert.showAndWait();

		}
	}


	@FXML
	private void VolumeInput(ActionEvent event) {
		Long doubleValue;
		Alert errorAlert = new Alert(AlertType.ERROR);
		try {
			if (event.getSource() instanceof TextField) {
				VolumeInput = (TextField) event.getSource();

				doubleValue= Long.parseLong(VolumeInput.getText());
				System.out.println(doubleValue);

			}

		}
		catch(NumberFormatException e) {
			errorAlert.setHeaderText("Invalid Input");
			errorAlert.setContentText("Must Be Number With No Commas");
			errorAlert.showAndWait();

		}
	}
	@FXML
	private void MarketCapInput(ActionEvent event) {
		Long doubleValue;
		Alert errorAlert = new Alert(AlertType.ERROR);
		try {
			if (event.getSource() instanceof TextField) {
				MarketCapInput = (TextField) event.getSource();

				doubleValue= Long.parseLong(MarketCapInput.getText());
				System.out.println(doubleValue);

			}
		}
		catch(NumberFormatException e) {
			errorAlert.setHeaderText("Invalid Input");
			errorAlert.setContentText("Must Be Number With No Commas");
			errorAlert.showAndWait();

		}
	}


	@FXML
	private void SectorFilter(ActionEvent event) {
		String Text = "";
		if (event.getSource() instanceof ComboBox) {
			SectorFilter = (ComboBox) event.getSource();
			Text=SectorFilter.getSelectionModel().getSelectedItem().toString();

			System.out.println(Text);

		}
	}



	@FXML
	private void ShortTermSwitch(ActionEvent event) {
		String text="Bullish";
		if (event.getSource() instanceof ToggleButton) {
			ShortTerm = (ToggleButton) event.getSource();
			if(ShortTerm.isSelected()) {
				ShortTerm.setText("Bearish");
				text=ShortTerm.getText();	}

			else {
				ShortTerm.setText("Bullish");
				text=ShortTerm.getText();	
			}
		}
	}
	@FXML
	private void MidTermSwitch(ActionEvent event) {
		String text="Bullish";
		if (event.getSource() instanceof ToggleButton) {
			MidTerm = (ToggleButton) event.getSource();
			if(MidTerm.isSelected()) {
				MidTerm.setText("Bearish");
				text=MidTerm.getText();	}

			else {
				MidTerm.setText("Bullish");
				text=MidTerm.getText();	
			}
		}
	}
	@FXML
	private void LongTermSwitch(ActionEvent event) {
		String text="Bullish";
		if (event.getSource() instanceof ToggleButton) {
			LongTerm = (ToggleButton) event.getSource();
			if(LongTerm.isSelected()) {
				LongTerm.setText("Bearish");
				text=LongTerm.getText();	}

			else {
				LongTerm.setText("Bullish");
				text=LongTerm.getText();	
			}
		}
	}

	@FXML
	private void circleButton(ActionEvent event) {

		if (event.getSource() instanceof ToggleButton){

			circleButton = (ToggleButton) event.getSource();

			if(!circleButton.isSelected()) {
				ShortTerm.setDisable(false);
				ShortTerm.setVisible(true);
			}
			if(circleButton.isSelected()) {
				ShortTerm.setDisable(true);
				ShortTerm.setVisible(false);
			}


		}
	}

	@FXML
	private void circleButton1(ActionEvent event) {
		if (event.getSource() instanceof ToggleButton) {
			circleButton1 = (ToggleButton) event.getSource();
			if(!circleButton1.isSelected()) {
				MidTerm.setDisable(false);
				MidTerm.setVisible(true);

			}
			if(circleButton1.isSelected()) {
				MidTerm.setDisable(true);
				MidTerm.setVisible(false);

			}

		}
	}


	@FXML
	private void circleButton2(ActionEvent event) {
		if (event.getSource() instanceof ToggleButton) {
			circleButton2 = (ToggleButton) event.getSource();
			if(!circleButton2.isSelected()) {
				LongTerm.setDisable(false);
				LongTerm.setVisible(true);;
			}

		}
		if(circleButton2.isSelected()) {
			LongTerm.setDisable(true);
			LongTerm.setVisible(false);

		}
	}
	@FXML
	private void getStockEarningDate(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockEarningDate = (CheckBox) event.getSource();
			if(StockEarningDate.isSelected()) {

				Text= StockEarningDate.getText();
				System.out.println(StockEarningColumn);
				StockEarningColumn.setCellValueFactory(new PropertyValueFactory<>("EarningDate"));
				TableView.getColumns().add(StockEarningColumn);
			}else {
				Text="false";
				StockEarningColumn.getCellValueFactory();
				TableView.getColumns().remove(StockEarningColumn);	
				System.out.println(StockEarningColumn);

			}
		}
	}

	@FXML
	private void getStockName(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockName = (CheckBox) event.getSource();
			if(StockName.isSelected()) {

				Text= StockName.getText();
				System.out.println(StockEarningColumn);
				StockNameColumn.setCellValueFactory(new PropertyValueFactory<>("StockName"));
				TableView.getColumns().add(StockNameColumn);
			}else {
				Text="false";
				StockNameColumn.getCellValueFactory();
				TableView.getColumns().remove(StockNameColumn);	
				System.out.println(StockNameColumn);

			}
		}
	}

	@FXML
	private void getStockPE(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockPE = (CheckBox) event.getSource();
			if(StockPE.isSelected()) {

				Text= StockPE.getText();
				System.out.println(StockPEColumn);
				StockPEColumn.setCellValueFactory(new PropertyValueFactory<>("StockPE"));
				TableView.getColumns().add(StockPEColumn);
			}else {
				Text="false";
				StockEarningColumn.getCellValueFactory();
				TableView.getColumns().remove(StockPEColumn);	
				System.out.println(StockPEColumn);

			}
		}
	}

	
	@FXML
	private void getStockPrice(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockPrice = (CheckBox) event.getSource();
			if(StockPrice.isSelected()) {

				Text= StockPrice.getText();
				System.out.println(StockPriceColumn);
				StockPriceColumn.setCellValueFactory(new PropertyValueFactory<>("StockPrice"));
				TableView.getColumns().add(StockPriceColumn);
			}else {
				Text="false";
				StockPriceColumn.getCellValueFactory();
				TableView.getColumns().remove(StockPriceColumn);	
				System.out.println(StockPriceColumn);

			}
		}
	}
	
	@FXML
	private void getClosePrice(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			ClosePrice = (CheckBox) event.getSource();
			if(ClosePrice.isSelected()) {

				Text= ClosePrice.getText();
				System.out.println(ClosePriceColumn);
				ClosePriceColumn.setCellValueFactory(new PropertyValueFactory<>("ClosePrice"));
				TableView.getColumns().add(ClosePriceColumn);
			}else {
				Text="false";
				ClosePriceColumn.getCellValueFactory();
				TableView.getColumns().remove(ClosePriceColumn);	
				System.out.println(ClosePriceColumn);

			}
		}
	}
	
	@FXML
	private void getOpenPrice(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			OpenPrice = (CheckBox) event.getSource();
			if(OpenPrice.isSelected()) {

				Text= OpenPrice.getText();
				System.out.println(OpenPriceColumn);
				OpenPriceColumn.setCellValueFactory(new PropertyValueFactory<>("OpenPrice"));
				TableView.getColumns().add(OpenPriceColumn);
			}else {
				Text="false";
				OpenPriceColumn.getCellValueFactory();
				TableView.getColumns().remove(OpenPriceColumn);	
				System.out.println(OpenPriceColumn);

			}
		}
	}
	
	@FXML
	private void getStockDayRange(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockDayRange = (CheckBox) event.getSource();
			if(StockDayRange.isSelected()) {

				Text= StockDayRange.getText();
				System.out.println(StockDayRangeColumn);
				StockDayRangeColumn.setCellValueFactory(new PropertyValueFactory<>("StockDayRange"));
				TableView.getColumns().add(StockDayRangeColumn);
			}else {
				Text="false";
				StockDayRangeColumn.getCellValueFactory();
				TableView.getColumns().remove(StockDayRangeColumn);	
				System.out.println(StockDayRangeColumn);

			}
		}
	}

	
	@FXML
	private void getStockVolume(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockVolume = (CheckBox) event.getSource();
			if(StockVolume.isSelected()) {

				Text= StockVolume.getText();
				System.out.println(StockVolumeColumn);
				StockVolumeColumn.setCellValueFactory(new PropertyValueFactory<>("StockVolume"));
				TableView.getColumns().add(StockVolumeColumn);
			}else {
				Text="false";
				StockVolumeColumn.getCellValueFactory();
				TableView.getColumns().remove(StockVolumeColumn);	
				System.out.println(StockVolumeColumn);

			}
		}
	}
	
	
	@FXML
	private void getStockMarketCap(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockMarketCap = (CheckBox) event.getSource();
			if(StockMarketCap.isSelected()) {

				Text= StockMarketCap.getText();
				System.out.println(StockMarketCapColumn);
				StockMarketCapColumn.setCellValueFactory(new PropertyValueFactory<>("StockMarketCap"));
				TableView.getColumns().add(StockMarketCapColumn);
			}else {
				Text="false";
				StockMarketCapColumn.getCellValueFactory();
				TableView.getColumns().remove(StockMarketCapColumn);	
				System.out.println(StockMarketCapColumn);

			}
		}
	}
	@FXML
	private void getStockTargetEstimate(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockTargetEstimate = (CheckBox) event.getSource();
			if(StockTargetEstimate.isSelected()) {

				Text= StockTargetEstimate.getText();
				System.out.println(StockTargetEstimatesColumn);
				StockTargetEstimatesColumn.setCellValueFactory(new PropertyValueFactory<>("StockTargetEstimate"));
				TableView.getColumns().add(StockTargetEstimatesColumn);
			}else {
				Text="false";
				StockTargetEstimatesColumn.getCellValueFactory();
				TableView.getColumns().remove(StockTargetEstimatesColumn);	
				System.out.println(StockTargetEstimatesColumn);

			}
		}
	}
	
	@FXML
	private void getStockEPS(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockEPS = (CheckBox) event.getSource();
			if(StockEPS.isSelected()) {

				Text= StockEPS.getText();
				System.out.println(StockEPSColumn);
				StockEPSColumn.setCellValueFactory(new PropertyValueFactory<>("StockEPS"));
				TableView.getColumns().add(StockEPSColumn);
			}else {
				Text="false";
				StockEPSColumn.getCellValueFactory();
				TableView.getColumns().remove(StockEPSColumn);	
				System.out.println(StockEPSColumn);

			}
		}
	}
	
	@FXML
	private void getStockValuation(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockValuation = (CheckBox) event.getSource();
			if(StockValuation.isSelected()) {

				Text= StockValuation.getText();
				System.out.println(StockValuationColumn);
				StockValuationColumn.setCellValueFactory(new PropertyValueFactory<>("StockValuation"));
				TableView.getColumns().add(StockValuationColumn);
			}else {
				Text="false";
				StockValuationColumn.getCellValueFactory();
				TableView.getColumns().remove(StockValuationColumn);	
				System.out.println(StockValuationColumn);

			}
		}
	}
	
	@FXML
	private void getStockOutlook(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockOutlook = (CheckBox) event.getSource();
			if(StockOutlook.isSelected()) {

				Text= StockOutlook.getText();
				System.out.println(StockOutlookColumn);
				StockOutlookColumn.setCellValueFactory(new PropertyValueFactory<>("StockOutlook"));
				TableView.getColumns().add(StockOutlookColumn);
			}else {
				Text="false";
				StockOutlookColumn.getCellValueFactory();
				TableView.getColumns().remove(StockOutlookColumn);	
				System.out.println(StockOutlookColumn);

			}
		}
	}
	
	
	@FXML
	private void getStockAVGRating(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockAVGRating = (CheckBox) event.getSource();
			if(StockAVGRating.isSelected()) {

				Text= StockAVGRating.getText();
				System.out.println(StockAVGRatingColumn);
				StockAVGRatingColumn.setCellValueFactory(new PropertyValueFactory<>("StockAVGRating"));
				TableView.getColumns().add(StockAVGRatingColumn);
			}else {
				Text="false";
				StockAVGRatingColumn.getCellValueFactory();
				TableView.getColumns().remove(StockAVGRatingColumn);	
				System.out.println(StockAVGRatingColumn);

			}
		}
	}
	
	
	@FXML
	private void getStockNumAnalysts(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockNumAnalysts = (CheckBox) event.getSource();
			if(StockNumAnalysts.isSelected()) {

				Text= StockNumAnalysts.getText();
				System.out.println(StockNumAnalystsColumn);
				StockNumAnalystsColumn.setCellValueFactory(new PropertyValueFactory<>("StockNumAnaysts"));
				TableView.getColumns().add(StockNumAnalystsColumn);
			}else {
				Text="false";
				StockNumAnalystsColumn.getCellValueFactory();
				TableView.getColumns().remove(StockNumAnalystsColumn);	
				System.out.println(StockNumAnalystsColumn);

			}
		}
	}
	
	
	@FXML
	private void getStockBuyorSell(ActionEvent event) {
		String Text=null;

		if (event.getSource() instanceof CheckBox) {
			StockBuyorSell = (CheckBox) event.getSource();
			if(StockBuyorSell.isSelected()) {

				Text= StockBuyorSell.getText();
				System.out.println(StockBuyorSellColumn);
				StockBuyorSellColumn.setCellValueFactory(new PropertyValueFactory<>("StockBuyOrSell"));
				TableView.getColumns().add(StockBuyorSellColumn);
			}else {
				Text="false";
				StockBuyorSellColumn.getCellValueFactory();
				TableView.getColumns().remove(StockBuyorSellColumn);	
				System.out.println(StockBuyorSellColumn);

			}
		}
	}
	
	@FXML
	private void ChangeValue(ObservableValue<Number> ovn, Number before, Number after) {
		double value;
		String string;
		if(after.intValue()>4.7 && after.intValue()<5.0) {
			TextFieldForRating.setStyle("-fx-text-inner-color: #0b6e09");
		}
		if(after.intValue()>3.7 && after.intValue()<4.69) {
			TextFieldForRating.setStyle("-fx-text-inner-color: #0bee2d");
		}
		if(after.intValue()>2.7 && after.intValue()<3.69) {
			TextFieldForRating.setStyle("-fx-text-inner-color: #c5c528");
		}
		if(after.intValue()>1.7 && after.intValue()<2.69) {
			TextFieldForRating.setStyle("-fx-text-inner-color: #ff6e00");
		}
		if(after.intValue()>0 && after.intValue()<1.69) {
			TextFieldForRating.setStyle("-fx-text-inner-color: #ff0000");
		}
		TextFieldForRating.setText(after.toString());
		value=(double) after;
		System.out.println(after);

	}

	@FXML
	private void GenerateReport(ActionEvent event) {
		
		if (event.getSource() == GenerateReport) {
			System.out.println(exchangeList);
			System.out.println(fileLocation);

			mapOfExchangeURLs= fetchTickers.getMapOfUrls();
			System.out.println(mapOfExchangeURLs);

			fileUrl=fetchTickers.setLocation(fileLocation);
			System.out.println(fileUrl);

			if(exchangeList != null) {
				for(String exchange : exchangeList) {
					
					fetchTickers.getCSV(mapOfExchangeURLs, exchange, fileUrl);
					if(exchange.equalsIgnoreCase("Nasdaq")) {
						
						fetchTickers.cleanNasdaqCSV(fileUrl, exchange);
						nasdaqList=(ArrayList<String>) fetchTickers.getTickers(fileUrl, exchange);
					}
					if(exchange.equalsIgnoreCase("Nyse")) {
						
						fetchTickers.cleanNyseCSV(fileUrl, exchange);
						nyseList=(ArrayList<String>) fetchTickers.getTickers(fileUrl, exchange);

					}
				}
			}
		}
		System.out.println(nasdaqList);
		System.out.println(nyseList);
		
		if(!nasdaqList.isEmpty()) {
			for(String tickers:nasdaqList) {
				
				
				
				
			}

			
		}


		
			
		}
		
		
	
		
		
		
		
	

	@FXML
	private void ExportToCSVButton(ActionEvent event) {
		try {
			media = new Media(getClass().getResource("/stockFilterView/Doughboyz Cashout.mp3").toURI().toString());
			MediaPlayer musicplayer = new MediaPlayer(media);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} 
		if (event.getSource() == ExportToCSVButton) {
			MediaPlayer musicplayer = new MediaPlayer(media);
			System.out.println("Export");
			musicplayer.setAutoPlay(true);




		}
	}
	public void initialize(URL url, ResourceBundle rb) {
		label = this.progress;
		statProgressBar = this.progressBar;


	}
}
